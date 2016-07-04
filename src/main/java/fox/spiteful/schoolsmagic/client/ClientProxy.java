package fox.spiteful.schoolsmagic.client;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Proxy;
import fox.spiteful.schoolsmagic.botany.SubTileAstralBloom;
import fox.spiteful.schoolsmagic.botany.SubTileEuclidaisy;
import fox.spiteful.schoolsmagic.botany.SubTileMindLotus;
import fox.spiteful.schoolsmagic.items.ItemWandCaps;
import fox.spiteful.schoolsmagic.items.ItemWandCores;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import org.apache.logging.log4j.Level;
import vazkii.botania.api.BotaniaAPIClient;

public class ClientProxy extends Proxy {

    @Override
    public void stupidJsonBullshit(){

        if(ModItems.cores != null) {
            for(int x = 0;x < ItemWandCores.types.length;x++) {
                setCustomModelResourceLocation(ModItems.cores, x, new ModelResourceLocation("schoolsmagic:wand_rod_" + ItemWandCores.types[x], "inventory"));
            }
        }
        if(ModItems.caps != null) {
            for(int x = 0;x < ItemWandCaps.types.length;x++) {
                setCustomModelResourceLocation(ModItems.caps, x, new ModelResourceLocation("schoolsmagic:wand_cap_" + ItemWandCaps.types[x], "inventory"));
            }
        }
        setCustomModelResourceLocation(ModItems.colorizer, 0, new ModelResourceLocation("schoolsmagic:thaumic_colorizer"));
        setCustomModelResourceLocation(ModItems.colorizer, 1, new ModelResourceLocation("schoolsmagic:flame_colorizer"));
        //setCustomModelResourceLocation(ModItems.colorizer, 2, new ModelResourceLocation("schoolsmagic:fairy_colorizer"));
        setCustomModelResourceLocation(ModItems.amuletMentalAgony, 0, new ModelResourceLocation("schoolsmagic:amuletMentalAgony"));

        if(Config.botany){
            try {
                if(Config.thaumaturgy) {
                    BotaniaAPIClient.registerSubtileModel(SubTileEuclidaisy.class, new ModelResourceLocation("schoolsmagic:euclidaisy"));
                    BotaniaAPIClient.registerSubtileModel(SubTileAstralBloom.class, new ModelResourceLocation("schoolsmagic:astralbloom"));
                }
                if(Config.psi)
                    BotaniaAPIClient.registerSubtileModel(SubTileMindLotus.class, new ModelResourceLocation("schoolsmagic:mindlotus"));
            }
            catch(Throwable e){
                Lumberjack.log(Level.ERROR, e, "Schools of Magic decayed like a Daybloom.");
            }
        }
    }

    public void setCustomModelResourceLocation(Item item, int meta, ModelResourceLocation model){
        if(item == null)
            return;
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }
}
