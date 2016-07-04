package fox.spiteful.schoolsmagic.psionics;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Magic;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Level;
import thaumcraft.api.items.ItemsTC;
import vazkii.psi.api.PsiAPI;

public class Psionics {

    public static void oneechan(){

        try {

            Item psiResource = Magic.getItem("psi", "material");
            CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.colorizer, 1, 1), " P ", "GBG", " I ",
                    'P', new ItemStack(psiResource, 1, 0),
                    'B', new ItemStack(Items.blaze_powder), 'G', "blockGlass", 'I', new ItemStack(Items.iron_ingot)));

            if(Config.thaumaturgy) {
                try {
                    PsiAPI.registerSpellPieceAndTexture("trickWarpWard", PieceTrickWarpWard.class);
                    PsiAPI.registerSpellPieceAndTexture("trickDeathGaze", PieceTrickDeathGaze.class);
                    PsiAPI.registerSpellPieceAndTexture("trickFluxFlu", PieceTrickFluxFlu.class);
                    PsiAPI.registerSpellPieceAndTexture("trickFluxTaint", PieceTrickFluxTaint.class);
                    PsiAPI.registerSpellPieceAndTexture("trickMoveNode", PieceTrickMoveNode.class);
                    PsiAPI.setGroupRequirements("thaumic", 25, "eidosReversal");
                    PsiAPI.addPieceToGroup(PieceTrickMoveNode.class, "thaumic", true);
                    PsiAPI.addPieceToGroup(PieceTrickWarpWard.class, "thaumic", false);
                    PsiAPI.addPieceToGroup(PieceTrickDeathGaze.class, "thaumic", false);
                    PsiAPI.addPieceToGroup(PieceTrickFluxFlu.class, "thaumic", false);
                    PsiAPI.addPieceToGroup(PieceTrickFluxTaint.class, "thaumic", false);

                    CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.colorizer, 1, 0), " P ", "GSG", " I ",
                            'P', new ItemStack(psiResource, 1, 0),
                            'S', new ItemStack(ItemsTC.salisMundus), 'G', "blockGlass", 'I', new ItemStack(Items.iron_ingot)));

                } catch (Throwable e) {
                    Lumberjack.log(Level.INFO, e, "Schools of Magic gained too much Warp.");
                }
            }
            if(Config.botany) {
                try {
                    /*Item manaResource = Magic.getItem("botania", "manaResource");

                    CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.colorizer, 1, 2), " P ", "GSG", " I ",
                            'P', new ItemStack(psiResource, 1, 0),
                            'S', new ItemStack(manaResource, 1, 8), 'G', "blockGlass", 'I', new ItemStack(Items.iron_ingot)));*/

                    PsiAPI.registerSpellPieceAndTexture("trickGenMana", PieceTrickGenMana.class);
                    PsiAPI.registerSpellPieceAndTexture("trickSoulCross", PieceTrickSoulCross.class);
                    PsiAPI.registerSpellPieceAndTexture("trickAllure", PieceTrickAllure.class);
                    PsiAPI.registerSpellPieceAndTexture("trickEmptiness", PieceTrickEmptiness.class);
                    PsiAPI.setGroupRequirements("botany", 25, "eidosReversal");
                    PsiAPI.addPieceToGroup(PieceTrickGenMana.class, "botany", true);
                    PsiAPI.addPieceToGroup(PieceTrickSoulCross.class, "botany", false);
                    PsiAPI.addPieceToGroup(PieceTrickAllure.class, "botany", false);
                    PsiAPI.addPieceToGroup(PieceTrickEmptiness.class, "botany", false);

                } catch (Throwable e) {
                    Lumberjack.log(Level.INFO, e, "Schools of Magic decayed passively.");
                }
            }
        } catch (Throwable e) {
            Lumberjack.log(Level.INFO, e, "Schools of Magic was just a weed.");
            Config.psi = false;
        }
    }

}
