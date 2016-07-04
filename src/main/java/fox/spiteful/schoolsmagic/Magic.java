package fox.spiteful.schoolsmagic;

import fox.spiteful.schoolsmagic.botany.Botany;
import fox.spiteful.schoolsmagic.items.ModItems;
import fox.spiteful.schoolsmagic.psionics.Psionics;
import fox.spiteful.schoolsmagic.thaumaturgy.Thaumaturgy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = "SchoolsMagic",
        name = "Schools of Magic",
        version = "1.0",
        dependencies = "after:Thaumcraft;after:BloodMagic;after:Botania;after:ProjectE"
)
public class Magic {

    @Mod.Instance("SchoolsMagic")
    public static Magic instance;
    @SidedProxy(clientSide = "fox.spiteful.schoolsmagic.client.ClientProxy", serverSide = "fox.spiteful.schoolsmagic.Proxy")
    public static Proxy proxy;

    public static CreativeTabs tab = new CreativeTabs("schoolsmagic"){
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem(){
            return Item.getItemFromBlock(Blocks.enchanting_table);
        }
    };

    @Mod.EventHandler
    public void bell(FMLPreInitializationEvent event)
    {
        instance = this;
        Config.configurate(event.getSuggestedConfigurationFile());
        ModItems.curriculum();
        if(Config.thaumaturgy)
            Thaumaturgy.miracles();
        if(Config.psi)
            Psionics.oneechan();
        if(Config.botany)
            Botany.hippyFlowerPower();

        proxy.stupidJsonBullshit();
    }

    @Mod.EventHandler
    public void book(FMLInitializationEvent event)
    {
        if(Config.botany)
            Botany.lexify();
    }

    @Mod.EventHandler
    public void candle(FMLPostInitializationEvent event)
    {
        if(Config.thaumaturgy)
            Thaumaturgy.research();
    }

    public static Item getItem(String mod, String item) throws ItemNotFoundException {
        Item target = GameRegistry.findItem(mod, item);
        if(target == null)
            throw new ItemNotFoundException(mod, item);
        return target;
    }
}
