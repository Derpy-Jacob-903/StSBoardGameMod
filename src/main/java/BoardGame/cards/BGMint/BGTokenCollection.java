package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGDefect;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BGTokenCollection extends AbstractBGCard {
    public static final String ID = makeID(BGTokenCollection.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);

    public BGTokenCollection() {
        super(ID, cardStrings.NAME, "blue/skill/hologram", 1, cardStrings.DESCRIPTION, CardType.SKILL, BGRailgun.Enums.BG_MINT, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new BetterDiscardPileToHandAction(1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new BGTokenCollection();
    }
}
