package fox.spiteful.schoolsmagic.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Magic;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.internal.EnumWarpType;
import thaumcraft.api.items.IRepairable;
import thaumcraft.api.items.IWarpingGear;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.common.Thaumcraft;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.util.List;

@Optional.Interface(iface = "vazkii.botania.api.item.ICosmeticAttachable", modid = "Botania")
public class ItemAmuletPsi extends Item implements IBauble, IRepairable, IWarpingGear, ICosmeticAttachable {

    public ItemAmuletPsi(){
        super();
        setMaxDamage(60);
        setMaxStackSize(1);
        setUnlocalizedName("amuletMentalAgony");
        setCreativeTab(Magic.tab);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public BaubleType getBaubleType(ItemStack itemstack){
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player){}
    public void onEquipped(ItemStack itemstack, EntityLivingBase player){}
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player){}

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player){
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player){
        return true;
    }

    @Override
    public ItemStack getCosmeticItem(ItemStack stack) {
        if(stack == null || stack.getTagCompound() == null)
            return null;
        if(!stack.getTagCompound().hasKey("cosmeticItem"))
            return null;
        NBTTagCompound cosmetic = stack.getTagCompound().getCompoundTag("cosmeticItem");
        return ItemStack.loadItemStackFromNBT(cosmetic);
    }

    @Override
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        if(stack == null)
            return;
        NBTTagCompound cmp = new NBTTagCompound();
        if(cosmetic != null)
            cosmetic.writeToNBT(cmp);
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null){
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setTag("cosmeticItem", cmp);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        if(Config.botany && GuiScreen.isShiftKeyDown() && getCosmeticItem(stack) != null)
            list.add(String.format(StatCollector.translateToLocal("botaniamisc.hasCosmetic"), getCosmeticItem(stack).getDisplayName()).replaceAll("&", "\u00a7"));

    }

    @SubscribeEvent
    public void onBacklash(LivingHurtEvent event){
        if(event.entityLiving instanceof EntityPlayer && event.source == PlayerDataHandler.damageSourceOverload){
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack amulet = BaublesApi.getBaubles(player).getStackInSlot(0);
            if(amulet != null && amulet.getItem() == ModItems.amuletMentalAgony) {
                int absorbed = Math.min((int) event.ammount - 1, amulet.getMaxDamage() - amulet.getItemDamage());
                event.ammount -= absorbed;
                amulet.damageItem(absorbed, player);
                if (amulet.getItemDamage() >= amulet.getMaxDamage()) {
                    BaublesApi.getBaubles(player).removeStackFromSlot(0);
                    ResearchHelper.addWarpToPlayer(player, 1, EnumWarpType.NORMAL);
                }
            }
        }
    }

    public int getWarp(ItemStack itemStack, EntityPlayer entityPlayer){
        return 1;
    }

}
