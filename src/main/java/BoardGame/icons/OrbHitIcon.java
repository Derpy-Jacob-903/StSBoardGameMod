package BoardGame.icons;

import BoardGame.util.TextureLoader;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

import java.util.ArrayList;
import java.util.List;

public class OrbHitIcon extends AbstractCustomIcon {
    public static final String ID = "BoardGame:OrbAtk";    //reminder: "Icon" is automatically added
    private static OrbHitIcon singleton;

    public OrbHitIcon() {
        super(ID, TextureLoader.getTexture("BoardGameResources/images/icons/orbatk.png"));
    }

    public static OrbHitIcon get()
    {
        if (singleton == null) {
            singleton = new OrbHitIcon();
        }
        return singleton;
    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo>list=new ArrayList<>();
        list.add(new TooltipInfo("[BoardGame:OrbAtkIcon] Orb Damage","Deal damage that is affected by [BoardGame:LockOnIcon]."));
        return list;
    }
}