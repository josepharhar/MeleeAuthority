package net.arhar.meleeauthorityscanner;

import com.google.common.collect.Sets;
import java.nio.ByteBuffer;
import java.util.*;
import net.arhar.meleeauthorityscanner.*;
import net.arhar.meleeauthorityscanner.DatReader.*;
import net.arhar.meleeauthorityscanner.Animation.*;

public class AnimationGenerator {

  public static Animation generate(
      ByteBuffer pldat,
      SubActionHeader motherCommand,
      AJDataHeader ajDataHeader,
      Character character,
      int subActionId) {

    Map<Integer, List<Hitbox>> frameToHitboxes = new TreeMap<>((one, two) -> one - two); // TODO this could be the wrong order
    List<FrameStripEntry> frameStrip = new LinkedList<>();
    List<AnimationCommand> commands = new LinkedList<>();

    int commandListOffset = motherCommand.getCommandListOffset();

    Stack<Integer> callStack = new Stack<>();
    Set<Integer> calledSubroutines = new HashSet<>();
    calledSubroutines.add(commandListOffset);
    int currentLocation = commandListOffset;

    int waitFrames = 0;
    int currentFrame = 1; // start frame numbering at 1 instead of 0
    float frameCount = ajDataHeader.frameCount;
    int totalFrames = (int) frameCount; // TODO this is a guess, and some animations advance "frames" per frame faster than others
    int loopsRemaining = -1, loopLocation = -1;


    FrameStripEntry.FrameStripEntryBuilder currentFrameStripEntry = FrameStripEntry.builder()
      .iasa(false)
      .hitbox(false)
      .autocancel(false)
      .invulnerable(false)
      .intangible(false);

    commandLoop:
    while (true) {
      pldat.position(currentLocation);
      AnimationCommandType commandType = AnimationCommandType.getById(pldat.get() & 0xFF);
      byte[] commandData = new byte[commandType.length];
      pldat.position(currentLocation);
      for (int i = 0; i < commandType.length; i++) {
        commandData[i] = pldat.get();
      }
      AnimationCommand command = new AnimationCommand(commandType, currentLocation, commandData, 0);
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
          currentFrame = addFrames(
              frameStrip,
              currentFrame,
              waitFrames,
              totalFrames,
              currentFrameStripEntry.build());
          break;
        case SYNC_TIMER:
          waitFrames = command.data[3] & 0xFF;
          currentFrame = addFrames(
              frameStrip,
              currentFrame,
              waitFrames,
              totalFrames,
              currentFrameStripEntry.build());
          break;
        case HITBOX:
          // TODO this is a hack to prevent duplicating hitbox info in loops
          if (loopsRemaining < 1) {
            // add the hitbox
            frameToHitboxes.putIfAbsent(currentFrame, new ArrayList<>());
            frameToHitboxes.get(currentFrame).add(new Hitbox(command.data));
          }
          currentFrameStripEntry = currentFrameStripEntry.hitbox(true);
          break;
        case IASA:
          currentFrameStripEntry = currentFrameStripEntry.iasa(true);
          break;
        case AUTOCANCEL:
          currentFrameStripEntry = currentFrameStripEntry
            .autocancel((command.data[3] & 0xFF) != 1);
          break;
        case BODY_STATE:
          currentFrameStripEntry = currentFrameStripEntry
            .invulnerable((command.data[3] & 0xFF) == 1)
            .intangible((command.data[3] & 0xFF) == 2);
          break;
        case TERMINATE_COLLISION:
          // TODO this should specify a hitbox or something
        case TERMINATE_ALL_COLLISIONS:
          currentFrameStripEntry = currentFrameStripEntry.hitbox(false);
          break;
        case SET_LOOP:
          loopsRemaining = (command.data[3] & 0xFF) - 1;
          loopLocation = currentLocation;
          break;
        case EXEC_LOOP:
          if (loopsRemaining > 0) {
            currentLocation = loopLocation;
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

    addFrames(
        frameStrip,
        currentFrame,
        totalFrames - currentFrame,
        totalFrames,
        currentFrameStripEntry.build());

    return new Animation(
        character,
        subActionId,
        ajDataHeader.frameCount,
        totalFrames,
        motherCommand.shortName,
        SubAction.getDescription(character, subActionId),
        ajDataHeader,
        motherCommand,
        frameToHitboxes,
        frameStrip,
        commands);
  }

  private static int addFrames(
      List<FrameStripEntry> frameStrip,
      int currentFrame,
      int numFrames,
      int totalFrames,
      FrameStripEntry entry) {
    for (int i = 0; i < numFrames && currentFrame < totalFrames; i++) {
      frameStrip.add(entry);
      currentFrame++;
    }
    return currentFrame;
  }
}
