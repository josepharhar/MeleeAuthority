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
import java.util.Stack;
import java.util.LinkedList;
import java.util.TreeMap;
import net.arhar.meleeauthorityscanner.DatReader;
import net.arhar.meleeauthorityscanner.DatReader.AJDataHeader;
import net.arhar.meleeauthorityscanner.DatReader.SubActionHeader;

public class Animation {

  public final float frameCount;
  public final List<AnimationCommand> commands;
  public final List<FrameStripEntry> frameStrip;
  public final Map<Integer, List<Hitbox>> frameToHitboxes;

  public final Character character;
  public final String internalName;
  public final int subActionId;
  public final SubAction.SubActionDescription description;

  public Animation(
      ByteBuffer pldat,
      SubActionHeader motherCommand,
      AJDataHeader ajHeader,
      Character character,
      int subActionId) {
    this.character = character;
    this.frameCount = ajHeader.frameCount;
    this.subActionId = subActionId;
    this.internalName = motherCommand.shortName;
    this.description = SubAction.getDescription(character, subActionId);
    this.frameToHitboxes = new TreeMap<>((one, two) -> one - two); // TODO this could be the wrong order

    // parse the commands from the file
    commands = new ArrayList<>();
    frameStrip = new ArrayList<>();
    System.out.printf("starting call at: %08X\n", motherCommand.getCommandListOffset());
    asdf(pldat, motherCommand.getCommandListOffset());
  }

  private Set<Integer> calledSubroutines = new HashSet<>();
  private Stack<Integer> callStack = new Stack<>();

  private void asdf(ByteBuffer pldat, int commandListOffset) {
    calledSubroutines.add(commandListOffset);
    int currentLocation = commandListOffset;

    boolean iasa = false;
    boolean hitbox = false;
    boolean autocancel = false;
    int commandIndex = 0;
    int waitFrames = 0;
    int currentFrame = 1; // start frame numbering at 1 instead of 0
    int totalFrames = (int) frameCount; // TODO this is a guess, and some animations advance "frames" per frame faster than others
    int loopsRemaining = -1, loopIndex = -1;
    commandLoop:
    while (true) {
      pldat.position(currentLocation);
      AnimationCommandType commandType = AnimationCommandType.getById(pldat.get() & 0xFF);
      byte[] commandData = new byte[commandType.length];
      pldat.position(currentLocation);
      for (int i = 0; i < commandType.length; i++) {
        commandData[i] = pldat.get();
      }
      AnimationCommand command = new AnimationCommand(commandType, currentLocation, commandData);
      command.totalFrames = totalFrames;
      commands.add(command);
      currentLocation += commandType.length;

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
          addFrames(waitFrames, iasa, hitbox, autocancel);
          currentFrame += waitFrames;
          break;
        case SYNC_TIMER:
          waitFrames = command.data[3] & 0xFF;
          addFrames(waitFrames, iasa, hitbox, autocancel);
          currentFrame += waitFrames;
          break;
        case HITBOX:
          // TODO this is a hack to prevent duplicating hitbox info in loops
          if (loopsRemaining < 1) {
            // add the hitbox
            frameToHitboxes.putIfAbsent(currentFrame, new ArrayList<>());
            frameToHitboxes.get(currentFrame).add(new Hitbox(command.data));
          }
          hitbox = true;
          break;
        case IASA:
          iasa = true;
          break;
        case AUTOCANCEL:
          autocancel = (command.data[3] & 0xFF) != 1;
          break;
        case TERMINATE_COLLISION:
          // TODO this should specify a hitbox or something
        case TERMINATE_ALL_COLLISIONS:
          hitbox = false;
          break;
        case SET_LOOP:
          loopsRemaining = (command.data[3] & 0xFF) - 1;
          loopIndex = commandIndex;
          break;
        case EXEC_LOOP:
          if (loopsRemaining > 0) {
            commandIndex = loopIndex;
            loopsRemaining--;
          }
          break;
        case GOTO:
        case SUBROUTINE:
          int offset = ((command.data[4] & 0xFF) << 24)
            | ((command.data[5] & 0xFF) << 16)
            | ((command.data[6] & 0xFF) << 8)
            | (command.data[7] & 0xFF);
          offset += DatReader.DATA_OFFSET;
          if (offset == commandListOffset || calledSubroutines.contains(offset)) {
            //System.out.println("skipping recursion");
          } else {
            System.out.printf("calling subroutine 0x%08X from 0x%08X command 0x%08X\n", offset, commandListOffset, command.location);
            System.out.println("animation: " + character.fullName + " " + internalName);
            callStack.push(currentLocation);
            calledSubroutines.add(offset);
            currentLocation = offset;
          }

          break;
        case RETURN:
          break commandLoop; // TODO this should set currentLocation and do a regular break
          //break;
        case EXIT:
          break commandLoop;
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
    }

    addFrames(totalFrames - currentFrame, iasa, hitbox, autocancel);
  }

  private void addFrames(int numFrames, boolean iasa, boolean hitbox, boolean autocancel) {
    for (int i = 0; i < numFrames; i++) {
      frameStrip.add(new FrameStripEntry(iasa, hitbox, autocancel));
    }
  }

  public static class FrameStripEntry {
    public final boolean iasa;
    public final boolean hitbox;
    public final boolean autocancel;
    // TODO add invulnerability, jump cancelling, etc. here

    public FrameStripEntry(boolean iasa, boolean hitbox, boolean autocancel) {
      this.iasa = iasa;
      this.hitbox = hitbox;
      this.autocancel = autocancel;
    }
  }

  public static class AnimationCommand {
    public final AnimationCommandType type;
    public final int location;
    public final byte[] data;

    public int totalFrames = -1; // TODO delet this
    public int frame = -1;

    public AnimationCommand(AnimationCommandType type, int location, byte[] data) {
      this.type = type;
      this.data = data;
      this.location = location;
    }
  }
}
