package fox.spiteful.schoolsmagic.client;

import fox.spiteful.schoolsmagic.Config;
import fox.spiteful.schoolsmagic.Proxy;
import fox.spiteful.schoolsmagic.items.ItemWandCores;
import fox.spiteful.schoolsmagic.items.ModItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends Proxy {

    @Override
    public void stupidJsonBullshit(){

        if(ModItems.cores != null) {
            for(int x = 0;x < ItemWandCores.types.length;x++) {
                setCustomModelResourceLocation(ModItems.cores, x, new ModelResourceLocation("schoolsmagic:wand_rod_" + ItemWandCores.types[x], "inventory"));
            }
        }
    }

    public void setCustomModelResourceLocation(Item item, int meta, ModelResourceLocation model){
        if(item == null)
            return;
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }
}
