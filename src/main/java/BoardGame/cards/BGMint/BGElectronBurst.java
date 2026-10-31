package BoardGame.cards.BGMint;

import BoardGame.actions.BGLightningOrbEvokeAction;
import BoardGame.actions.BGLightningOrbPassiveAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import BoardGame.orbs.BGDark;
import BoardGame.orbs.BGLightning;
import BoardGame.orbs.BGVolt;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

public class BGElectronBurst extends AbstractBGCard {
    public static final String ID = makeID(BGElectronBurst.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);

    public BGElectronBurst() {
        super(ID, cardStrings.NAME, "blue/skill/defend", 1, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber=this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++){
            if (AbstractDungeon.player.orbs.get(i) instanceof BGVolt) {
                if (AbstractDungeon.player.hasPower("BGVoltElectroPower")) {
                    float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
                    if (Settings.FAST_MODE)
                        speedTime = 0.0F;
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction)new BGLightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.orbs.get(i).passiveAmount, DamageInfo.DamageType.THORNS), true));
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(AbstractDungeon.player.orbs.get(i), OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
                } else {
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction)new BGLightningOrbPassiveAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.orbs.get(i).passiveAmount, DamageInfo.DamageType.THORNS), AbstractDungeon.player.orbs.get(i), false));
                }
            }
            else
            {
                for (int j = 0; j < magicNumber; j++) {
                    AbstractDungeon.player.orbs.get(i).onEndOfTurn(); //doesn't work with plasma, but BG has no plasma so idc.
                }
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new BGElectronBurst();
    }
}


