package fox.spiteful.schoolsmagic.botany;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.ItemNotFoundException;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Magic;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.Level;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;

public class Botany {

    public static void hippyFlowerPower(){
        try {

            if(Config.thaumaturgy) {

                BotaniaAPI.registerSubTile("euclidaisy", SubTileEuclidaisy.class);
                BotaniaAPI.registerSubTileSignature(SubTileEuclidaisy.class, new MagicSignature("euclidaisy"));
                BotaniaAPI.addSubTileToCreativeMenu("euclidaisy");

                BotaniaAPI.registerSubTile("astralbloom", SubTileAstralBloom.class);
                BotaniaAPI.registerSubTileSignature(SubTileAstralBloom.class, new MagicSignature("astralbloom"));
                BotaniaAPI.addSubTileToCreativeMenu("astralbloom");
            }

            if(Config.psi){
                BotaniaAPI.registerSubTile("mindlotus", SubTileMindLotus.class);
                BotaniaAPI.registerSubTileSignature(SubTileMindLotus.class, new MagicSignature("mindlotus"));
                BotaniaAPI.addSubTileToCreativeMenu("mindlotus");
            }
        }
        catch(Throwable e){
            Lumberjack.log(Level.ERROR, e, "Schools of Magic decayed like a Daybloom.");
        }
    }

    public static void lexify(){
        try {

            if(Config.thaumaturgy) {

                SubTileEuclidaisy.lexicon = new MagicLexicon("euclidaisy", BotaniaAPI.categoryFunctionalFlowers, "Thaumcraft");
                SubTileEuclidaisy.lexicon.addPage(BotaniaAPI.internalHandler.textPage("schools.lexicon.euclidaisy.0"));
                ItemStack euclidaisy = getFlower("euclidaisy");
                SubTileEuclidaisy.lexicon.setIcon(euclidaisy);

                SubTileAstralBloom.lexicon = new MagicLexicon("astralbloom", BotaniaAPI.categoryFunctionalFlowers, "Thaumcraft");
                SubTileAstralBloom.lexicon.addPage(BotaniaAPI.internalHandler.textPage("schools.lexicon.astralbloom.0"));
                ItemStack astralbloom = getFlower("astralbloom");
                SubTileAstralBloom.lexicon.setIcon(astralbloom);

            }

            if(Config.psi){
                ItemStack lotus = getFlower("mindlotus");
                Item psiResource = Magic.getItem("psi", "material");
                //Item rune = Magic.getItem("Botania", "rune");
                Item resource = Magic.getItem("Botania", "manaResource");
                Item petal = Magic.getItem("Botania", "petal");
                RecipeRuneAltar lotus_recipe = BotaniaAPI.registerRuneAltarRecipe(lotus, 600, new ItemStack(petal, 1, 3), new ItemStack(petal, 1, 11), new ItemStack(Items.wheat_seeds, 1), new ItemStack(psiResource, 1, 2), new ItemStack(resource, 1, 1));

                SubTileMindLotus.lexicon = new MagicLexicon("mindlotus", BotaniaAPI.categoryGenerationFlowers, "Psi");
                SubTileMindLotus.lexicon.addPage(BotaniaAPI.internalHandler.textPage("schools.lexicon.mindlotus.0"));
                SubTileMindLotus.lexicon.addPage(BotaniaAPI.internalHandler.runeRecipePage("schools.lexicon.mindlotus.1", lotus_recipe));
                SubTileMindLotus.lexicon.setIcon(lotus);
            }
        }
        catch(Throwable e){
            Lumberjack.log(Level.ERROR, e, "Schools of Magic decayed like a Daybloom.");
        }
    }

    public static ItemStack getFlower(String type) throws ItemNotFoundException {
        Item specialFlower = Magic.getItem("Botania", "specialFlower");
        ItemStack flower = new ItemStack(specialFlower, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", type);
        flower.setTagCompound(tag);
        return flower;
    }

}
