package net.arhar.meleeauthorityscanner;

import com.google.common.collect.Sets;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.arhar.meleeauthorityscanner.DatReader.AJDataHeader;
import net.arhar.meleeauthorityscanner.DatReader.SubActionHeader;

public class Animation {

  public final float frameCount;
  public final List<AnimationCommand> commands;
  public final List<FrameStripEntry> frameStrip;
  public final Map<Integer, List<Hitbox>> frameToHitboxes;

  public final String internalName;
  public final int subActionId;
  public final SubAction.SubActionDescription description;

  // TODO remove this
  public static boolean temp = false;

  public Animation(
      ByteBuffer pldat,
      SubActionHeader motherCommand,
      AJDataHeader ajHeader,
      Character character,
      int subActionId) {
    if (temp) {
      System.out.println();
    }

    this.frameCount = ajHeader.frameCount;

    this.subActionId = subActionId;
    this.internalName = motherCommand.shortName;
    this.description = SubAction.getDescription(character, subActionId);

    // parse the commands from the file
    commands = new ArrayList<>();
    frameStrip = new ArrayList<>();
    int commandListOffset = motherCommand.getCommandListOffset();
    int bytesDown = 0;
    pldat.position(commandListOffset + bytesDown);
    // command lists are null terminated by four zero bytes
    while (pldat.getInt() != 0) {
      pldat.position(commandListOffset + bytesDown);
      AnimationCommandType commandType = AnimationCommandType.getById(pldat.get() & 0xFF);
      byte[] commandData = new byte[commandType.length];
      pldat.position(commandListOffset + bytesDown);
      for (int i = 0; i < commandType.length; i++) {
        commandData[i] = pldat.get();
      }
      commands.add(new AnimationCommand(commandType, commandData));
      bytesDown += commandType.length;
      pldat.position(commandListOffset + bytesDown);
    }

    frameToHitboxes = new TreeMap<>((one, two) -> one - two); // TODO this could be the wrong order

    boolean iasa = false;
    boolean hitbox = false;
    boolean autocancel = false;
    int commandIndex = 0;
    int waitFrames = 0;
    int currentFrame = 1; // start frame numbering at 1 instead of 0
    int totalFrames = (int) frameCount; // TODO this is a guess, and some animations advance "frames" per frame faster than others
    int loopsRemaining = 0, loopIndex = 0;
    frameLoop:
    while (commandIndex < commands.size()
        || waitFrames > 0
        || currentFrame < totalFrames) { // TODO check is frameCount comparison is correct here
      if (commandIndex < commands.size() && waitFrames == 0) {
        // execute the next command because there is no wait left
        AnimationCommand command = commands.get(commandIndex++);
        command.frame = currentFrame;

        switch (command.type) {
          case ASYNC_TIMER:
            // async timers: execute the next command on this frame number
            // async 4 -> do thing on 4th frame, or frames[3]
            // if we are on frame[0], and we want to do something on frame[3], then we have 3 wait frames
            // wait frames = asyncframe - 1
            // it isnt wait this number of frames its do this thing on this frame
            waitFrames = (command.data[3] & 0xFF) - currentFrame;
            if (waitFrames < 0) {
              // the command is telling us to do something on a frame that already occured
              // just do it now i guess
              // TODO investigate this more, there are aync timers for frame 0 that worked before for some reason
              waitFrames = 0;
            }
            break;
          case SYNC_TIMER:
            waitFrames = command.data[3] & 0xFF;
            break;
          case HITBOX:
            // add the hitbox
            frameToHitboxes.putIfAbsent(currentFrame, new ArrayList<>());
            frameToHitboxes.get(currentFrame).add(new Hitbox(command.data));
            hitbox = true;
            break;
          case IASA:
            iasa = true;
            if (temp) {
              System.out.println("iasa on frame " + currentFrame);
            }
            break;
          case AUTOCANCEL:
            if ((command.data[3] & 0xFF) == 1) {
              autocancel = false;
            } else {
              autocancel = true;
            }
            if (temp) {
              System.out.println("autocancel on frame " + currentFrame + ": " + autocancel);
            }
            break;
          case TERMINATE_ALL_COLLISIONS:
            hitbox = false;
            break;
          case TERMINATE_COLLISION:
            // TODO this should specify a hitbox or something
            hitbox = false;
            break;
          case SET_LOOP:
            loopsRemaining = command.data[3] & 0xFF;
            loopIndex = commandIndex + 1;
            break;
          case EXEC_LOOP:
            if (loopsRemaining > 0) {
              commandIndex = loopIndex;
              loopsRemaining--;
            }
            break;
          case GOTO:
          case RETURN:
          case SUBROUTINE:
            // TODO
            break frameLoop;
          case ARTICLE:
          case BODY_STATE:
          case CHARGE:
          case CONTINUATION:
          case GRAPHIC:
          case MODEL:
          case RAND_SFX:
          case REVERSE_DIRECTION:
          case SELF_DAMAGE:
          case SET_BONE:
          case SET_BONES:
          case SOUND:
          case TBD:
          case THROW:
          case UNKNOWN:
            // TODO
            break;
        }
      } else {
        // advance a frame
        if (waitFrames > 0) {
          waitFrames--;
        }
        currentFrame++;

        frameStrip.add(new FrameStripEntry(iasa, hitbox, autocancel));
      }
    }

    if (temp) {
      System.out.println();
    }
  }

  public static class FrameStripEntry {
    public final boolean iasa;
    public final boolean hitbox;
    public final boolean autocancel;
    // TODO add invulnerability, etc. here

    public FrameStripEntry(boolean iasa, boolean hitbox, boolean autocancel) {
      this.iasa = iasa;
      this.hitbox = hitbox;
      this.autocancel = autocancel;
    }
  }

  public static class AnimationCommand {
    public final AnimationCommandType type;
    public final byte[] data;

    public int frame = -1;

    public AnimationCommand(AnimationCommandType type, byte[] data) {
      this.type = type;
      this.data = data;
    }
  }
}
