package fox.spiteful.schoolsmagic.thaumaturgy;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.blood.Blood;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.wands.WandRod;

public class Thaumaturgy {

    public static WandRod WAND_ROD_BLOOD;
    public static WandRod WAND_ROD_LIVINGWOOD;
    public static WandRod WAND_ROD_DREAMWOOD;
    public static WandRod WAND_ROD_AETERNALIS;

    public static WandRod STAFF_ROD_BLOOD;
    public static WandRod STAFF_ROD_DREAMWOOD;

    public static void miracles(){
        WAND_ROD_BLOOD = new WandRod("blood", 500, new ItemStack(ModItems.cores, 1, 0), 9, new BloodWandUpdate(), new ResourceLocation("schoolsmagic", "wands/rod_blood_mat"));
        WAND_ROD_LIVINGWOOD = new WandRod("livingwood", 500, new ItemStack(ModItems.cores, 1, 3), 9, new ResourceLocation("schoolsmagic", "wands/rod_livingwood_mat"));
        WAND_ROD_DREAMWOOD = new WandRod("dreamwood", 500, new ItemStack(ModItems.cores, 1, 5), 9, new ResourceLocation("schoolsmagic", "wands/rod_dreamwood_mat"));
        WAND_ROD_AETERNALIS = new WandRod("aeternalis", 500, new ItemStack(ModItems.cores, 1, 8), 9, new ResourceLocation("schoolsmagic", "wands/rod_aeternalis_mat"));

        STAFF_ROD_BLOOD = new WandRod("blood_staff", 750, new ItemStack(ModItems.cores, 1, 2), 24, new BloodWandUpdate(), new ResourceLocation("schoolsmagic", "wands/rod_blood_mat"));
        STAFF_ROD_BLOOD.setStaff(true);
        STAFF_ROD_BLOOD.setPotencyBonus(true);

        new MixedResearchItem("ROD_aeternalis", "THAUMATURGY").setAutoUnlock().registerResearchItem();

        if(Config.blood){
            MinecraftForge.EVENT_BUS.register(new Blood.OwnerTooltip());
        }
    }

}
