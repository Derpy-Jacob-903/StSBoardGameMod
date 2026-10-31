package BoardGame.cards.BGMint;
import BoardGame.actions.BGChooseOneAttackAction;
import BoardGame.cards.AbstractBGAttackCardChoice;
import BoardGame.cards.AbstractBGCard;
import BoardGame.cards.BGRed.BGIronWaveShield;
import BoardGame.cards.BGRed.BGIronWaveSpear;
import BoardGame.characters.BGIronclad;
import BoardGame.characters.BGRailgun;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import java.util.ArrayList;

//TODO: make sure this doesn't crash if played targetless via Havoc etc
public class BGStatic extends AbstractBGCard {
    public static final String ID = makeID(BGStatic.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);

    public BGStatic() {
        super(ID, cardStrings.NAME, "red/attack/iron_wave", 1, cardStrings.DESCRIPTION, CardType.ATTACK, BGRailgun.Enums.BG_MINT, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 1;
        this.baseBlock = 1;
        this.baseMagicNumber=1;
        this.magicNumber=this.baseMagicNumber;
        this.defaultBaseSecondMagicNumber=1;
        this.defaultSecondMagicNumber=this.defaultBaseSecondMagicNumber;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot((AbstractGameAction) new WaitAction(0.1F));
            if (p != null && m != null) {
                addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
            }
            addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(1);
        }
    }


    public AbstractCard makeCopy() {
        return new BGStatic();
    }
}


