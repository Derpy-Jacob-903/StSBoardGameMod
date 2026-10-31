package BoardGame.relics;

import BoardGame.BoardGame;
import BoardGame.actions.BGChannelAction;
import BoardGame.actions.BGLightningOrbPassiveAction;
import BoardGame.actions.TargetSelectScreenAction;
import BoardGame.characters.BGDefect;
import BoardGame.orbs.BGVolt;
import BoardGame.screen.TargetSelectScreen;
import BoardGame.util.TextureLoader;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static BoardGame.BoardGame.makeRelicOutlinePath;
import static BoardGame.BoardGame.makeRelicPath;

public class BGPrismaticGem extends AbstractBGRelic {
    public static final String ID = "BGPrismaticGem";

    //TODO: consider changing RelicTier for all(?) relics so they show up in a more appropriate category in the compendium
    public BGPrismaticGem() {
        super("BGPrismaticGem", "prism.png", RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BGPrismaticShard();
    }

    

    public void onEquip() {
    }
}
