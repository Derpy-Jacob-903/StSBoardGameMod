package BoardGame.cards.BGStatus;


import BoardGame.cards.AbstractBGCard;
import BoardGame.cards.CardDisappearsOnExhaust;
import BoardGame.characters.BGColorless;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField.fleeting;

public class BGError extends AbstractBGCard implements CardDisappearsOnExhaust {
    public static final String ID = "BGError";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:BGError");

    public BGError() {
        super("BGError", cardStrings.NAME, "status/slimed", 1, cardStrings.DESCRIPTION, CardType.STATUS, BGColorless.Enums.CARD_COLOR, CardRarity.SPECIAL, CardTarget.SELF);
        fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void upgrade() {}

    public AbstractCard makeCopy() {
        return new BGError();
    }
}


