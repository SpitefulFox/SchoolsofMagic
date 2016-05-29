package fox.spiteful.schoolsmagic.items;

import fox.spiteful.schoolsmagic.Config;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static Item cores;
    public static Item colorizer;
    public static Item amuletMentalAgony;

    public static void curriculum(){

        if(Config.thaumaturgy) {
            cores = new ItemWandCores();
            GameRegistry.registerItem(cores, "WandCores");
        }

        if(Config.psi){
            if(Config.thaumaturgy){
                colorizer = new ItemThaumicColorizer();
                GameRegistry.registerItem(colorizer, "ThaumicColorizer");
                amuletMentalAgony = new ItemAmuletPsi();
                GameRegistry.registerItem(amuletMentalAgony, "AmuletMentalAgony");
            }
        }
    }

}
