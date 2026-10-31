package BoardGame.relics;

import BoardGame.actions.BGActivateDieAbilityAction;
import BoardGame.actions.TargetSelectScreenAction;
import BoardGame.powers.BGLockOnPower;
import BoardGame.powers.BGVulnerablePower;
import BoardGame.screen.TargetSelectScreen;
import BoardGame.thedie.TheDie;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BGMarbleBag extends AbstractBGRelic implements DieControlledRelic {
    public BGMarbleBag() {
        super("BGMarbles", "marbles.png", RelicTier.COMMON, LandingSound.SOLID);
    }
    public int getPrice() {return 8;}

    public String getQuickSummary(){
        if(TheDie.monsterRoll==5)return "1 Lock-On";
    else return "";}

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BGMarbleBag();
    }

    public void checkDieAbility(){
        if(TheDie.finalRelicRoll==5){
            activateDieAbility();
        }
    }

    public void activateDieAbility(){
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));

        TargetSelectScreen.TargetSelectAction tssAction = (target) -> {
            if(target==null)return;
            addToBot((AbstractGameAction) new ApplyPowerAction(target,AbstractDungeon.player,new BGLockOnPower(target,1),1 ));
        };
        addToBot((AbstractGameAction)new TargetSelectScreenAction(tssAction,"Choose a target for Marble Bag (1 Lock-On)."));
    }

    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.

    @Override
    public void onRightClick() {// On right click
        if (!isObtained || !isPlayerTurn ) {
            return;
        }
        addToBot((AbstractGameAction)new BGActivateDieAbilityAction(this));
    }

    public void atTurnStart() {
        isPlayerTurn = true; // It's our turn!
    }

    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false; // Not our turn now.
        stopPulse();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.maxOrbs > 0;
    }

    @Override
    public void onVictory() {
        stopPulse(); // Don't keep pulsing past the victory screen/outside of combat.
    }


}


