package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Config;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static Item cores;
    public static Item caps;
    public static Item colorizer;
    public static Item amuletMentalAgony;

    public static void curriculum(){

        if(Config.thaumaturgy) {
            cores = new ItemWandCores();
            GameRegistry.registerItem(cores, "WandCores");
            caps = new ItemWandCaps();
            GameRegistry.registerItem(caps, "WandCaps");
        }

        if(Config.psi){
            colorizer = new ItemExtraColorizer();
            GameRegistry.registerItem(colorizer, "Colorizer");

            if(Config.thaumaturgy){
                amuletMentalAgony = new ItemAmuletPsi();
                GameRegistry.registerItem(amuletMentalAgony, "AmuletMentalAgony");
            }
        }
    }

}
