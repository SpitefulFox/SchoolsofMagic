package fox.spiteful.schoolsmagic.blood;

import WayofTime.bloodmagic.api.event.ItemBindEvent;
import WayofTime.bloodmagic.api.iface.IBindable;
import WayofTime.bloodmagic.api.util.helper.BindableHelper;
import WayofTime.bloodmagic.api.util.helper.NBTHelper;
import WayofTime.bloodmagic.api.util.helper.PlayerHelper;
import com.google.common.base.Strings;
import fox.spiteful.schoolsmagic.Lumberjack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.wands.IWand;

public class Blood {

    public static void checkAndSetItemOwner(ItemStack stack, EntityPlayer player){
        if (stack != null)
        {
            stack = NBTHelper.checkNBT(stack);
            if (Strings.isNullOrEmpty(getOwnerUUID(stack)))
            {
                String uuid = PlayerHelper.getUUIDFromPlayer(player).toString();
                ItemBindEvent toPost = new ItemBindEvent(player, uuid, stack);
                if (MinecraftForge.EVENT_BUS.post(toPost) || toPost.getResult() == Event.Result.DENY)
                    return;
                setItemOwnerUUID(stack, uuid);
                setItemOwnerName(stack, player.getDisplayNameString());

            } else if (getOwnerUUID(stack).equals(PlayerHelper.getUUIDFromPlayer(player).toString()) && !getOwnerName(stack).equals(player.getDisplayNameString()))
                setItemOwnerName(stack, player.getDisplayNameString());
        }
    }

    private static void setItemOwnerUUID(ItemStack stack, String ownerUUID)
    {
        stack = NBTHelper.checkNBT(stack);

        stack.getTagCompound().setString("ownerUUID", ownerUUID);
    }

    private static void setItemOwnerName(ItemStack stack, String ownerName)
    {
        stack = NBTHelper.checkNBT(stack);

        stack.getTagCompound().setString("ownerNAME", ownerName);
    }

    public static String getOwnerUUID(ItemStack stack)
    {
        stack = NBTHelper.checkNBT(stack);

        return stack.getTagCompound().getString("ownerUUID");
    }

    public static String getOwnerName(ItemStack stack)
    {
        stack = NBTHelper.checkNBT(stack);

        return PlayerHelper.getUsernameFromStack(stack);
    }

    public static class OwnerTooltip {

        @SubscribeEvent
        public void onTooltip(ItemTooltipEvent event) {
            if (event.itemStack.getItem() instanceof IWand) {
                IWand wand = (IWand)event.itemStack.getItem();
                if (wand.getRod(event.itemStack).getTag().startsWith("blood") && !getOwnerName(event.itemStack).equals("")) {
                    event.toolTip.add(StatCollector.translateToLocalFormatted("tooltip.BloodMagic.currentOwner", getOwnerName(event.itemStack)));
                }
                else if(wand.getRod(event.itemStack).getTag().startsWith("blood"))
                    Lumberjack.log("Wand has no owner?!");
            }
        }
    }
}
