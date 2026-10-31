package BoardGame.cards.BGMint;

import BoardGame.actions.BGConcentrateAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import BoardGame.characters.BGSilent;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGRecharge extends AbstractBGCard {
    public static final String ID = makeID(BGRecharge.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);

    public BGRecharge() {
        super(ID, cardStrings.NAME, "green/skill/concentrate", 1, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        defaultBaseSecondMagicNumber = 1;
        defaultSecondMagicNumber=this.defaultBaseSecondMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, defaultSecondMagicNumber, false));
        addToBot(new GainEnergyAction(magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();

        }
    }

    public AbstractCard makeCopy() {
        return new BGRecharge();
    }
}

