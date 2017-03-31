package net.arhar.meleeauthorityscanner;

import java.util.Arrays;

// http://opensa.dantarion.com/wiki/Events_(Melee)
public enum AnimationCommandType {
  EXIT(0x00, 0x4, "Exit"),
  SYNC_TIMER(0x04, 0x4, "Synchronous Timer"),
  ASYNC_TIMER(0x08, 0x4, "Asynchronous Timer"),
  SET_LOOP(0x0C, 0x4, "Set Loop"),
  EXEC_LOOP(0x10, 0x4, "Execute Loop"),
  GOTO(0x14, 0x8, "GoTo"),
  RETURN(0x18, 0x4, "Return"),
  SUBROUTINE(0x1C, 0x8, "Subroutine"),
  // 0x20
  // 0x24
  GRAPHIC(0x28, 0x14, "Graphic Effect"),
  HITBOX(0x2C, 0x14, "Hitbox"),
  // 0x30
  // 0x34
  // 0x38
  TERMINATE_COLLISION(0x3C, 0x4, "Terminate Specific Collision"),
  TERMINATE_ALL_COLLISIONS(0x40, 0x4, "Terminate Collisions"),
  SOUND(0x44, 0xC, "Sound Effect"),
  RAND_SFX(0x48, 0x4, "Random Smash SFX"),
  AUTOCANCEL(0x4C, 0x4, "Autocancel"),
  REVERSE_DIRECTION(0x50, 0x4, "Reverse Direction?"),
  // 0x54
  // 0x58
  IASA(0x5C, 0x4, "Allow Interrupt (IASA)"),
  // 0x60
  // 0x64
  BODY_STATE(0x68, 0x4, "Body State"),
  SET_BONES(0x6C, 0x4, "Set All Bones State"),
  SET_BONE(0x70, 0x4, "Set Specific Bone State"),
  MODEL(0x7C, 0x4, "Model mod"),
  UNKNOWN(0x74, 0x4, "Unknown"), // TODO
  // 0x78
  // 0x7C
  // 0x80
  // 0x84
  THROW(0x88, 0xC, "Throw"),
  // 0x8C
  // 0x90
  // 0x94
  // 0x98
  // 0x9C
  ARTICLE(0xAC, 0x4, "Generate article?"), // TODO this also appears as "Rumble" in CrazyHand
  SELF_DAMAGE(0xCC, 0x4, "Self-Damage"),
  CONTINUATION(0xD0, 0x4, "Continuation control?"),
  CHARGE(0xE0, 0x8, "Start Smash Charge"),

  //(0x4e, 0x4, "B button check?"),
  //01 checks if b button is held down, and if it is not continues the script. See young link's arrow start.

  TBD(0xE8, 0x10, "TBD"); // TODO

  public final int id;
  public final int length;
  public final String fullName;

  private AnimationCommandType(int id, int length, String fullName) {
    this.id = id;
    this.length = length;
    this.fullName = fullName;
  }

  public static final int MAX_COMMAND_LENGTH =
      Arrays.stream(AnimationCommandType.values())
          .mapToInt(command -> command.length)
          .max()
          .getAsInt();

  public static AnimationCommandType getById(int id) {
    // clear lowest two bits
    id &= ~0b11;

    for (AnimationCommandType command : AnimationCommandType.values()) {
      if (command.id == id) {
        return command;
      }
    }

    // TODO there should probably be an entry in the enum for each id that exists
    return UNKNOWN;
  }
}
