package BoardGame.icons;

import BoardGame.orbs.BGLightning;
import BoardGame.orbs.BGVolt;
import BoardGame.util.TextureLoader;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

import java.util.ArrayList;
import java.util.List;

public class RGLIghtningOrbIcon extends AbstractCustomIcon {
    public static final String ID = "BoardGame:RGLightningOrb";    //reminder: "Icon" is automatically added
    private static RGLIghtningOrbIcon singleton;

    public RGLIghtningOrbIcon() {
        super(ID, TextureLoader.getTexture("BoardGameResources/images/icons/lightning.png"));
    }

    public static RGLIghtningOrbIcon get()
    {
        if (singleton == null) {
            singleton = new RGLIghtningOrbIcon();
        }
        return singleton;
    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo>list=new ArrayList<>();
        list.add(new TooltipInfo("[BoardGame:RGLightningOrb] Lightning","Orb: Deals damage to enemies."));
        return list;
    }
}