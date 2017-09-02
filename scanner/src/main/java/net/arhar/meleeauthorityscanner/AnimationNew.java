package net.arhar.meleeauthorityscanner;

import java.nio.ByteBuffer;
import java.util.*;
import net.arhar.meleeauthorityscanner.*;
import net.arhar.meleeauthorityscanner.DatReader.*;
import lombok.*;

// POJO for animation data
@Value
public class AnimationNew {
  float frameCount;
  List<AnimationCommand> commands;
  List<FrameStripEntry> frameStrip;
  Map<Integer, List<Hitbox>> frameToHitboxes;

  Character character;
  String internalName;
  int subActionId;
  SubAction.SubActionDescription description;

  int totalFrames;

  SubActionHeader motherCommand;
  AJDataHeader ajDataHeader;

  @Value
  public static class FrameStripEntry {
    boolean iasa;
    boolean hitbox;
    boolean autocancel;
    boolean invulnerable;
    boolean intangible;
    // TODO add jump cancelling, etc. here
  }

  @Value
  public static class AnimationCommand {
    AnimationCommandType type;
    int location;
    byte[] data;
  }
}
