package BoardGame.cards.BGMint;
import BoardGame.cards.AbstractBGCard;
import BoardGame.cards.AbstractBGSpendCard;
import BoardGame.characters.BGIronclad;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class BGBiribiri extends AbstractBGSpendCard {
    public static final String ID = makeID(BGBiribiri.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGBiribiri() {
        super(ID, cardStrings.NAME, "red/power/inflame", 1, cardStrings.DESCRIPTION, CardType.POWER, BGRailgun.Enums.BG_MINT, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void myUse(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new InflameEffect((AbstractCreature)p), 1.0F));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new BGBiribiri();
    }
}


