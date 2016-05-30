package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Magic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.EnumCADStat;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.client.core.handler.ClientTickHandler;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ItemExtraColorizer extends Item implements ICADColorizer {

    private final HashMap<Pair<EnumCADStat, Integer>, Integer> stats;
    private final String[] types = new String[]{"Thaumic", "Flame"};

    public ItemExtraColorizer(){
        super();
        setMaxStackSize(1);
        setMaxDamage(0);
        stats = new HashMap<Pair<EnumCADStat, Integer>, Integer>();
        setUnlocalizedName("cadColorizer");
        setCreativeTab(Magic.tab);
        setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack){
        int meta = stack.getItemDamage();
        if(meta == 0) {
            float time = ClientTickHandler.total;
            float w = (float) (Math.sin(time * 0.1) * 0.5 + 0.5) * 0.5F;
            float r = 0.6F - w;
            float g = 0F;
            float b = 0.75F - w;
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
        }
        if(meta == 1){
            float time = ClientTickHandler.total;
            float w = (float) (Math.sin(time * 0.1) * 0.5 + 0.5) * 0.3F;
            float r = 1F;
            float g = Math.min(0.2F + w, 0.7F);
            float b = 0F;
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
        }
        return 0xFFFFFF;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return "item.cadColorizer" + types[meta % types.length];
    }

    @Override
    public int getCADStatValue(ItemStack stack, EnumCADStat stat) {
        Pair p = Pair.of(stat, stack.getItemDamage());
        if(stats.containsKey(p))
            return stats.get(p);
        return 0;
    }

    @Override
    public EnumCADComponent getComponentType(ItemStack stack) {
        return EnumCADComponent.DYE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }
    }

}
