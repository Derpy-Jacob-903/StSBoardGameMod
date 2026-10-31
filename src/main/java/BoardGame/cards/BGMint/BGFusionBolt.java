package BoardGame.cards.BGMint;

import BoardGame.actions.BGPlayDrawnCardAction;
import BoardGame.cards.AbstractBGCard;
import BoardGame.cards.AbstractBGSpendCard;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class BGFusionBolt extends AbstractBGSpendCard {
    public static final String ID = makeID(BGFusionBolt.class, "Railgun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoardGame:" + ID);
    public BGFusionBolt() {
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
        return new BGFusionBolt();
    }
}


