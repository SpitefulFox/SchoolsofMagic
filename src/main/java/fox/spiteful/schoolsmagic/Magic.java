package fox.spiteful.schoolsmagic;

import fox.spiteful.schoolsmagic.items.ModItems;
import fox.spiteful.schoolsmagic.psionics.Psionics;
import fox.spiteful.schoolsmagic.thaumaturgy.Thaumaturgy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

        proxy.stupidJsonBullshit();
    }

    @Mod.EventHandler
    public void book(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void candle(FMLPostInitializationEvent event)
    {

    }


}
