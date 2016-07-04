package fox.spiteful.schoolsmagic;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Level;

import java.io.File;

public class Config {

    public static boolean thaumaturgy;
    public static boolean botany;
    //public static boolean alchemy;
    public static boolean psi;

    public static int manaToVis = 80;
    //public static int emcToVis = 16;

    public static boolean tagResearch;

    public static void configurate(File targ) {

        Configuration conf = new Configuration(targ);
        try {
            conf.load();

            tagResearch = conf.get("general", "Tag Research", true, "Should research subtitles include a [SoM] tag").getBoolean(true);

            thaumaturgy = conf.get("compatibility", "Thaumcraft", true, "Disable to stop all Thaumcraft integration").getBoolean(true) && Loader.isModLoaded("Thaumcraft");
            botany = conf.get("compatibility", "Botania", true, "Disable to stop all Botania integration").getBoolean(true) && Loader.isModLoaded("Botania");
            //alchemy = conf.get("compatibility", "Project E", true, "Disable to stop all Project E integration").getBoolean(true) && Loader.isModLoaded("ProjectE");
            psi = conf.get("compatibility", "Psi", true, "Disable to stop all Psi integration").getBoolean(true) && Loader.isModLoaded("Psi");

            manaToVis = conf.get("conversion rates", "Mana to Vis", manaToVis, "How much Mana 1 Vis is worth").getInt(manaToVis);
            //emcToVis = conf.get("conversion rates", "EMC to Vis", emcToVis, "How much EMC 1 Vis is worth").getInt(emcToVis);

        } catch (Exception e) {
            Lumberjack.log(Level.ERROR, e, "Schools of Magic had a problem loading its configuration.");
        } finally {
            conf.save();
        }
    }

}
