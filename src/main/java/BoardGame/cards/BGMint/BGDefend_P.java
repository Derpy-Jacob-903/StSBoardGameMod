package BoardGame.cards.BGMint;

import BoardGame.cards.AbstractBGCard;
import BoardGame.characters.BGJester;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//TODO: "to any player"
public class BGDefend_P extends AbstractBGCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:BGDefend_M");
    public static final String ID = "BGDefend_M";

    public BGDefend_P() {
        super("BGDefend_M", cardStrings.NAME, "blue/skill/defend", 1, cardStrings.DESCRIPTION, CardType.SKILL, BGJester.Enums.BG_PINK, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 1;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, 50));
        } else {
            addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new BGDefend_P();
    }
}


