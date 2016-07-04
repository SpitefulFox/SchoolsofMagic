package fox.spiteful.schoolsmagic.thaumaturgy;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWand;
import thaumcraft.api.wands.IWandRodOnUpdate;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotaniaWandUpdate implements IWandRodOnUpdate {

    Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player)
    {
        if(Config.botany && player.ticksExisted % 40 == 0 && checkHotbar(itemstack, player))
        {
            try
            {
                int cost;
                if(((IWand)itemstack.getItem()).getCap(itemstack).getTag().equals("manasteel")
                        ||((IWand)itemstack.getItem()).getCap(itemstack).getTag().equals("elementium"))
                    cost = Math.max((Config.manaToVis / 4) * 3, 1);
                else
                    cost = Config.manaToVis;

                cost = Math.max(0, cost);

                for(int x = 0;x < primals.length;x++)
                {
                    int deficit = ((IWand)itemstack.getItem()).getMaxVis(itemstack) - ((IWand)itemstack.getItem()).getVis(itemstack, primals[x]);
                    if(deficit > 0)
                    {
                        deficit = Math.min(deficit, 30);
                        int available = ManaItemHandler.requestMana(itemstack, player, cost * deficit, false);
                        if(available / cost > 0) {
                            available -= available % cost;
                            ManaItemHandler.requestMana(itemstack, player, available, true);
                            ((IWand) itemstack.getItem()).addVis(itemstack, primals[x], available / cost, true);
                        }
                    }
                }

            }
            catch(Throwable e)
            {
                Config.botany = false;
                Lumberjack.log(Level.ERROR, e, "Botania and Thaumcraft aren't playing nice.");
            }
        }

    }

    private boolean checkHotbar(ItemStack stack, EntityPlayer player)
    {
        for(int x = 0; x < 9; ++x)
        {
            ItemStack item = player.inventory.getStackInSlot(x);
            if(item == stack)
                return true;
        }
        return false;
    }

}
