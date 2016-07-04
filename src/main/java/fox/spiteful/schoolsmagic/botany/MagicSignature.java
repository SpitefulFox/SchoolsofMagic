package fox.spiteful.schoolsmagic.botany;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.api.subtile.signature.BasicSignature;
import vazkii.botania.api.subtile.signature.SubTileSignature;

import java.util.List;

public class MagicSignature extends BasicSignature {

    public MagicSignature(String name){
        super(name);
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack item){
        return "schoolflower." + getName();
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack item){
        return "tile.schoolflower." + getName() + ".lore";
    }

}