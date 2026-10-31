package BoardGame.powers;

import BoardGame.BoardGame;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BGLockOnProccedPower extends AbstractBGPower implements InvisiblePower {
    public static final String POWER_ID = BoardGame.makeID("LockOnProcced");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    private static final int EFFECTIVENESS_STRING = 1;

    public BGLockOnProccedPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
        this.name = NAME;
        this.ID = "LockOnProcced";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("shackle");

        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.priority = 99;
    }


    public void atStartOfTurn() {
        if(this.owner != AbstractDungeon.player) {
            addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "LockOnProcced"));
            addToTop((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, "BGLockOn", 1));
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        //if monster, wears off after card resolves
        //be careful when using with "slow" cards -- damage action must go on top of stack
        addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "LockOnProcced"));
        addToTop((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, "BGLockOn", 1));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        this.amount=1;
    }


}


