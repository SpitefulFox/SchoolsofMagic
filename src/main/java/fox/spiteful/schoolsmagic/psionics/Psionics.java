package fox.spiteful.schoolsmagic.psionics;

import fox.spiteful.schoolsmagic.Config;
import vazkii.psi.common.spell.base.ModSpellPieces;

public class Psionics {

    public static void oneechan(){
        if(Config.thaumaturgy){
            ModSpellPieces.register(PieceTrickDeathGaze.class, "trickDeathGaze", "negativeEffects");
            ModSpellPieces.register(PieceTrickFluxPhage.class, "trickFluxPhage", "negativeEffects");
            ModSpellPieces.register(PieceTrickSiphonAura.class, "trickSiphonAura", "flowControl");
        }
    }

}
