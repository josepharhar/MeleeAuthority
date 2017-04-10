package net.arhar.meleeauthorityscanner;

import static net.arhar.meleeauthorityscanner.Character.Ca;
import static net.arhar.meleeauthorityscanner.Character.Cl;
import static net.arhar.meleeauthorityscanner.Character.Dk;
import static net.arhar.meleeauthorityscanner.Character.Dr;
import static net.arhar.meleeauthorityscanner.Character.Fc;
import static net.arhar.meleeauthorityscanner.Character.Fe;
import static net.arhar.meleeauthorityscanner.Character.Fx;
import static net.arhar.meleeauthorityscanner.Character.Gn;
import static net.arhar.meleeauthorityscanner.Character.Gw;
import static net.arhar.meleeauthorityscanner.Character.Kp;
import static net.arhar.meleeauthorityscanner.Character.Lg;
import static net.arhar.meleeauthorityscanner.Character.Lk;
import static net.arhar.meleeauthorityscanner.Character.Mr;
import static net.arhar.meleeauthorityscanner.Character.Ms;
import static net.arhar.meleeauthorityscanner.Character.Mt;
import static net.arhar.meleeauthorityscanner.Character.Ns;
import static net.arhar.meleeauthorityscanner.Character.Pc;
import static net.arhar.meleeauthorityscanner.Character.Pe;
import static net.arhar.meleeauthorityscanner.Character.Pk;
import static net.arhar.meleeauthorityscanner.Character.Pp;
import static net.arhar.meleeauthorityscanner.Character.Pr;
import static net.arhar.meleeauthorityscanner.Character.Sk;
import static net.arhar.meleeauthorityscanner.Character.Ss;
import static net.arhar.meleeauthorityscanner.Character.Ys;
import static net.arhar.meleeauthorityscanner.Character.Zd;
import static net.arhar.meleeauthorityscanner.SubAction.SubActionCategory.*;
import static net.arhar.meleeauthorityscanner.ViewCategory.*;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class SubAction {

  private static final Map<Integer, SubAction> SUBACTIONS = ImmutableMap.<Integer, SubAction>builder()

    // Shared Moves

    .put(0x000, new SubAction())
    // TODO ...
    .put(0x00F, new SubAction("Jump Squat/Charge")) // this information is in the character attribute "JumpFrames"

    .put(0x024, new SubAction("Special/Wavedash Landing Lag")) // this is broken?
    .put(0x025, new SubAction("Start Shield"))

    .put(0x027, new SubAction("Stop Shield"))

    .put(0x029, new SubAction("Spot Dodge", Dodge, BASIC))
    .put(0x02A, new SubAction("Dodge Roll Forward", Dodge, BASIC))
    .put(0x02B, new SubAction("Dodge Roll Backward", Dodge, BASIC))
    .put(0x02C, new SubAction("Air Dodge", Dodge, BASIC))
    .put(0x02E, new SubAction("Jab 1"))
    .put(0x02F, new SubAction("Jab 2"))

    .put(0x031, new SubAction("Rapid Jab Start"))
    .put(0x032, new SubAction("Rapid Jab Loop"))
    .put(0x033, new SubAction("Rapid Jab End"))
    .put(0x034, new SubAction("Dash Attack"))
    .put(0x035, new SubAction("Forward Tilt (High)", Tilt, BASIC))
    .put(0x037, new SubAction("Forward Tilt", Tilt, BASIC))
    .put(0x039, new SubAction("Forward Tilt (Low)", Tilt, BASIC))
    .put(0x03A, new SubAction("Up Tilt", Tilt, BASIC))
    .put(0x03B, new SubAction("Down Tilt", Tilt, BASIC))
    .put(0x03C, new SubAction("Forward Smash (High)", Smash, BASIC))
    .put(0x03E, new SubAction("Forward Smash", Smash, BASIC))
    .put(0x040, new SubAction("Forward Smash (Low)", Smash, BASIC))
    .put(0x042, new SubAction("Up Smash", Smash, BASIC))
    .put(0x043, new SubAction("Down Smash", Smash, BASIC))
    .put(0x044, new SubAction("Neutral Aerial", Aerial, BASIC))
    .put(0x045, new SubAction("Forward Aerial", Aerial, BASIC))
    .put(0x046, new SubAction("Back Aerial", Aerial, BASIC))
    .put(0x047, new SubAction("Up Aerial", Aerial, BASIC))
    .put(0x048, new SubAction("Down Aerial", Aerial, BASIC))
    .put(0x049, new SubAction("Nair Landing Lag"))
    .put(0x04A, new SubAction("Fair Landing Lag"))
    .put(0x04B, new SubAction("Bair Landing Lag"))
    .put(0x04C, new SubAction("Uair Landing Lag"))
    .put(0x04D, new SubAction("Dair Landing Lag"))

    .put(0x0B7, new SubAction("Missed Tech", Tech, BASIC)) // DownBoundU
    // 0x0B8 DownWaitU
    // 0x0B9 DownDamageU
    .put(0x0BA, new SubAction("Missed Tech Neutral Get Up", Tech, BASIC)) // DownStandU
    .put(0x0BB, new SubAction("Missed Tech Get Up Attack", Tech, BASIC)) // DownAttackU
    .put(0x0BC, new SubAction("Missed Tech Roll Forwards", Tech, BASIC)) // DownForwardU
    .put(0x0BD, new SubAction("Missed Tech Roll Backwards", Tech, BASIC)) // DownBackU
    // 0x0BE DownSpotU
    // TODO what is the difference between "U" and "D" techs? http://opensa.dantarion.com/wiki/Subactions_(Melee)
    // DownBoundU and DownBoundD have their own pieces of memory for animation commands,
    // but the animation commands are identical and the animations have the same length on marth.
    //.put(0x0BF, new SubAction("Missed Tech (DownBoundD)", Tech, BASIC)) // DownBoundD

    .put(0x0C7, new SubAction("Tech In Place", Tech, BASIC)) // Passive
    .put(0x0C8, new SubAction("Tech Forwards", Tech, BASIC)) // PassiveStandF
    .put(0x0C9, new SubAction("Tech Backwards", Tech, BASIC)) // PassiveStandB

    //.put(0x0D9, new SubAction("CliffWait1", Ledge, BASIC))
    //.put(0x0DA, new SubAction("CliffWait2", Ledge, BASIC))
    .put(0x0DB, new SubAction("Neutral Get Up >100%", Ledge, BASIC)) // CliffClimbSlow
    .put(0x0DC, new SubAction("Neutral Get Up", Ledge, BASIC)) // CliffClimbQuick
    .put(0x0DD, new SubAction("Get Up Attack >100%", Ledge, BASIC)) // CliffAttackSlow
    .put(0x0DE, new SubAction("Get Up Attack", Ledge, BASIC)) // CliffAttackQuick
    .put(0x0DF, new SubAction("Roll Up >100%", Ledge, BASIC)) // CliffEscapeSlow
    .put(0x0E0, new SubAction("Roll Up", Ledge, BASIC)) // CliffEscapeQuick
    // TODO When you do a tournament winner, it does both of these subactions consecutively
    .put(0x0E1, new SubAction("Tournament Winner part 1 >100%", Ledge, BASIC)) // CliffJumpSlow1
    .put(0x0E2, new SubAction("Tournament Winner part 2 >100%", Ledge, BASIC)) // CliffJumpSlow2
    .put(0x0E3, new SubAction("Tournament Winner part 1", Ledge, BASIC)) // CliffJumpQuick1
    .put(0x0E4, new SubAction("Tournament Winner part 2", Ledge, BASIC)) // CliffJumpQuick2
    
    // tech in place: Passive
    // tech backwards: PassiveStandB
    // tech forwards: PassiveStandF

    .put(0x0F7, new SubAction("Forward Throw"))
    .put(0x0F8, new SubAction("Back Throw"))
    .put(0x0F9, new SubAction("Up Throw"))
    .put(0x0FA, new SubAction("Down Throw"))

    // Special Moves

    .put(0x127, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B Start (Ground)"))
      .put(Mr, new SubActionDescription("Mario Neutral-B (Ground)"))
      .put(Dr, new SubActionDescription("Dr. Mario Neutral-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B Start (Ground)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B Start (Ground)"))
      .put(Gw, new SubActionDescription("G&W Neutral-B (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Neutral-B (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Neutral-B (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B Start (Ground)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B Start (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Start (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Neutral-B (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Neutral-B (Ground)"))
      .put(Ss, new SubActionDescription("Samus Down-B? (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B Start (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Neutral-B (Ground)"))
      .build()))
    .put(0x128, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B Loop (Ground)"))
      .put(Mr, new SubActionDescription("Mario Neutral-B (Air)"))
      .put(Dr, new SubActionDescription("Dr. Mario Neutral-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B Loop (Ground)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B Loop (Ground)"))
      .put(Gw, new SubActionDescription("G&W Neutral-B (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Neutral-B (Air)"))
      .put(Lg, new SubActionDescription("Luigi Neutral-B (Air)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B Hold (Ground)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B Hold (Ground)"))
      .put(Lk, new SubActionDescription("Link Neutral-B Start (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B Start (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Loop (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Neutral-B (Air)"))
      .put(Pc, new SubActionDescription("Pichu Neutral-B (Air)"))
      .put(Ss, new SubActionDescription("Samus Down-B (Air)"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B(2) (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Neutral-B (Air)"))
      .build()))
    .put(0x129, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B End (Ground)"))
      .put(Mr, new SubActionDescription("Mario Side-B (Ground)"))
      .put(Dr, new SubActionDescription("Dr. Mario Side-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B End (Ground)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B End (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(1) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Side-B(1) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B Start (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B End (Ground)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B End (Ground)"))
      .put(Lk, new SubActionDescription("Link Neutral-B Charge (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B Charge (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Loop(2) (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B Start (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Side-B Start (Ground)"))
      .put(Ss, new SubActionDescription("Samus Neutral-BStart"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B Cancel (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B(3) (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Side-B Start (Ground)"))
      .build()))
    .put(0x12A, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B Start (Air)"))
      .put(Mr, new SubActionDescription("Mario Side-B (Air)"))
      .put(Dr, new SubActionDescription("Dr. Mario Side-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B Start (Air)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B Start (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(2) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Side-B(2) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B Charge (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B End(2) (Ground)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B End(2) (Ground)"))
      .put(Lk, new SubActionDescription("Link Neutral-B End (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B End (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Cancel (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B Hold (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Side-B Hold (Ground)"))
      .put(Ss, new SubActionDescription("Samus Neutral-B Hold"))
      .put(Sk, new SubActionDescription("Sheik Shoot Needles(?) (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B (Air)"))
      .put(Zd, new SubActionDescription("Zelda Side-B Loop (Ground)"))
      .build()))
    .put(0x12B, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B Loop (Air)"))
      .put(Mr, new SubActionDescription("Mario Up-B (Ground)"))
      .put(Dr, new SubActionDescription("Dr. Mario Up-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B Loop (Air)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B Loop (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(3) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Side-B(1) (Air)"))
      .put(Lg, new SubActionDescription("Luigi Side-B(1) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B Start (Air)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B Start (Air)"))
      .put(Lk, new SubActionDescription("Link Neutral-B Start (Air)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B Start (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B End (Ground)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Start (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Side-B (Ground)"))
      .put(Ss, new SubActionDescription("Samus Neutral-B Cancel"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B Start (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B(2) (Air)"))
      .put(Zd, new SubActionDescription("Zelda Side-B End (Ground)"))
      .build()))
    .put(0x12C, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Neutral-B End (Air)"))
      .put(Mr, new SubActionDescription("Mario Up-B (Air)"))
      .put(Dr, new SubActionDescription("Dr. Mario Up-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Neutral-B End (Air)"))
      .put(Fc, new SubActionDescription("Falco Neutral-B End (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(4) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Side-B(2) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B Start (R) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B(2) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B Hold (Air)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B Hold (Air)"))
      .put(Lk, new SubActionDescription("Link Neutral-B Charge (Air)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B Charge (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Start (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Hold (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B(2) (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Side-B(2) (Ground)"))
      .put(Ss, new SubActionDescription("Samus Neutral-B"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Neutral-B(3) (Air)"))
      .put(Zd, new SubActionDescription("Zelda Side-B Start (Air)"))
      .build()))
    .put(0x12D, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Start (Ground)"))
      .put(Ca, new SubActionDescription("Falcon Neutral-B (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Neutral-B (Ground)"))
      .put(Mr, new SubActionDescription("Mario Down-B (Ground)"))
      .put(Dr, new SubActionDescription("Dr. Mario Down-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Side-B Start (Ground)"))
      .put(Fc, new SubActionDescription("Falco Side-B Start (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(5) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Start (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B Start (L) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B(3) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B End (Air)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B End (Air)"))
      .put(Lk, new SubActionDescription("Link Neutral-B End (Air)"))
      .put(Cl, new SubActionDescription("Young Link Neutral-B End (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Loop(2) (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Hold(2) (Ground)"))
      .put(Pe, new SubActionDescription("Peach Down-B"))
      .put(Pk, new SubActionDescription("Pikachu Side-B End (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Side-B End (Ground)"))
      .put(Ss, new SubActionDescription("Samus Neutral-B Start (Air)"))
      .put(Sk, new SubActionDescription("Sheik Neutral-B Cancel (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Start (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Side-B Loop (Air)"))
      .build()))
    .put(0x12E, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Hit (Ground)")) //One is forward, other is behind?
      .put(Ca, new SubActionDescription("Falcon Neutral-B (Air)"))
      .put(Gn, new SubActionDescription("Ganon Neutral-B (Air)"))
      .put(Mr, new SubActionDescription("Mario Down-B (Air)"))
      .put(Dr, new SubActionDescription("Dr. Mario Down-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Side-B (Ground)"))
      .put(Fc, new SubActionDescription("Falco Side-B (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(6) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(1) (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(1) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B End (Ground)"))
      .put(Ms, new SubActionDescription("Marth Neutral-B End(2) (Air)"))
      .put(Fe, new SubActionDescription("Roy Neutral-B End(2) (Air)"))
      .put(Lk, new SubActionDescription("Link Side-B Throw (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Throw (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Loop (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B End (Ground)"))
      .put(Pe, new SubActionDescription("Peach Side-B Start (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B Start (Air)"))
      .put(Pc, new SubActionDescription("Pichu Side-B Start (Air)"))
      .put(Ss, new SubActionDescription("Samus Neutral-B (Air)"))
      .put(Sk, new SubActionDescription("Sheik Shoot Needles(?) (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Loop (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Side-B End (Air)"))
      .build()))
    .put(0x12F, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Hit(2) (Ground)")) //One is forward, other is behind?
      .put(Ca, new SubActionDescription("Falcon Side-B Start (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Side-B Start (Ground)"))
      .put(Fx, new SubActionDescription("Fox Side-B End (Ground)"))
      .put(Fc, new SubActionDescription("Falco Side-B End (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(7) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(2) (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(2) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B Start (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 1 (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 1 (Ground)"))
      .put(Lk, new SubActionDescription("Link Side-B Catch (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Catch (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B Cancel (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Start (Air)"))
      .put(Pe, new SubActionDescription("Peach Side-B Miss (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B Hold (Air)"))
      .put(Pc, new SubActionDescription("Pichu Side-B Hold (Air)"))
      .put(Ss, new SubActionDescription("Samus Homing Rocket (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Side-B Start (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Loop(2) (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Up-B Start (Ground)"))
      .build()))
    .put(0x130, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B End Forward (Ground)"))
      .put(Ca, new SubActionDescription("Falcon Side-B (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Side-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Side-B Start (Air)"))
      .put(Fc, new SubActionDescription("Falco Side-B Start (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(8) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(3) (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(3) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B Charge (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 2 Up (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 2 Up (Ground)"))
      .put(Lk, new SubActionDescription("Link Side-B Throw(Angled) (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Throw(Angled) (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Neutral-B End (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Hold (Air)"))
      .put(Pe, new SubActionDescription("Peach Side-B Hit"))
      .put(Pk, new SubActionDescription("Pikachu Side-B(2) (Air)"))
      .put(Pc, new SubActionDescription("Pichu Side-B(2) (Air)"))
      .put(Ss, new SubActionDescription("Samus Hard Rocket (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Side-B End (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B End (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Up-B (Ground)"))
      .build()))
    .put(0x131, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B End Backward (Ground)"))
      .put(Ca, new SubActionDescription("Falcon Side-B Start (Air)"))
      .put(Gn, new SubActionDescription("Ganon Side-B Start (Air)"))
      .put(Fx, new SubActionDescription("Fox Side-B (Air)"))
      .put(Fc, new SubActionDescription("Falco Side-B (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(9) (Ground)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(4) (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(4) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B(1) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 2 Side (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 2 Side (Ground)"))
      .put(Lk, new SubActionDescription("Link Side-B Throw (Air)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Throw (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Side-B (Ground)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B Hold(2) (Air)"))
      .put(Pe, new SubActionDescription("Peach Side-B Start (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Side-B End (Air)"))
      .put(Pc, new SubActionDescription("Pichu Side-B End (Air)"))
      .put(Ss, new SubActionDescription("Samus Homing Rocket(?) (Air)"))
      .put(Sk, new SubActionDescription("Sheik Side-B Loop(?) (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Start (Air)"))
      .put(Zd, new SubActionDescription("Zelda Up-B Start (Air)"))
      .build()))
    .put(0x132, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Start (Air)"))
      .put(Ca, new SubActionDescription("Falcon Side-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Side-B End (Air)"))
      .put(Fc, new SubActionDescription("Falco Side-B End (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(1) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Start (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B End (R) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B(2) (Air)")) // could be misfire
      .put(Ms, new SubActionDescription("Marth Side-B 3 Up (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Up (Ground)"))
      .put(Lk, new SubActionDescription("Link Side-B Catch (Air)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Catch (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Side-B (Air)"))
      .put(Ns, new SubActionDescription("Ness Neutral-B End (Air)"))
      .put(Pe, new SubActionDescription("Peach Side-B Miss (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Up-B Start (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Up-B Start (Ground)"))
      .put(Ss, new SubActionDescription("Samus Hard Rocket(?) (Air)"))
      .put(Sk, new SubActionDescription("Sheik Side-B Start (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Loop (Air)"))
      .put(Zd, new SubActionDescription("Zelda Up-B (Air)"))
      .build()))
    .put(0x133, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Hit (Air)")) //One is forward, other is behind?
      .put(Ca, new SubActionDescription("Falcon Up-B (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Up-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Up-B Hold (Ground)"))
      .put(Fc, new SubActionDescription("Falco Up-B Hold (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(2) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(1) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B End (L) (Ground)"))
      .put(Lg, new SubActionDescription("Luigi Side-B End (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 3 Side (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Side (Ground)"))
      .put(Lk, new SubActionDescription("Link Side-B Throw(Angled) (Air)"))
      .put(Cl, new SubActionDescription("Young Link Side-B Throw(Angled) (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Up-B Start (Ground)"))
      .put(Ns, new SubActionDescription("Ness Side-B (Ground)"))
      .put(Pe, new SubActionDescription("Peach Side-B hit wall"))
      .put(Pk, new SubActionDescription("Pikachu Up-B Start(2) (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Up-B Start(2) (Ground)"))
      .put(Ss, new SubActionDescription("Samus Screw Attack (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Side-B End (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B Loop(2) (Air)"))
      .put(Zd, new SubActionDescription("Zelda Down-B(?) (Ground)"))
      .build()))
    .put(0x134, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B Hit(2) (Air)")) //One is forward, other is behind?
      .put(Ca, new SubActionDescription("Falcon Up-B (Air)"))
      .put(Gn, new SubActionDescription("Ganon Up-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Up-B Hold (Air)"))
      .put(Fc, new SubActionDescription("Falco Up-B Hold (Air)"))
      .put(Gw, new SubActionDescription("G&W Side-B(3) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(2) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B Start (R) (Air)"))
      .put(Lg, new SubActionDescription("Luigi Up-B (Ground)"))
      .put(Ms, new SubActionDescription("Marth Side-B 3 Down (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Down (Ground)"))
      .put(Lk, new SubActionDescription("Link Up-B (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Up-B (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Up-B (Ground)"))
      .put(Ns, new SubActionDescription("Ness Side-B (Air)"))
      .put(Pe, new SubActionDescription("Peach Up-B Start (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Up-B End (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Up-B End (Ground)"))
      .put(Ss, new SubActionDescription("Samus Screw Attack (Air)"))
      .put(Sk, new SubActionDescription("Sheik Side-B Loop(?) (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Side-B End (Air)"))
      .put(Zd, new SubActionDescription("Zelda Down-B(?)(2) (Ground)"))
      .build()))
    .put(0x135, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B End Forward (Air)"))
      .put(Ca, new SubActionDescription("Falcon Up-B Hold"))
      .put(Gn, new SubActionDescription("Ganon Up-B Hold"))
      .put(Fx, new SubActionDescription("Fox Up-B"))
      .put(Fc, new SubActionDescription("Falco Up-B"))
      .put(Gw, new SubActionDescription("G&W Side-B(4) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(3) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B Start (L) (Air)"))
      .put(Lg, new SubActionDescription("Luigi Up-B (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Up (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Up (Ground)"))
      .put(Lk, new SubActionDescription("Link Up-B (Air)"))
      .put(Cl, new SubActionDescription("Young Link Up-B (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Up-B Lost"))
      .put(Ns, new SubActionDescription("Ness Up-B Start (Ground)"))
      .put(Pe, new SubActionDescription("Peach Up-B End (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Up-B Start (Air)"))
      .put(Pc, new SubActionDescription("Pichu Up-B Start (Air)"))
      .put(Ss, new SubActionDescription("Samus Down-B?(2) (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Up-B Start (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Up-B"))
      .put(Zd, new SubActionDescription("Zelda Down-B(?) (Air)"))
      .build()))
    .put(0x136, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Side-B End Backward (Air)"))
      .put(Ca, new SubActionDescription("Falcon Up-B Release"))
      .put(Gn, new SubActionDescription("Ganon Up-B Release"))
      .put(Fx, new SubActionDescription("Fox Up-B Landing"))
      .put(Fc, new SubActionDescription("Falco Up-B Landing"))
      .put(Gw, new SubActionDescription("G&W Side-B(5) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Up-B Throw(4) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(1) (Air)"))
      .put(Lg, new SubActionDescription("Luigi Down-B (Ground)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Side (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Side (Ground)"))
      .put(Lk, new SubActionDescription("Link Down-B (Ground)"))
      .put(Cl, new SubActionDescription("Young Link Down-B (Ground)"))
      .put(Mt, new SubActionDescription("Mewtwo Up-B Start (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B Hold (Ground)"))
      .put(Pe, new SubActionDescription("Peach Up-B Start (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Up-B Start(2) (Air)"))
      .put(Pc, new SubActionDescription("Pichu Up-B Start(2) (Air)"))
      .put(Ss, new SubActionDescription("Samus Down-B?(2) (Air)"))
      .put(Sk, new SubActionDescription("Sheik Up-B (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Down-B (Ground)"))
      .put(Zd, new SubActionDescription("Zelda Down-B(?)(2) (Air)"))
      .build()))
    .put(0x137, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Up-B (Ground)"))
      .put(Ca, new SubActionDescription("Falcon Down-B (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Down-B (Ground)"))
      .put(Fx, new SubActionDescription("Fox Up-B Fall"))
      .put(Fc, new SubActionDescription("Falco Up-B Fall"))
      .put(Gw, new SubActionDescription("G&W Side-B(6) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Down-B (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(2) (Air)"))
      .put(Lg, new SubActionDescription("Luigi Down-B (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Down (Ground)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Down (Ground)"))
      .put(Lk, new SubActionDescription("Link Down-B (Air)"))
      .put(Cl, new SubActionDescription("Young Link Down-B (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Up-B (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B End (Ground)"))
      .put(Pe, new SubActionDescription("Peach Up-B End (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Up-B End (Air)"))
      .put(Pc, new SubActionDescription("Pichu Up-B End (Air)"))
      .put(Sk, new SubActionDescription("Sheik Up-B Start (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Down-B (Air)"))
      .build()))
    .put(0x138, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Up-B (Air)"))
      .put(Ca, new SubActionDescription("Falcon Down-B End (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Down-B End (Ground)"))
      .put(Fx, new SubActionDescription("Fox Up-B Bound"))
      .put(Fc, new SubActionDescription("Falco Up-B Bound"))
      .put(Gw, new SubActionDescription("G&W Side-B(7) (Air)"))
      .put(Pp, new SubActionDescription("Ice Climbers Down-B (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(3) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 1 (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 1 (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Down-B (Ground)"))
      .put(Ns, new SubActionDescription("Ness Up-B"))
      .put(Pe, new SubActionDescription("Peach Neutral-B (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Start (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Start (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Up-B (Air)"))
      .put(Ys, new SubActionDescription("Yoshi Down-B Landing(?)"))
      .build()))
    .put(0x139, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Down-B (Ground)"))
      .put(Ca, new SubActionDescription("Falcon Down-B (Air)"))
      .put(Gn, new SubActionDescription("Ganon Down-B (Air)"))
      .put(Fx, new SubActionDescription("Fox Down-B Start (Ground)"))
      .put(Fc, new SubActionDescription("Falco Down-B Start (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(8) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B(4) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 2 Up (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 2 Up (Air)"))
      .put(Mt, new SubActionDescription("Mewtwo Down-B (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B Start (Air)"))
      .put(Pe, new SubActionDescription("Peach Neutral-B Counter (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Loop (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Loop (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Down-B Animation(?) (Ground)"))
      .put(Ys, new SubActionDescription("Yoshi Down-B land (Air)"))
      .build()))
    .put(0x13A, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Down-B (Air)"))
      .put(Ca, new SubActionDescription("Falcon Down-B End (Air)"))
      .put(Gn, new SubActionDescription("Ganon Down-B End (Air)"))
      .put(Fx, new SubActionDescription("Fox Down-B Loop (Ground)"))
      .put(Fc, new SubActionDescription("Falco Down-B Loop (Ground)"))
      .put(Gw, new SubActionDescription("G&W Side-B(9) (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B End (R) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 2 Side (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 2 Side (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B Hold (Air)"))
      .put(Pe, new SubActionDescription("Peach Neutral-B (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Loop(2) (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Loop(2) (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Down-B(?) (Ground)"))
      .build()))
    .put(0x13B, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Kp, new SubActionDescription("Bowser Down-B Landing"))
      .put(Ca, new SubActionDescription("Falcon Down-B End In-Air (Ground)"))
      .put(Gn, new SubActionDescription("Ganon Down-B End In-Air (Ground)"))
      .put(Fx, new SubActionDescription("Fox Down-B Hit (Ground)"))
      .put(Fc, new SubActionDescription("Falco Down-B Hit (Ground)"))
      .put(Gw, new SubActionDescription("G&W Up-B (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B End (L) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 3 Up (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Up (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B End (Air)"))
      .put(Pe, new SubActionDescription("Peach Neutral-B Counter (Air)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B End (Ground)"))
      .put(Pc, new SubActionDescription("Pichu Down-B End (Ground)"))
      .put(Sk, new SubActionDescription("Sheik Down-B Animation(?) (Air)"))
      .build()))
    .put(0x13C, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Ca, new SubActionDescription("Falcon Down-B End In-Air (Air)"))
      .put(Gn, new SubActionDescription("Ganon Down-B End In-Air (Air)"))
      .put(Fx, new SubActionDescription("Fox Down-B End (Ground)"))
      .put(Fc, new SubActionDescription("Falco Down-B End (Ground)"))
      .put(Gw, new SubActionDescription("G&W Up-B (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Neutral-B"))
      .put(Ms, new SubActionDescription("Marth Side-B 3 Side (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Side (Air)"))
      .put(Ns, new SubActionDescription("Ness Up-B(2)"))
      .put(Pe, new SubActionDescription("Peach Open Parasol"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Start (Air)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Start (Air)"))
      .put(Sk, new SubActionDescription("Sheik Down-B(?) (Air)"))
      .build()))
    .put(0x13D, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Fx, new SubActionDescription("Fox Down-B Start (Air)"))
      .put(Fc, new SubActionDescription("Falco Down-B Start (Air)"))
      .put(Gw, new SubActionDescription("G&W Down-B (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Side-B (Ground)"))
      .put(Ms, new SubActionDescription("Marth Side-B 3 Down (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 3 Down (Air)"))
      .put(Ns, new SubActionDescription("Ness DamageFall"))
      .put(Pe, new SubActionDescription("Peach Close Parasol"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Loop (Air)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Loop (Air)"))
      .build()))
    .put(0x13E, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Fx, new SubActionDescription("Fox Down-B Loop (Air)"))
      .put(Fc, new SubActionDescription("Falco Down-B Loop (Air)"))
      .put(Gw, new SubActionDescription("G&W Down-B Absorb (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Side-B (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Up (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Up (Air)"))
      .put(Ns, new SubActionDescription("Ness Down-B Start (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B Loop(2) (Air)"))
      .put(Pc, new SubActionDescription("Pichu Down-B Loop(2) (Air)"))
      .build()))
    .put(0x13F, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Start (Ground)"))
      .put(Fx, new SubActionDescription("Fox Down-B Hit (Air)"))
      .put(Fc, new SubActionDescription("Falco Down-B Hit (Air)"))
      .put(Gw, new SubActionDescription("G&W Down-B Shoot (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Up-B (L) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Side (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Side (Air)"))
      .put(Ns, new SubActionDescription("Ness Down-B Hold (Ground)"))
      .put(Pk, new SubActionDescription("Pikachu Down-B End (Air)"))
      .put(Pc, new SubActionDescription("Pichu Down-B End (Air)"))
      .build()))
    .put(0x140, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Loop (Ground)"))
      .put(Fx, new SubActionDescription("Fox Down-B End (Air)"))
      .put(Fc, new SubActionDescription("Falco Down-B End (Air)"))
      .put(Gw, new SubActionDescription("G&W Down-B (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Up-B (L) (Air)"))
      .put(Ms, new SubActionDescription("Marth Side-B 4 Down (Air)"))
      .put(Fe, new SubActionDescription("Roy Side-B 4 Down (Air)"))
      .put(Ns, new SubActionDescription("Ness Down-B Hit (Ground)"))
      .build()))
    .put(0x141, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Cancel (Ground)"))
      .put(Gw, new SubActionDescription("G&W Down-B Absorb (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Up-B (R) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Up-B (Ground)"))
      .put(Fe, new SubActionDescription("Roy Up-B (Ground)"))
      .put(Ns, new SubActionDescription("Ness Down-B End (Ground)"))
      .build()))
    .put(0x142, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B (Ground)"))
      .put(Gw, new SubActionDescription("G&W Down-B Shoot (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Up-B (R) (Air)"))
      .put(Ms, new SubActionDescription("Marth Up-B (Air)"))
      .put(Fe, new SubActionDescription("Roy Up-B (Air)"))
      .put(Ns, new SubActionDescription("Ness Down-B Start (Air)"))
      .build()))
    .put(0x143, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B(2) (Ground)"))
      .put(Pr, new SubActionDescription("Jigglypuff Down-B (L) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Down-B (Ground)"))
      .put(Fe, new SubActionDescription("Roy Down-B (Ground)"))
      .put(Ns, new SubActionDescription("Ness Down-B Hold (Air)"))
      .build()))
    .put(0x144, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Start (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Down-B (L) (Air)"))
      .put(Ms, new SubActionDescription("Marth Down-B Counter (Ground)"))
      .put(Fe, new SubActionDescription("Roy Down-B Counter (Ground)"))
      .put(Ns, new SubActionDescription("Ness Down-B Hit (Air)"))
      .build()))
    .put(0x145, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Loop (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Down-B (R) (Ground)"))
      .put(Ms, new SubActionDescription("Marth Down-B (Air)"))
      .put(Fe, new SubActionDescription("Roy Down-B (Air)"))
      .put(Ns, new SubActionDescription("Ness Down-B End (Air)"))
      .build()))
    .put(0x146, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B Cancel (Air)"))
      .put(Pr, new SubActionDescription("Jigglypuff Down-B (R) (Air)"))
      .put(Ms, new SubActionDescription("Marth Down-B Counter (Air)"))
      .put(Fe, new SubActionDescription("Roy Down-B Counter (Air)"))
      .build()))
    .put(0x147, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B (Air)"))
      .build()))
    .put(0x148, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Neutral-B(2) (Air)"))
      .build()))
    .put(0x149, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Side-B (Ground)"))
      .build()))
    .put(0x14A, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Side-B (Air)"))
      .build()))
    .put(0x14B, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Up-B (Ground)"))
      .build()))
    .put(0x14C, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Up-B (Air)"))
      .build()))
    .put(0x14D, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Down-B Start"))
      .build()))
    .put(0x14E, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Down-B Loop"))
      .build()))
    .put(0x14F, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Down-B End"))
      .build()))
    .put(0x150, new SubAction(ImmutableMap.<Character, SubActionDescription>builder()
      .put(Dk, new SubActionDescription("Donkey Kong Down-B End(2)"))
      .build()))

    .build();

  // Skipping Kirby TODO

  // TODO link/cl stuff?
  //Two more entries below SpecialAirLw, AirCatch and AirCatchHit.
  //I'd assume that's links zair.
  //There's also an entry above SpecialNStart, AttackS42.
  //Its entries look similar to down tilt, and all its hitboxes launch at angle 65
  //I'd assume this is the spike hitbox for link's down tilt.
  //Maybe consider creating a new list for misc. subactions for cases like this.


  // TODO represent l cancelling somehow, this is also related to variable "frame" per frame speeds
