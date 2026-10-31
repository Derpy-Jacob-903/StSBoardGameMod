package BoardGame.icons;

import BoardGame.util.TextureLoader;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

import java.util.ArrayList;
import java.util.List;

public class LockOnIcon extends AbstractCustomIcon {
    public static final String ID = "BoardGame:LockOn";    //reminder: "Icon" is automatically added
    private static LockOnIcon singleton;

    public LockOnIcon() {
        super(ID, TextureLoader.getTexture("BoardGameResources/images/icons/lockon.png"));
    }

    public static LockOnIcon get()
    {
        if (singleton == null) {
            singleton = new LockOnIcon();
        }
        return singleton;
    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo>list=new ArrayList<>();
        list.add(new TooltipInfo("[BoardGame:LockOnIcon] Lock-On","Takes +1 damage on each [BoardGame:OrbHitIcon]. Remove a [BoardGame:LockOnIcon] token at the start of the enemy turn."));
        return list;
    }
}