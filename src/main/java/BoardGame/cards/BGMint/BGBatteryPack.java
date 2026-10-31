package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGBatteryPack extends AbstractBGCard {
    public static final String ID = makeID(BGBatteryPack.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGBatteryPack() {
        super(ID, cardStrings.NAME, "green/attack/strike", 1, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.COMMON, CardTarget.ENEMY);
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
        return new BGBatteryPack();
    }
}


