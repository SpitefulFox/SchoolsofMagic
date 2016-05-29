package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Magic;
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
import java.util.HashMap;

public class ItemThaumicColorizer extends Item implements ICADColorizer {

    private final HashMap<Pair<EnumCADStat, Integer>, Integer> stats;

    public ItemThaumicColorizer(){
        super();
        setMaxStackSize(1);
        setMaxDamage(0);
        stats = new HashMap<Pair<EnumCADStat, Integer>, Integer>();
        setUnlocalizedName("cadColorizerThaumic");
        setCreativeTab(Magic.tab);
    }

    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack){
        float time = ClientTickHandler.total;
        float w = (float) (Math.sin(time * 0.1) * 0.5 + 0.5) * 0.5F;
        float r = 0.6F - w;
        float g = 0F;
        float b = 0.6F - w;
        return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
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

}
