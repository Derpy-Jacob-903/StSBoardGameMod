package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGGoofball extends AbstractBGCard {
    public static final String ID = makeID(BGGoofball.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGGoofball() {
        super(ID, cardStrings.NAME, "green/attack/strike", 1, cardStrings.DESCRIPTION, CardType.POWER, BGJester.Enums.BG_PINK, CardRarity.RARE, CardTarget.ENEMY);
        //
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        //
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
    public AbstractCard makeCopy() {
        return new BGGoofball();
    }
}


