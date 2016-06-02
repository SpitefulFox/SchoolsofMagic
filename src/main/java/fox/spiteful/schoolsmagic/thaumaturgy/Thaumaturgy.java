package fox.spiteful.schoolsmagic.thaumaturgy;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Magic;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.WandRod;

public class Thaumaturgy {

    public static WandRod WAND_ROD_LIVINGWOOD;
    public static WandRod WAND_ROD_DREAMWOOD;
    //public static WandRod WAND_ROD_AETERNALIS;

    public static WandRod STAFF_ROD_DREAMWOOD;

    public static void miracles(){
        WAND_ROD_LIVINGWOOD = new WandRod("livingwood", 500, new ItemStack(ModItems.cores, 1, 3), 9, new ResourceLocation("schoolsmagic", "wands/rod_livingwood_mat"));
        WAND_ROD_DREAMWOOD = new WandRod("dreamwood", 500, new ItemStack(ModItems.cores, 1, 5), 9, new ResourceLocation("schoolsmagic", "wands/rod_dreamwood_mat"));
        //WAND_ROD_AETERNALIS = new WandRod("aeternalis", 500, new ItemStack(ModItems.cores, 1, 8), 9, new ResourceLocation("schoolsmagic", "wands/rod_aeternalis_mat"));

    }

    public static void research(){

        try {
            ResearchCategories.registerCategory("MIXED", null, new ResourceLocation("schoolsmagic", "textures/misc/forbidden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
            //new MixedResearchItem("ROD_aeternalis", "THAUMATURGY").setAutoUnlock().registerResearchItem();

            if (Config.psi) {
                Item psiResource = Magic.getItem("psi", "material");
                InfusionRecipe amulet = ThaumcraftApi.addInfusionCraftingRecipe("AMULETPSI", new ItemStack(ModItems.amuletMentalAgony, 1, 0), 5, new AspectList().add(Aspect.MIND, 8).add(Aspect.LIFE, 4).add(Aspect.PROTECT, 8), new ItemStack(ItemsTC.baubles, 1, 0), new Object[]{new ItemStack(ItemsTC.eldritchEye), new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(psiResource, 1, 2), new ItemStack(psiResource, 1, 0), new ItemStack(psiResource, 1, 0)});
                new MixedResearchItem("AMULETPSI", "MIXED", "[PSI]", new AspectList().add(Aspect.MIND, 8).add(Aspect.PROTECT, 4).add(Aspect.ENERGY, 4), 0, 0, 2, new ItemStack(ModItems.amuletMentalAgony)).setPages(new ResearchPage("schools.research_page.AMULETPSI.1"), new ResearchPage(amulet)).setParents("ELDRITCHMINOR").registerResearchItem();
            }
        }
        catch(Throwable e){
            Lumberjack.log(Level.INFO, e, "Schools of Magic slacked off instead of doing research.");
        }
    }

}
