package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGRailgun;
import BoardGame.powers.BGLockOnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGAdapt extends AbstractBGCard {
    public static final String ID = makeID(BGAdapt.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGAdapt() {
        super(ID, cardStrings.NAME, "green/attack/strike", 1, cardStrings.DESCRIPTION, CardType.POWER, BGRailgun.Enums.BG_MINT, CardRarity.RARE, CardTarget.ENEMY);
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
        return new BGAdapt();
    }
}


