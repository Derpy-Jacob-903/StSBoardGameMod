package BoardGame.cards.BGMint;

import BoardGame.actions.BGGainShivAction;
import BoardGame.actions.BGPlayDrawnCardAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import BoardGame.characters.BGSilent;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class BGJudgementOfficer extends AbstractBGCard {
    public static final String ID = makeID(BGJudgementOfficer.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGJudgementOfficer() {
        super(ID, cardStrings.NAME, "green/attack/riddle_with_holes", 3, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber=this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new BGGainShivAction(this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            //upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
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

    public AbstractCard makeCopy() {
        return new BGJudgementOfficer();
    }
}

