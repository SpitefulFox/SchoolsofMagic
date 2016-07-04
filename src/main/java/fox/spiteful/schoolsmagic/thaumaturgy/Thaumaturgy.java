package fox.spiteful.schoolsmagic.thaumaturgy;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Lumberjack;
import fox.spiteful.schoolsmagic.Magic;
import fox.spiteful.schoolsmagic.botany.Botany;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import vazkii.botania.api.BotaniaAPI;

public class Thaumaturgy {

    public static WandRod WAND_ROD_LIVINGWOOD;
    public static WandRod WAND_ROD_DREAMWOOD;
    //public static WandRod WAND_ROD_AETERNALIS;

    public static WandCap WAND_CAP_MANASTEEL;
    public static WandCap WAND_CAP_ELEMENTIUM;
    public static WandCap WAND_CAP_EBONY;
    public static WandCap WAND_CAP_IVORY;

    public static WandRod STAFF_ROD_DREAMWOOD;

    public static void miracles(){
        WAND_ROD_LIVINGWOOD = new WandRod("livingwood", 500, new ItemStack(ModItems.cores, 1, 3), 9, new BotaniaWandUpdate(), new ResourceLocation("schoolsmagic", "wands/rod_livingwood_mat"));
        WAND_ROD_DREAMWOOD = new WandRod("dreamwood", 500, new ItemStack(ModItems.cores, 1, 5), 9, new BotaniaWandUpdate(), new ResourceLocation("schoolsmagic", "wands/rod_dreamwood_mat"));
        STAFF_ROD_DREAMWOOD = new WandRod("dreamwood_staff", 750, new ItemStack(ModItems.cores, 1, 7), 24, new BotaniaWandUpdate(), new ResourceLocation("schoolsmagic", "wands/rod_dreamwood_mat"));
        STAFF_ROD_DREAMWOOD.setStaff(true);
        STAFF_ROD_DREAMWOOD.setPotencyBonus(true);
        //WAND_ROD_AETERNALIS = new WandRod("aeternalis", 500, new ItemStack(ModItems.cores, 1, 8), 9, new ResourceLocation("schoolsmagic", "wands/rod_aeternalis_mat"));
        WAND_CAP_MANASTEEL = new WandCap("manasteel", 0.9F, 0, new ItemStack(ModItems.caps, 1, 0), 6, new ResourceLocation("schoolsmagic", "wands/cap_manasteel_mat"));
        WAND_CAP_ELEMENTIUM = new WandCap("elementium", 0.7F, 0, new ItemStack(ModItems.caps, 1, 2), 9, new ResourceLocation("schoolsmagic", "wands/cap_elementium_mat"));
        WAND_CAP_EBONY = new WandCap("ebony", 0.95F, 2, new ItemStack(ModItems.caps, 1, 4), 7, new ResourceLocation("schoolsmagic", "wands/cap_ebony_mat"));
        WAND_CAP_IVORY = new WandCap("ivory", 0.85F, 0, new ItemStack(ModItems.caps, 1, 5), 7, new ResourceLocation("schoolsmagic", "wands/cap_ivory_mat"));
    }

