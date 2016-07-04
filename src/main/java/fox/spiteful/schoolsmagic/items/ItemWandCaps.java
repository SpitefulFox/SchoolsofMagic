package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Magic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemWandCaps extends Item {

    public static final String[] types = {"manasteel", "manasteel_inert", "elementium", "elementium_inert", "ebony", "ivory"};

    public ItemWandCaps(){
        setHasSubtypes(true);
        setUnlocalizedName("school_wand_cap");
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
        /*for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }*/
        if(Config.botany){
            list.add(new ItemStack(this, 1, 0));
            list.add(new ItemStack(this, 1, 1));
            list.add(new ItemStack(this, 1, 2));
            list.add(new ItemStack(this, 1, 3));
        }
        if(Config.psi){
            list.add(new ItemStack(this, 1, 4));
            list.add(new ItemStack(this, 1, 5));
        }
    }

}
