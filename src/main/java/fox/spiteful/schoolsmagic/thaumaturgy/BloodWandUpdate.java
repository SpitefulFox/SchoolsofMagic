package fox.spiteful.schoolsmagic.thaumaturgy;

import WayofTime.bloodmagic.api.util.helper.NetworkHelper;
import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.blood.Blood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWand;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class BloodWandUpdate implements IWandRodOnUpdate {

    Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    @Override
    public void onUpdate(ItemStack itemstack, EntityPlayer player)
    {
        if(Config.blood && player.ticksExisted % 100 == 0)
        {
            if(!(itemstack.getItem() instanceof IWand))
                return;
            try
            {
                IWand wand = (IWand)itemstack.getItem();

                Blood.checkAndSetItemOwner(itemstack, player);

                int cost;
                /*if(wand.getCap(itemstack).getTag().equals("alchemical"))
                    cost = Config.bloodToVis - 1;
                else*/
                    cost = Config.bloodToVis;

                cost = Math.max(0, cost);

                for(int x = 0;x < primals.length;x++)
                {
                    int deficit = wand.getMaxVis(itemstack) - wand.getVis(itemstack, primals[x]);
                    Lumberjack.log(primals[x].getName() + " " + deficit);
                    if(deficit > 0)
                    {
                        deficit = Math.min(deficit, 10);
                        if(player.capabilities.isCreativeMode) {
                            wand.addVis(itemstack, primals[x], 10, true);
                            deficit = 0;
                        }
                        else {
                            int blood = Math.min(NetworkHelper.getSoulNetwork(Blood.getOwnerUUID(itemstack)).getCurrentEssence(), cost * deficit);
                            if(blood < cost * deficit)
                                blood -= blood % cost;
                            if (NetworkHelper.getSoulNetwork(Blood.getOwnerUUID(itemstack)).syphon(blood) > 0)
                                wand.addVis(itemstack, primals[x], blood / cost, true);
                            Lumberjack.log(primals[x].getName() + ": " + wand.getVis(itemstack, primals[x]) +"/" + (wand.getMaxVis(itemstack) - deficit) + ", " + deficit + ", " + (blood/cost));
                            deficit -= blood / cost;
                        }

                        if(deficit > 0 && syphonHealth(player))
                        {
                            wand.addVis(itemstack, primals[x], 1, true);
                            return;
                        }
                        else
                            return;
                    }
                }

            }
            catch(Throwable e)
            {
                Lumberjack.log(Level.ERROR, e, "Something's gone wrong.");
            }
        }
    }

    public boolean syphonHealth(EntityPlayer player)
    {
        if(player.getHealth() > 6)
        {
            player.setHealth(player.getHealth() - 3);
            return true;
        }
        else
            return false;
    }
}
