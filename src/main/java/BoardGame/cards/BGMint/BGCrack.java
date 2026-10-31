package BoardGame.cards.BGMint;

import BoardGame.actions.BGPlayDrawnCardAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.cards.BGRed.BGHavoc;
import BoardGame.characters.BGRailgun;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class BGCrack extends AbstractBGCard {
    public static final String ID = makeID(BGCrack.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGCrack() {
        super(ID, cardStrings.NAME, "green/attack/strike", 1, cardStrings.DESCRIPTION, CardType.ATTACK, BGRailgun.Enums.BG_MINT, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            if (Settings.isInfo) {
                this.multiDamage = new int[(AbstractDungeon.getCurrRoom()).monsters.monsters.size()];
                for (int i = 0; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size(); i++) {
                    this.multiDamage[i] = 150;
                }
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            } else {
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, 150, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        } else {
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    public boolean isShorted = false;

    public CardStrings getCardStrings() {
        if (isShorted) {
            cardStrings.DESCRIPTION += " NL p#Shorted";
        }
        return cardStrings;
    }

    @Override
    public void triggerOnManualDiscard() {
        DrawCardAction.drawnCards = new ArrayList<>(0);
        DrawCardAction.drawnCards.add(this);
        addToTop(new BGPlayDrawnCardAction(true));
    }

    @Override
    public void triggerOnExhaust() {
        if (!isShorted) {
            this.exhaustOnUseOnce = true;
            this.isShorted = true;
            //this is stupid
            DrawCardAction.drawnCards = new ArrayList<>(0);
            DrawCardAction.drawnCards.add(this);
            addToTop(new BGPlayDrawnCardAction(true));
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        isShorted = false;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }
    public AbstractCard makeCopy() {
        return new BGCrack();
    }
}


