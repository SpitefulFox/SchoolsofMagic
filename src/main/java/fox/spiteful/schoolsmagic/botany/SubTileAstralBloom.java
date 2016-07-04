package fox.spiteful.schoolsmagic.botany;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.common.Thaumcraft;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileAstralBloom extends SubTileFunctional {
    private static final int cost = 50000;
    public static LexiconEntry lexicon;

    @Override
    public void onUpdate(){
        super.onUpdate();

        if(redstoneSignal > 0)
            return;

        if(mana >= cost && !supertile.getWorld().isRemote && this.ticksExisted % 800 == 0) {
            if(AuraHelper.drainAura(supertile.getWorld(), supertile.getPos(), Aspect.FLUX, 5)){
                if(Thaumcraft.proxy.getFX() != null)
                    Thaumcraft.proxy.getFX().drawPollutionParticles(supertile.getPos());
                mana -= cost;
                sync();
            }
        }
    }

    @Override
    public int getColor(){
        return 0xA9C6C1;
    }

    @Override
    public int getMaxMana() {
        return cost;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public LexiconEntry getEntry(){
        return lexicon;
    }

}