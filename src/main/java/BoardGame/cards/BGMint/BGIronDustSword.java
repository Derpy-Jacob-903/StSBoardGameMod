package BoardGame.cards.BGMint;

import BoardGame.actions.BGGainShivAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import BoardGame.characters.BGSilent;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGIronDustSword extends AbstractBGCard {
    public static final String ID = makeID(BGIronDustSword.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGIronDustSword() {
        super(ID, cardStrings.NAME, "green/attack/riddle_with_holes", 2, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber=this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new BGGainShivAction(this.magicNumber));
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
        return new BGIronDustSword();
    }
}