//  public static final Set<SubAction> L_CANCELLABLE = ImmutableSet.of(
//    LandingAirN,
//    LandingAirF,
//    LandingAirB,
//    LandingAirHi,
//    LandingAirLw);
//  public static final String UNKNOWN_ANIMATION = "[Unknown]";

  private final SubActionDescription description;
  private final Map<Character, SubActionDescription> characterToDescription;

  private SubAction() {
    // no description
    this.description = null;
    this.characterToDescription = null;
  }

  private SubAction(String description) {
    this.description = new SubActionDescription(description);
    this.characterToDescription = null;
  }

  private SubAction(String description, SubActionCategory category, ViewCategory viewCategory) {
    this.description = new SubActionDescription(description, category, viewCategory);
    this.characterToDescription = null;
  }

  private SubAction(Map<Character, SubActionDescription> characterToDescription) {
    this.description = null;
    this.characterToDescription = characterToDescription;
  }

  private SubActionDescription getDescription(Character character) {
    if (description != null) {
      return description;
    }
    if (characterToDescription != null && characterToDescription.containsKey(character)) {
      return characterToDescription.get(character);
    }
    return NO_DESCRIPTION;
  }

  public static SubActionDescription getDescription(Character character, int subActionId) {
    if (!SUBACTIONS.containsKey(subActionId)) {
      // TODO
//      System.out.println("no entry for subaction: " + subActionId);
      return NO_DESCRIPTION;
    }
    return SUBACTIONS.get(subActionId).getDescription(character);
  }

  public static final SubActionDescription NO_DESCRIPTION = new SubActionDescription("(No Description)");

  public static class SubActionDescription {
    public final String description;
    public final SubActionCategory category;
    public final ViewCategory viewCategory;

    public SubActionDescription(String description, SubActionCategory category, ViewCategory viewCategory) {
      this.description = description;
      this.category = category;
      this.viewCategory = viewCategory;
    }

    public SubActionDescription(String description) {
      this.description = description;
      this.category = SubActionCategory.Other;
      this.viewCategory = ViewCategory.ADVANCED;
    }
  }

  public static enum SubActionCategory {
    Tilt, Aerial, Smash, Throw, Item, Special, Dodge, Other, Tech, Ledge
  }
}
