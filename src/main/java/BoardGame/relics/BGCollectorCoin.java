package BoardGame.relics;

import BoardGame.BoardGame;
import BoardGame.actions.BGChannelAction;
import BoardGame.actions.BGLightningOrbEvokeAction;
import BoardGame.actions.BGLightningOrbPassiveAction;
import BoardGame.actions.TargetSelectScreenAction;
import BoardGame.orbs.BGLightning;
import BoardGame.orbs.BGVolt;
import BoardGame.screen.TargetSelectScreen;
import BoardGame.util.TextureLoader;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

import static BoardGame.BoardGame.makeRelicOutlinePath;
import static BoardGame.BoardGame.makeRelicPath;

public class BGCollectorCoin extends AbstractBGRelic {

    // ID, images, text.
    public static final String ID = BoardGame.makeID("CollectorCoin");

    //private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BGCollectorCoin.png"));
    //private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BGCollectorCoin.png"));
    private DamageInfo info = new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS);

    public BGCollectorCoin() {
        super(ID,
                "oldCoin.png",
                RelicTier.STARTER, LandingSound.MAGICAL);
    }
    //private static final int HEALTH_AMT = 1;

    public AbstractRelic makeCopy() {
        return new BGCollectorCoin();
    }

    public void onEquip() {
        BaseMod.MAX_HAND_SIZE=999;
    }

    @Override
    public void onPlayerEndTurn() {
        if (!(AbstractDungeon.player.orbs.stream().filter(this::canSpendOrb).count() >= 2)){
            if (AbstractDungeon.player.hasPower("BGElectroPower")) {
                float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
                if (Settings.FAST_MODE)
                    speedTime = 0.0F;
                AbstractDungeon.actionManager.addToTop(new BGLightningOrbPassiveAction(new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS), false));
            } else {
                TargetSelectScreen.TargetSelectAction tssAction = (target) -> {
                    if (target != null) {
                        float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
                        if (Settings.FAST_MODE)
                            speedTime = 0.0F;
                        this.info.output = AbstractOrb.applyLockOn((AbstractCreature) target, this.info.base);
                        addToTop((AbstractGameAction) new DamageAction((AbstractCreature) target, this.info, AbstractGameAction.AttackEffect.NONE, true));
                        addToTop((AbstractGameAction) new VFXAction((AbstractGameEffect) new LightningEffect(((AbstractCreature) target).drawX, ((AbstractCreature) target).drawY), speedTime));
                        addToTop((AbstractGameAction) new SFXAction("ORB_LIGHTNING_EVOKE"));
                    }
                };
                addToTop((AbstractGameAction)new TargetSelectScreenAction(tssAction,"Choose a target for Lightning Orb."));
            }
        }
        addToBot(new BGChannelAction(new BGVolt()));
    }

    public boolean canSpendOrb(AbstractOrb orb)
    {
        return orb instanceof BGVolt;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
