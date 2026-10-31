package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGSpendCard;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGStunShock extends AbstractBGSpendCard {
    public static final String ID = makeID(BGStunShock.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGStunShock() {
        super(ID, cardStrings.NAME, "green/attack/strike", 1, cardStrings.DESCRIPTION, CardType.ATTACK, BGRailgun.Enums.BG_MINT, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.canonicalSpendCost = 1;
    }

    @Override
    public void myUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }
    public AbstractCard makeCopy() {
        return new BGStunShock();
    }
}


