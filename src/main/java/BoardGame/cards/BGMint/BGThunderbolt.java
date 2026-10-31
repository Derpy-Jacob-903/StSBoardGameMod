package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGThunderbolt extends AbstractBGCard {
    public static final String ID = makeID(BGThunderbolt.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGThunderbolt() {
        super(ID, cardStrings.NAME, "green/attack/strike", 2, cardStrings.DESCRIPTION, CardType.ATTACK, BGRailgun.Enums.BG_MINT, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }
    public AbstractCard makeCopy() {
        return new BGThunderbolt();
    }
}


