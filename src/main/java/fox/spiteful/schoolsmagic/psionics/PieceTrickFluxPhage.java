package fox.spiteful.schoolsmagic.psionics;

import net.minecraft.potion.Potion;
import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.common.spell.trick.potion.PieceTrickPotionBase;

public class PieceTrickFluxPhage extends PieceTrickPotionBase {

    public PieceTrickFluxPhage(Spell spell) {
        super(spell);
    }

    @Override
    public Potion getPotion() {
        return PotionInfectiousVisExhaust.instance;
    }
}
