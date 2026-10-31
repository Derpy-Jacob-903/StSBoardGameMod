package BoardGame.actions;

import BoardGame.dungeons.AbstractBGDungeon;
import BoardGame.orbs.BGDark;
import BoardGame.screen.OrbSelectScreen;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

//TODO: showEvokeOrbCount needs a patch
//TODO: prevent AnimateOrbAction until we know WHICH orb is evoking

public class BGRemoveOrbAction extends AbstractGameAction {
    public boolean allOrbs;
    public String description;
    public AbstractOrb newOrbType;
    public BGRemoveOrbAction(boolean allOrbs, String description, AbstractOrb newOrbType) {
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FAST;
        }
        this.duration = this.startDuration;
        this.allOrbs=allOrbs;
        this.description=description;
        this.newOrbType=newOrbType;
        this.actionType = ActionType.DAMAGE;

    }
    public BGRemoveOrbAction(boolean allOrbs, String description) {
        this(allOrbs,description,null);
    }

    public void update() {
        if (this.duration == this.startDuration) {
                OrbSelectScreen.OrbSelectAction ossAction = (target) -> {
                    addToTop((AbstractGameAction) new BGEvokeSpecificOrbAction(target));
                };
                //TODO: are we addingToBot any TARGETSelectScreenActions that should be addedToTop instead? (maybe it doesn't matter?? card queue waits until action queue is empty before playing next card)
                addToTop((AbstractGameAction) new OrbSelectScreenAction(ossAction, description, false));
        }
        this.tickDuration();
    }
}
