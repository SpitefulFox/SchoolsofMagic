package fox.spiteful.schoolsmagic.botany;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemGenericEssentiaContainer;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileEuclidaisy extends SubTileFunctional {
    private static final int cost = 5000;
    public static LexiconEntry lexicon;

    @Override
    public void onUpdate(){
        super.onUpdate();

        if(redstoneSignal > 0)
            return;

        if(mana >= cost && !supertile.getWorld().isRemote && this.ticksExisted % 400 == 0) {
            if(Thaumcraft.proxy.getFX() != null)
                Thaumcraft.proxy.getFX().burst((double)supertile.getPos().getX() + 0.5D, (double)supertile.getPos().getY() + 0.5D, (double)supertile.getPos().getZ() + 0.5D, 1.0F);
            AspectList aspect;
            if(supertile.getWorld().rand.nextInt(10) < 4)
                aspect = (new AspectList()).add(Aspect.AURA, 2);
            else {
                Aspect[] aspects = Aspect.aspects.values().toArray(new Aspect[0]);
                aspect = (new AspectList()).add(aspects[supertile.getWorld().rand.nextInt(aspects.length)], 2);
            }
            ItemStack ess = new ItemStack(ItemsTC.wispyEssence);
            ((ItemGenericEssentiaContainer)ess.getItem()).setAspects(ess, aspect);
            dropItem(supertile.getWorld(), supertile.getPos().getX(), supertile.getPos().getY(), supertile.getPos().getZ(), ess);
            mana -= cost;
            sync();
        }
    }

    public void dropItem(World world, int x, int y, int z, ItemStack item){
        float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
        entityitem.setDefaultPickupDelay();
        world.spawnEntityInWorld(entityitem);
    }

    @Override
    public int getColor(){
        return 0xFF8CFF;
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