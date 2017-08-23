package net.arhar.meleeauthorityscanner;

import com.google.common.collect.Sets;
import java.nio.ByteBuffer;
import java.util.*;
import net.arhar.meleeauthorityscanner.DatReader;
import net.arhar.meleeauthorityscanner.DatReader.AJDataHeader;
import net.arhar.meleeauthorityscanner.DatReader.SubActionHeader;

// TODO make this a pojo and move animation simulation and json stuff to other classes
public class Animation {

  public final float frameCount;
  public final List<AnimationCommand> commands;
  public final List<FrameStripEntry> frameStrip;
  public final Map<Integer, List<Hitbox>> frameToHitboxes;

  public final Character character;
  public final String internalName;
  public final int subActionId;
  public final SubAction.SubActionDescription description;

  public final int totalFrames;

  public final SubActionHeader motherCommand;
  public final AJDataHeader ajDataHeader;

  private Set<Integer> calledSubroutines = new HashSet<>();
  private Stack<Integer> callStack = new Stack<>();

  private boolean iasa = false, hitbox = false, autocancel = false, invulnerable = false, intangible = false;

  // TODO all of these are for json and should probably be put somewhere else

  Map<String, Object> getStats() {
    Map<String, Object> stats = new LinkedHashMap<>();
    //stats.put("internalName", animation.internalName);
    //stats.put("subActionId", animation.subActionId);
    stats.put("Active Frame", getActiveFrame());
    stats.put("IASA Frame", getIasaFrame());
    stats.put("Total Frames", frameCount);
    stats.put("Max Damage", getMaxDamage());
    stats.put("Max Base Knockback", getMaxBaseKnockback());
    stats.put("Max Scaling Knockback", getMaxScalingKnockback());
    stats.put("Fixed Knockback", getFixedKnockback());
    /*stats.put("motherCommand.undefined0x10", String.format("0x%08X", animation.motherCommand.undefined0x10));
    stats.put("motherCommand.undefined0x14", String.format("0x%08X", animation.motherCommand.undefined0x14));
    stats.put("ajDataHeader.undefined0x10", String.format("0x%08X", animation.ajDataHeader.undefined0x10));
    stats.put("ajDataHeader.undefined0x14", String.format("0x%08X", animation.ajDataHeader.undefined0x14));
    stats.put("ajDataHeader.undefined0x18", String.format("0x%08X", animation.ajDataHeader.undefined0x18));
    stats.put("ajDataHeader.undefined0x1C", String.format("0x%08X", animation.ajDataHeader.undefined0x1C));*/
    return stats;
  }

  Map<String, Object> getInfo() {
    Map<String, Object> info = new LinkedHashMap<>();
    info.put("description", description);
    // TODO figure out what else should go here
    return info;
  }

  List<Map<String, Object>> getFrameStrip() {
    List<Map<String, Object>> frameStripJson = new ArrayList<>();
    for (int i = 0; i < frameStrip.size(); i++) {
      Map<String, Object> frameStripMap = new LinkedHashMap<>();
      frameStripMap.put("Frame", i);
      frameStripMap.put("Hitbox", frameStrip.get(i).hitbox);
      frameStripMap.put("IASA", frameStrip.get(i).iasa);
      frameStripMap.put("Autocancel", frameStrip.get(i).autocancel);
      frameStripMap.put("Invulnerable", frameStrip.get(i).invulnerable);
      frameStripMap.put("Intangible", frameStrip.get(i).intangible);
      frameStripJson.add(frameStripMap);
    }
    return frameStripJson;
  }

  // TODO move this to some util class or something
  private static String toHexString(byte[] data) {
    StringBuilder builder = new StringBuilder();
    builder.append("0x");
    for (int i = 0; i < data.length; i++) {
      builder.append(String.format("%02X", data[i]));
    }
    return builder.toString();
  }

  List<Map<String, Object>> getCommands() {
    List<Map<String, Object>> commandsJson = new ArrayList<>();
    for (int i = 0; i < commands.size(); i++) {
      Map<String, Object> commandMap = new LinkedHashMap<>();
      commandMap.put("Command Type", commands.get(i).type.name());
      commandMap.put("Location", String.format("0x%08X", commands.get(i).location));
      // TODO mark data as monospaced somehow?
      commandMap.put("Data", toHexString(commands.get(i).data));
      commandsJson.add(commandMap);
    }
    return commandsJson;
  }

  int getActiveFrame() {
    for (int i = 0; i < frameStrip.size(); i++) {
      FrameStripEntry entry = frameStrip.get(i);
      if (entry.hitbox || entry.invulnerable || entry.intangible) {
        return i + 1;
      }
    }
    return -1;
  }

  int getIasaFrame() {
    for (int i = 0; i < frameStrip.size(); i++) {
      FrameStripEntry entry = frameStrip.get(i);
      if (entry.iasa) {
        return i + 1;
      }
    }
    return -1;
  }

