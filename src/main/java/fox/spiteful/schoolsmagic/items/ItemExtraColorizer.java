package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Magic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.EnumCADStat;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.internal.TooltipHelper;
import vazkii.psi.client.core.handler.ClientTickHandler;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ItemExtraColorizer extends Item implements ICADColorizer {

    private final String[] types = new String[]{"Thaumic", "Flame"};

    public ItemExtraColorizer(){
        super();
        setMaxStackSize(1);
        setMaxDamage(0);
        setUnlocalizedName("cadColorizer");
        setCreativeTab(Magic.tab);
        setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack){
        int meta = stack.getItemDamage();
        if(meta == 0) {
            float time = ClientTickHandler.total;
            float w = (float) (Math.sin(time * 0.1) * 0.7 + 0.7) * 0.5F;
            float r = 0.6F - Math.min(w, 0.6F);
            float g = 0F;
            float b = 0.75F - w;
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
        }
        if(meta == 1){
            float time = ClientTickHandler.total;
            float w = (float) (Math.sin(time * 0.1) * 0.4 + 0.4) * 0.5F;
            float r = 1F;
            float g = 0.4F + w;
            float b = 0F;
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
        }
        /*if(meta == 2){
            float time = ClientTickHandler.total;
            if(Math.sin(time * 0.1) < 0)// && Math.sin(time * 0.2) + 0.4F > 0)
                return  Color.HSBtoRGB(time * 0.02F, 1F, 1F);
            float r = 1F;
            float g = 0.4F;
            float b = 0.8F;
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
        }*/
        return 0xFFFFFF;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return "item.cadColorizer" + types[meta % types.length];
    }

    @Override
    public int getCADStatValue(ItemStack stack, EnumCADStat stat) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int x = 0; x < types.length; x++) {
            list.add(new ItemStack(this, 1, x));
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        TooltipHelper.tooltipIfShift(tooltip, () -> {
            EnumCADComponent componentType = getComponentType(stack);
            String componentName = TooltipHelper.local(componentType.getName());
            TooltipHelper.addToTooltip(tooltip, "psimisc.componentType", componentName);
        });
    }

}
