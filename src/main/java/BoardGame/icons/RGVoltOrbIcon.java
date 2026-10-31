package BoardGame.icons;

import BoardGame.util.TextureLoader;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

import java.util.ArrayList;
import java.util.List;

public class RGVoltOrbIcon extends AbstractCustomIcon {
    public static final String ID = "BoardGame:VoltOrb";    //reminder: "Icon" is automatically added
    private static RGVoltOrbIcon singleton;

    public RGVoltOrbIcon() {
        super(ID, TextureLoader.getTexture("BoardGameResources/images/icons/volt.png"));
    }

    public static RGVoltOrbIcon get()
    {
        if (singleton == null) {
            singleton = new RGVoltOrbIcon();
        }
        return singleton;
    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo>list=new ArrayList<>();
        list.add(new TooltipInfo("[BoardGame:VoltOrb] Volt","Orb: Deals damage to enemies."));
        return list;
    }
}