package net.arhar.meleeauthorityscanner;

/** Ordinal value indicates position on disc Enum name is used for SQL schema */
public enum Attribute {
  InitWalkVel("Initial Walk Velocity", Float.class, false, null),
  WalkAccel("Walk Acceleration?", Float.class, false, null), // unknown
  WalkMaxVel("Walk Maximum Velocity", Float.class, false, null),
  SlowWalkMax("Slow Walk Max?", Float.class, false, null), // unknown
  MidWalkPoint("Mid Walk Point?", Float.class, false, null), // unknown
  FastWalkMin("Fast Walk Min?", Float.class, false, null), // unknown
  Friction("Friction/Stop Deccel", Float.class, false, "Influences wavedash length, lower is slidier"),
  DashInitVel("Dash Initial Velocity", Float.class, false, null),
  DashAccelA("Dash & Run Acceleration A", Float.class, false, null),
  DashAccelB("Dash & Run Acceleration B", Float.class, false, null),
  DashTermVel("Dash & Run Terminal Velocity", Float.class, false, null),
  RunAnimScal("Run Animation Scaling", Float.class, false, null),
  RunAccel("Run Acceleration?", Float.class, false, null), // unknown
  Unknown14(Attribute.UNKNOWN, Float.class, false, null), // unknown
  JumpFrames("Jump Startup Lag (Frames)", Float.class, true, null),
  JumpHInitVel("Jump H Initial Velocity", Float.class, false, null),
  JumpVInitVel("Jump V Initial Velocity", Float.class, false, null),
  JumpMomentMult("Ground to Air Jump Momentum Multiplier", Float.class, false, null),
  JumpHMaxVel("Jump H Maximum Velocity", Float.class, false, null),
  SHVInitVel("Shorthop V Initial Velocity", Float.class, false, null),
  AirJMult("Air Jump Multiplier", Float.class, false, null),
  DblJMult("Double Jump Momentum", Float.class, false, null),
  NumJumps("Number of Jumps", Integer.class, true, null),
  Gravity("Gravity", Float.class, true, null),
  TermVel("Terminal Velocity", Float.class, true, null),
  AirMobA("Air Mobility A", Float.class, false, null),
  AirMobB("Air Mobility B", Float.class, false, null),
  MaxAirHVel("Max Aerial H Velocity", Float.class, false, null),
  AirFriction("Air Friction", Float.class, false, null),
  FFTermVel("Fast Fall Terminal Velocity", Float.class, true, null),
  Unknown31(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Jab2Window("Jab 2 Window?", Float.class, false, null), // unknown
  Jab3Window("Jab 3 Window?", Float.class, false, null), // unknown
  ChDirFrames("Frames to Change Direction on Standing Turn", Float.class, false, null),
  Weight("Weight", Float.class, true, null),
  ModelScaling("Model Scaling", Float.class, false, null),
  ShieldSize("Shield Size", Float.class, false, null),
  ShldBrkInitVel("Shield Break Initial Velocity", Float.class, false, null),
  RpdJabWindow("Rapid Jab Window", Integer.class, false, null),
  Unknown40(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown41(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown42(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  LdgJmpHVel("Ledgejump Horizontal Velocity", Float.class, false, null),
  LdgJmpVVel("Ledgejump Vertical Velocity", Float.class, false, null),
  ThrowVel("Item Throw Velocity", Float.class, false, null),
  Unknown46(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown47(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown48(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown49(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unkonwn50(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown51(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown52(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown53(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unkonwn54(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown55(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown56(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  StarDmg("Kirby Neutral+B Star Damage", Float.class, false, null),
  ALag("Normal Landing Lag", Float.class, true, null),
  NLag("N-Air Landing Lag", Float.class, true, null),
  FLag("F-Air Landing Lag", Float.class, true, null),
  BLag("B-Air Landing Lag", Float.class, true, null),
  ULag("U-Air Landing Lag", Float.class, true, null),
  DLag("D-Air Landing Lag", Float.class, true, null),
  VMdlScaling("Victory Screen Window Model Scaling", Float.class, false, null),
  Unknown65(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  WJmpHVel("WallJump H Velocity", Float.class, false, null),
  WJmpVVel("WallJump V Velocity", Float.class, false, null),
  Unknown68(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown69(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown70(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown71(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown72(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown73(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown74(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown75(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown76(Attribute.UNKNOWN, Integer.class, false, "DJC?"), // unknown
  Unknown77(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown78(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown79(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown80(Attribute.UNKNOWN, Integer.class, false, "DJC?"), // unknown
  Unknown81(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown82(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown83(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown84(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown85(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown86(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown87(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown88(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown89(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown90(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown91(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown92(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown93(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown94(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown95(Attribute.UNKNOWN, Integer.class, false, null), // unknown
  Unknown96(Attribute.UNKNOWN, Integer.class, false, "Double jump cancel?"); // unknown

  private static final String UNKNOWN = "????";

  public final String fullName;
  public final Class<?> numberType;
  public final boolean basic;
  public final String notes;

  private Attribute(String fullName, Class<?> numberType, boolean basic, String notes) {
    this.fullName = fullName;
    this.numberType = numberType;
    this.basic = basic;
    this.notes = notes;
  }
}