  int getNetLengthFrame() {
    int iasa = getIasaFrame();
    if (iasa == -1) {
      return totalFrames;
    } else if (totalFrames > iasa) {
      return iasa;
    } else {
      return totalFrames;
    }
  }

  int getMaxDamage() {
    int maxDamage = -1;
    for (Map.Entry<Integer, List<Hitbox>> entry : frameToHitboxes.entrySet()) {
      for (Hitbox hitbox : entry.getValue()) {
        if (hitbox.damage > maxDamage) {
          maxDamage = hitbox.damage;
        }
      }
    }
    return maxDamage;
  }

  int getMaxBaseKnockback() {
    int maxBaseKnockback = -1;
    for (Map.Entry<Integer, List<Hitbox>> entry : frameToHitboxes.entrySet()) {
      for (Hitbox hitbox : entry.getValue()) {
        if (hitbox.baseKnockback > maxBaseKnockback) {
          maxBaseKnockback = hitbox.baseKnockback;
        }
      }
    }
    return maxBaseKnockback;
  }

  int getMaxScalingKnockback() {
    int maxScalingKnockback = -1;
    for (Map.Entry<Integer, List<Hitbox>> entry : frameToHitboxes.entrySet()) {
      for (Hitbox hitbox : entry.getValue()) {
        if (hitbox.knockbackScaling > maxScalingKnockback) {
          maxScalingKnockback = hitbox.knockbackScaling;
        }
      }
    }
    return maxScalingKnockback;
  }

  int getFixedKnockback() {
    int maxFixedKnockback = -1;
    for (Map.Entry<Integer, List<Hitbox>> entry : frameToHitboxes.entrySet()) {
      for (Hitbox hitbox : entry.getValue()) {
        if (hitbox.fixedKnockback > maxFixedKnockback) {
          maxFixedKnockback = hitbox.fixedKnockback;
        }
      }
    }
    return maxFixedKnockback;
  }

  public Animation(
      ByteBuffer pldat,
      SubActionHeader motherCommand,
      AJDataHeader ajDataHeader,
      Character character,
      int subActionId) {
    this.character = character;
    this.frameCount = ajDataHeader.frameCount;
    this.subActionId = subActionId;
    this.internalName = motherCommand.shortName;
    this.description = SubAction.getDescription(character, subActionId);
    this.frameToHitboxes = new TreeMap<>((one, two) -> one - two); // TODO this could be the wrong order
    this.motherCommand = motherCommand;
    this.ajDataHeader = ajDataHeader;

    // parse the commands from the file
    commands = new ArrayList<>();
    frameStrip = new ArrayList<>();
    System.out.printf("starting call at: %08X\n", motherCommand.getCommandListOffset());
    int commandListOffset = motherCommand.getCommandListOffset();

    calledSubroutines.add(commandListOffset);
    int currentLocation = commandListOffset;

    int waitFrames = 0;
    int currentFrame = 1; // start frame numbering at 1 instead of 0
    totalFrames = (int) frameCount; // TODO this is a guess, and some animations advance "frames" per frame faster than others
    int loopsRemaining = -1, loopLocation = -1;
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
          currentFrame = addFrames(currentFrame, waitFrames);
          break;
        case SYNC_TIMER:
          waitFrames = command.data[3] & 0xFF;
          currentFrame = addFrames(currentFrame, waitFrames);
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
        case BODY_STATE:
          invulnerable = (command.data[3] & 0xFF) == 1;
          intangible = (command.data[3] & 0xFF) == 2;
          break;
        case TERMINATE_COLLISION:
          // TODO this should specify a hitbox or something
        case TERMINATE_ALL_COLLISIONS:
          hitbox = false;
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

    addFrames(currentFrame, totalFrames - currentFrame);
  }

  private int addFrames(int currentFrame, int numFrames) {
    for (int i = 0; i < numFrames && currentFrame < totalFrames; i++) {
      frameStrip.add(new FrameStripEntry(iasa, hitbox, autocancel, invulnerable, intangible));
      currentFrame++;
    }
    return currentFrame;
  }

  public static class FrameStripEntry {
    public final boolean iasa;
    public final boolean hitbox;
    public final boolean autocancel;
    public final boolean invulnerable;
    public final boolean intangible;
    // TODO add jump cancelling, etc. here

    public FrameStripEntry(boolean iasa, boolean hitbox, boolean autocancel, boolean invulnerable, boolean intangible) {
      this.iasa = iasa;
      this.hitbox = hitbox;
      this.autocancel = autocancel;
      this.invulnerable = invulnerable;
      this.intangible = intangible;
    }
  }

  public static class AnimationCommand {
    public final AnimationCommandType type;
    public final int location;
    public final byte[] data;

    public int frame = -1;

    public AnimationCommand(AnimationCommandType type, int location, byte[] data) {
      this.type = type;
      this.data = data;
      this.location = location;
    }
  }
}
