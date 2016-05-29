package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Magic;
import fox.spiteful.schoolsmagic.thaumaturgy.Thaumaturgy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.wands.IWand;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;

import java.util.List;

public class ItemWandCores extends Item {

    public static final String[] types = {"blood", "blood_inert", "blood_staff", "livingwood", "livingwood_inert", "dreamwood", "dreamwood_inert",
        "dreamwood_staff", "aeternalis"};

    public ItemWandCores(){
        setHasSubtypes(true);
        setUnlocalizedName("school_wand");
        setMaxDamage(0);
        setCreativeTab(Magic.tab);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + "." + types[stack.getItemDamage() % types.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }
        list.add(getWand(Thaumaturgy.WAND_ROD_AETERNALIS, WandCap.caps.get("thaumium")));
        list.add(getWand(Thaumaturgy.WAND_ROD_LIVINGWOOD, WandCap.caps.get("thaumium")));
        list.add(getWand(Thaumaturgy.WAND_ROD_DREAMWOOD, WandCap.caps.get("thaumium")));
    }

    private ItemStack getWand(WandRod rod, WandCap cap){
        ItemStack wand = new ItemStack(ItemsTC.wand, 1, 0);
        IWand wandClass = (IWand)wand.getItem();
        wandClass.setRod(wand, rod);
        wandClass.setCap(wand, cap);
        return wand;
    }

}
