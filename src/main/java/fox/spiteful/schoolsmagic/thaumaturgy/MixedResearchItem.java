package fox.spiteful.schoolsmagic.thaumaturgy;

import fox.spiteful.schoolsmagic.Config;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

public class MixedResearchItem extends ResearchItem {

    private String inter;

    public MixedResearchItem(String key, String category)
    {
        super(key, category);
    }

    public MixedResearchItem(String key, String category, AspectList tags, int col, int row, int complex, Object ... icon)
    {
        super(key, category, tags, col, row, complex, icon);
    }

    public MixedResearchItem(String key, String category, String study, AspectList tags, int col, int row, int complex, Object ... icon)
    {
        super(key, category, tags, col, row, complex, icon);
        inter = study;
    }

    @Override
    public String getName()
    {
        return StatCollector.translateToLocal("schools.research_name." + key);
    }

    @SideOnly(Side.CLIENT)
    public String getText() {
        if (Config.tagResearch) {
            if (inter == null)
                return "[SoM] " + StatCollector.translateToLocal("schools.research_text." + key);
            else
                return "[SoM] " + inter + " " + StatCollector.translateToLocal("schools.research_text." + key);
        } else
            return StatCollector.translateToLocal("schools.research_text." + key);
    }

}
