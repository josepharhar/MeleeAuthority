package net.arhar.meleeauthorityscanner;

import java.nio.ByteBuffer;
import java.util.*;
import net.arhar.meleeauthorityscanner.*;
import net.arhar.meleeauthorityscanner.DatReader.*;
import lombok.*;

// TODO use @Value for all of these classes

@AllArgsConstructor
public class Animation {
  Character character;
  int subActionId;
  float frameCount;
  int totalFrames;
  String internalName;
  SubAction.SubActionDescription description;
  AJDataHeader ajDataHeader;
  SubActionHeader motherCommand;
  Map<Integer, List<Hitbox>> frameToHitboxes;
  List<FrameStripEntry> frameStrip;
  List<AnimationCommand> commands;

  @Builder
  public static class FrameStripEntry {
    boolean iasa;
    boolean hitbox;
    boolean autocancel;
    boolean invulnerable;
    boolean intangible;
    // TODO add jump cancelling, etc. here
  }

  @AllArgsConstructor
  public static class AnimationCommand {
    AnimationCommandType type;
    int location;
    byte[] data;

    // variable
    int frame;
  }

  // TODO all of these are for json and should probably be put somewhere else

  Map<String, Object> getStats() {
    Map<String, Object> stats = new LinkedHashMap<>();
    //stats.put("internalName", animation.internalName);
    //stats.put("subActionId", animation.subActionId);
    stats.put("Active Frame", getActiveFrame());
    //stats.put("IASA Frame", getIasaFrame());
    stats.put("IASA Frame", getNetLengthFrame());
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
}
