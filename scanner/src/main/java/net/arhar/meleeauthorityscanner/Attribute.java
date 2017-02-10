package net.arhar.meleeauthorityscanner;

/** Ordinal value indicates position on disc Enum name is used for SQL schema */
public enum Attribute {
  InitWalkVel("Initial Walk Velocity", Float.class, ViewCategory.ADVANCED, null),
  WalkAccel("Walk Acceleration?", Float.class, ViewCategory.ADVANCED, null), // unknown
  WalkMaxVel("Walk Maximum Velocity", Float.class, ViewCategory.ADVANCED, null),
  SlowWalkMax("Slow Walk Max?", Float.class, ViewCategory.ADVANCED, null), // unknown
  MidWalkPoint("Mid Walk Point?", Float.class, ViewCategory.ADVANCED, null), // unknown
  FastWalkMin("Fast Walk Min?", Float.class, ViewCategory.ADVANCED, null), // unknown
  Friction("Friction/Stop Deccel", Float.class, ViewCategory.ADVANCED, "Influences wavedash length, lower is slidier"),
  DashInitVel("Dash Initial Velocity", Float.class, ViewCategory.ADVANCED, null),
  DashAccelA("Dash & Run Acceleration A", Float.class, ViewCategory.ADVANCED, null),
  DashAccelB("Dash & Run Acceleration B", Float.class, ViewCategory.ADVANCED, null),
  DashTermVel("Dash & Run Terminal Velocity", Float.class, ViewCategory.ADVANCED, null),
  RunAnimScal("Run Animation Scaling", Float.class, ViewCategory.ADVANCED, null),
  RunAccel("Run Acceleration?", Float.class, ViewCategory.ADVANCED, null), // unknown
  Unknown14(Attribute.UNKNOWN, Float.class, ViewCategory.UNKNOWN, null), // unknown
  JumpSquat("Jump Squat Frames", Integer.class, ViewCategory.BASIC, null), // TODO can i link to ssbwiki here?
  JumpHInitVel("Jump H Initial Velocity", Float.class, ViewCategory.ADVANCED, null),
  JumpVInitVel("Jump V Initial Velocity", Float.class, ViewCategory.ADVANCED, null),
  JumpMomentMult("Ground to Air Jump Momentum Multiplier", Float.class, ViewCategory.ADVANCED, null),
  JumpHMaxVel("Jump H Maximum Velocity", Float.class, ViewCategory.ADVANCED, null),
  SHVInitVel("Shorthop V Initial Velocity", Float.class, ViewCategory.ADVANCED, null),
  AirJMult("Air Jump Multiplier", Float.class, ViewCategory.ADVANCED, null),
  DblJMult("Double Jump Momentum", Float.class, ViewCategory.ADVANCED, null),
  NumJumps("Number of Jumps", Float.class, ViewCategory.BASIC, null),
  Gravity("Gravity", Float.class, ViewCategory.BASIC, null),
  TermVel("Terminal Velocity", Float.class, ViewCategory.BASIC, null),
  AirMobA("Air Mobility A", Float.class, ViewCategory.ADVANCED, null),
  AirMobB("Air Mobility B", Float.class, ViewCategory.ADVANCED, null),
  MaxAirHVel("Max Aerial H Velocity", Float.class, ViewCategory.ADVANCED, null),
  AirFriction("Air Friction", Float.class, ViewCategory.ADVANCED, null),
  FFTermVel("Fast Fall Terminal Velocity", Float.class, ViewCategory.BASIC, null),
  Unknown31(Attribute.UNKNOWN, Integer.class, ViewCategory.ADVANCED, null), // unknown
  Jab2Window("Jab 2 Window?", Float.class, ViewCategory.ADVANCED, null), // unknown
  Jab3Window("Jab 3 Window?", Float.class, ViewCategory.ADVANCED, null), // unknown
  ChDirFrames("Frames to Change Direction on Standing Turn", Float.class, ViewCategory.ADVANCED, null),
  Weight("Weight", Float.class, ViewCategory.BASIC, null),
  ModelScaling("Model Scaling", Float.class, ViewCategory.ADVANCED, null),
  ShieldSize("Shield Size", Float.class, ViewCategory.ADVANCED, null),
  ShldBrkInitVel("Shield Break Initial Velocity", Float.class, ViewCategory.ADVANCED, null),
  RpdJabWindow("Rapid Jab Window", Integer.class, ViewCategory.ADVANCED, null),
  Unknown40(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown41(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown42(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  LdgJmpHVel("Ledgejump Horizontal Velocity", Float.class, ViewCategory.ADVANCED, null),
  LdgJmpVVel("Ledgejump Vertical Velocity", Float.class, ViewCategory.ADVANCED, null),
  ThrowVel("Item Throw Velocity", Float.class, ViewCategory.ADVANCED, null),
  Unknown46(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown47(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown48(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown49(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unkonwn50(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown51(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown52(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown53(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unkonwn54(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown55(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown56(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  StarDmg("Kirby Neutral+B Star Damage", Float.class, ViewCategory.ADVANCED, null),
  ALag("Normal Landing Lag", Float.class, ViewCategory.BASIC, null),
  NLag("Nair Landing Lag", Float.class, ViewCategory.BASIC, null),
  FLag("Fair Landing Lag", Float.class, ViewCategory.BASIC, null),
  BLag("Bair Landing Lag", Float.class, ViewCategory.BASIC, null),
  ULag("Uair Landing Lag", Float.class, ViewCategory.BASIC, null),
  DLag("Dair Landing Lag", Float.class, ViewCategory.BASIC, null),
  VMdlScaling("Victory Screen Window Model Scaling", Float.class, ViewCategory.ADVANCED, null),
  Unknown65(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  WJmpHVel("WallJump H Velocity", Float.class, ViewCategory.ADVANCED, null),
  WJmpVVel("WallJump V Velocity", Float.class, ViewCategory.ADVANCED, null),
  Unknown68(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown69(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown70(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown71(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown72(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown73(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown74(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown75(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown76(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, "DJC?"), // unknown
  Unknown77(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown78(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown79(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown80(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, "DJC?"), // unknown
  Unknown81(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown82(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown83(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown84(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown85(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown86(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown87(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown88(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown89(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown90(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown91(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown92(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown93(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown94(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown95(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, null), // unknown
  Unknown96(Attribute.UNKNOWN, Integer.class, ViewCategory.UNKNOWN, "Double jump cancel?"); // unknown

  private static final String UNKNOWN = "????";

  public final String fullName;
  public final Class<?> numberType;
  public final ViewCategory viewCategory;
  public final String notes;

  private Attribute(String fullName, Class<?> numberType, ViewCategory viewCategory, String notes) {
    this.fullName = fullName;
    this.numberType = numberType;
    this.viewCategory = viewCategory;
    this.notes = notes;
  }
}