    public static void research(){

        try {
            ResearchCategories.registerCategory("MIXED", null, new ResourceLocation("schoolsmagic", "textures/misc/forbidden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
            //new MixedResearchItem("ROD_aeternalis", "THAUMATURGY").setAutoUnlock().registerResearchItem();

            if (Config.psi) {
                try {
                    Item psiResource = Magic.getItem("psi", "material");
                    InfusionRecipe amulet = ThaumcraftApi.addInfusionCraftingRecipe("AMULETPSI", new ItemStack(ModItems.amuletMentalAgony, 1, 0), 5, new AspectList().add(Aspect.MIND, 8).add(Aspect.LIFE, 4).add(Aspect.PROTECT, 8), new ItemStack(ItemsTC.baubles, 1, 0), new Object[]{new ItemStack(ItemsTC.eldritchEye), new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(psiResource, 1, 2), new ItemStack(psiResource, 1, 0), new ItemStack(psiResource, 1, 0)});
                    new MixedResearchItem("AMULETPSI", "MIXED", "[PSI]", new AspectList().add(Aspect.MIND, 8).add(Aspect.PROTECT, 4).add(Aspect.ENERGY, 4), 0, 0, 2, new ItemStack(ModItems.amuletMentalAgony)).setPages(new ResearchPage("schools.research_page.AMULETPSI.1"), new ResearchPage(amulet)).setParents("ELDRITCHMINOR", "INFUSION").registerResearchItem();

                    IArcaneRecipe ebony_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_ebony", new ItemStack(ModItems.caps, 1, 4), (new AspectList()).add(Aspect.ENTROPY, 27).add(Aspect.FIRE, 27).add(Aspect.WATER, 27).add(Aspect.AIR, 27).add(Aspect.EARTH, 27).add(Aspect.ORDER, 27), new Object[]{"NNN", "NIN", Character.valueOf('N'), new ItemStack(psiResource, 1, 5), Character.valueOf('I'), new ItemStack(psiResource, 1, 1)});
                    IArcaneRecipe ivory_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_ivory", new ItemStack(ModItems.caps, 1, 5), (new AspectList()).add(Aspect.ENTROPY, 27).add(Aspect.FIRE, 27).add(Aspect.WATER, 27).add(Aspect.AIR, 27).add(Aspect.EARTH, 27).add(Aspect.ORDER, 27), new Object[]{"NNN", "NIN", Character.valueOf('N'), new ItemStack(psiResource, 1, 6), Character.valueOf('I'), new ItemStack(psiResource, 1, 1)});
                    new MixedResearchItem("CAP_ebony", "MIXED").registerResearchItem();
                    new MixedResearchItem("CAP_ivory", "MIXED").registerResearchItem();
                    new MixedResearchItem("TAOCAPS", "MIXED", "[PSI]", new AspectList().add(Aspect.MIND, 8).add(Aspect.LIGHT, 4).add(Aspect.DARKNESS, 4), 0, 2, 2, new ResourceLocation("schoolsmagic", "textures/misc/taocap.png")).setPages(new ResearchPage("schools.research_page.TAOCAPS.1"), new ResearchPage(ebony_cap), new ResearchPage(ivory_cap)).setParents("CAP_thaumium").setSiblings("CAP_ebony", "CAP_ivory").setSecondary().registerResearchItem();
                }
                catch(Throwable e){
                    Lumberjack.log(Level.INFO, e, "Schools of Magic was just a weed.");
                }
            }

            if(Config.botany){
                try {
                    Block livingLog = GameRegistry.findBlock("Botania", "livingwood");
                    Block dreamwood = GameRegistry.findBlock("Botania", "dreamwood");
                    Item rune = Magic.getItem("Botania", "rune");
                    Item resource = Magic.getItem("Botania", "manaResource");
                    Item flower = Magic.getItem("Botania", "flower");
                    Item petal = Magic.getItem("Botania", "petal");
                    
                    InfusionRecipe livingwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_livingwood", new ItemStack(ModItems.cores, 1, 4), 3, (new AspectList()).add(Aspect.ENERGY, 16).add(Aspect.PLANT, 32).add(Aspect.LIFE, 16), new ItemStack(livingLog), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
                    (new MixedResearchItem("ROD_livingwood", "MIXED", "[B]", (new AspectList()).add(Aspect.EARTH, 8).add(Aspect.ENERGY, 4).add(Aspect.PLANT, 6), 4, 0, 3, new ItemStack(ModItems.cores, 1, 3))).setPages(new ResearchPage[]{new ResearchPage("schools.research_page.ROD_livingwood.1"), new ResearchPage(livingwood_rod)}).setParents(new String[]{"ROD_silverwood", "INFUSION"}).registerResearchItem();
                    ThaumcraftApi.addWarpToResearch("ROD_livingwood", 2);
                    BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.cores, 1, 3), new ItemStack(ModItems.cores, 1, 4), 10000);

                    IArcaneRecipe manasteel_cap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_manasteel", new ItemStack(ModItems.caps, 1, 1), (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.AIR, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6), new Object[]{"NNN", "N N", Character.valueOf('N'), "nuggetManasteel"});
                    (new MixedResearchItem("CAP_manasteel", "MIXED", "[B]", (new AspectList()).add(Aspect.AURA, 6).add(Aspect.ENERGY, 4).add(Aspect.METAL, 4), 5, -2, 2, new ItemStack(ModItems.caps, 1, 0))).setPages(new ResearchPage[]{new ResearchPage("schools.research_page.CAP_manasteel.1"), new ResearchPage(manasteel_cap)}).setParents(new String[]{"ROD_livingwood"}).setSecondary().registerResearchItem();
                    BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.caps, 1, 0), new ItemStack(ModItems.caps, 1, 1), 1000);

                    InfusionRecipe dreamwood_rod = ThaumcraftApi.addInfusionCraftingRecipe("ROD_dreamwood", new ItemStack(ModItems.cores, 1, 6), 3, (new AspectList()).add(Aspect.ENERGY, 16).add(Aspect.PLANT, 32).add(Aspect.LIFE, 16), new ItemStack(dreamwood), new ItemStack[]{new ItemStack(resource, 1, 2), new ItemStack(rune, 1, 8), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3), new ItemStack(rune, 1, 4), new ItemStack(rune, 1, 5), new ItemStack(rune, 1, 6), new ItemStack(rune, 1, 7)});
                    IArcaneRecipe dreamwood_staff = ThaumcraftApi.addArcaneCraftingRecipe("ROD_dreamwood_staff", new ItemStack(ModItems.cores, 1, 7), (new AspectList()).add(Aspect.ENTROPY, 26).add(Aspect.FIRE, 26).add(Aspect.WATER, 26).add(Aspect.AIR, 26).add(Aspect.EARTH, 26).add(Aspect.ORDER, 26), new Object[]{"__D", "_B_", "B__", Character.valueOf('B'), new ItemStack(ModItems.cores, 1, 5), Character.valueOf('D'), new ItemStack(resource, 1, 9)});
                    BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.cores, 1, 5), new ItemStack(ModItems.cores, 1, 6), 10000);
                    (new MixedResearchItem("ROD_dreamwood_staff", "MIXED", "[B]", (new AspectList()).add(Aspect.PLANT, 16).add(Aspect.ENERGY, 8).add(Aspect.ELDRITCH, 6), 4, -2, 2, new ItemStack(ModItems.cores, 1, 5))).setPages(new ResearchPage[]{new ResearchPage("schools.research_page.ROD_dreamwood_staff.1"), new ResearchPage(dreamwood_staff), new ResearchPage(dreamwood_rod)}).setParents(new String[]{"ROD_livingwood", "ROD_silverwood_staff"}).setSiblings(new String[]{"ROD_dreamwood"}).registerResearchItem();
                    (new MixedResearchItem("ROD_dreamwood", "MIXED")).registerResearchItem();

                    IArcaneRecipe elementium_cap_inert = ThaumcraftApi.addArcaneCraftingRecipe("CAP_elementium", new ItemStack(ModItems.caps, 1, 3), (new AspectList()).add(Aspect.ENTROPY, 27).add(Aspect.FIRE, 27).add(Aspect.WATER, 27).add(Aspect.AIR, 27).add(Aspect.EARTH, 27).add(Aspect.ORDER, 27), new Object[]{"NNN", "N N", Character.valueOf('N'), "nuggetElvenElementium"});
                    InfusionRecipe elementium_cap = ThaumcraftApi.addInfusionCraftingRecipe("CAP_elementium", new ItemStack(ModItems.caps, 1, 2), 1, (new AspectList()).add(Aspect.EARTH, 18).add(Aspect.FIRE, 18).add(Aspect.WATER, 18).add(Aspect.AIR, 18), new ItemStack(ModItems.caps, 1, 3), new ItemStack[]{new ItemStack(resource, 1, 5), new ItemStack(rune, 1, 0), new ItemStack(rune, 1, 1), new ItemStack(rune, 1, 2), new ItemStack(rune, 1, 3)});
                    (new MixedResearchItem("CAP_elementium", "MIXED", "[B]", (new AspectList()).add(Aspect.FIRE, 8).add(Aspect.ENERGY, 12).add(Aspect.WATER, 8).add(Aspect.AIR, 8).add(Aspect.EARTH, 8), 4, -4, 4, new ItemStack(ModItems.caps, 1, 2))).setPages(new ResearchPage[]{new ResearchPage("schools.research_page.CAP_elementium.1"), new ResearchPage(elementium_cap_inert), new ResearchPage(elementium_cap)}).setParents(new String[]{"ROD_dreamwood_staff"}).registerResearchItem();

                    ItemStack euclidaisy = Botany.getFlower("euclidaisy");
                    InfusionRecipe euclid =  ThaumcraftApi.addInfusionCraftingRecipe("EUCLIDAISY", euclidaisy, 8, (new AspectList()).add(Aspect.AURA, 4).add(Aspect.ELDRITCH, 10).add(Aspect.PLANT, 8), new ItemStack(flower, 1, 6), new ItemStack[]{new ItemStack(ItemsTC.salisMundus, 1), new ItemStack(resource, 1, 1), new ItemStack(resource, 1, 6), new ItemStack(petal, 1, 6), new ItemStack(petal, 1, 6), new ItemStack(rune, 1, 12), new ItemStack(rune, 1, 11)});
                    (new MixedResearchItem("EUCLIDAISY", "MIXED", "[B]", (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.ELDRITCH, 4).add(Aspect.AURA, 8), 4, 2, 2, euclidaisy)).setPages(new ResearchPage[] { new ResearchPage("schools.research_page.EUCLIDAISY.1"), new ResearchPage(euclid) }).setParents(new String[] { "INFUSION" }).registerResearchItem();

                    ItemStack astralbloom = Botany.getFlower("astralbloom");
                    InfusionRecipe astral =  ThaumcraftApi.addInfusionCraftingRecipe("ASTRALBLOOM", astralbloom, 8, (new AspectList()).add(Aspect.AURA, 8).add(Aspect.LIFE, 10).add(Aspect.LIGHT, 16), new ItemStack(BlocksTC.shimmerleaf, 1), new ItemStack[]{new ItemStack(ItemsTC.filter, 1), new ItemStack(ItemsTC.filter, 1), new ItemStack(resource, 1, 2), new ItemStack(resource, 1, 6), new ItemStack(petal, 1, 3), new ItemStack(petal, 1, 8), new ItemStack(rune, 1, 15), new ItemStack(rune, 1, 3)});
                    (new MixedResearchItem("ASTRALBLOOM", "MIXED", "[B]", (new AspectList()).add(Aspect.LIFE, 4).add(Aspect.FLUX, 8).add(Aspect.AURA, 4), 5, 2, 3, astralbloom)).setPages(new ResearchPage[] { new ResearchPage("schools.research_page.ASTRALBLOOM.1"), new ResearchPage(astral) }).setParents(new String[] { "INFUSION", "ETHEREALBLOOM" }).registerResearchItem();

                }
                catch(Throwable e){
                    Lumberjack.log(Level.INFO, e, "Schools of Magic decayed like a Daybloom.");
                }
            }
        }
        catch(Throwable e){
            Lumberjack.log(Level.INFO, e, "Schools of Magic slacked off instead of doing research.");
        }
    }

}
