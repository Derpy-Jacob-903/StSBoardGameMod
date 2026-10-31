
package BoardGame.cards;

import BoardGame.actions.BGEvokeAllOrbsAction;
import BoardGame.actions.BGRemoveOrbAction;
import BoardGame.actions.TargetSelectScreenAction;
import BoardGame.characters.BGColorless;
import BoardGame.dungeons.AbstractBGDungeon;
import BoardGame.orbs.BGLightning;
import BoardGame.orbs.BGVolt;
import BoardGame.relics.BGTheDieRelic;
import BoardGame.screen.TargetSelectScreen;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.interfaces.AlternateCardCostModifier;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderFixSwitches;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

//class for cards which use artwork from the original game but custom colors.
public abstract class AbstractBGSpendCard extends AbstractBGCard
        //implements AlternateCardCostModifier
    {
        public int canonicalSpendCost;

        public AbstractBGSpendCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
            super(id, name, img, cost, rawDescription, type, color, rarity, target);
        }

        public boolean canSpendOrb(AbstractOrb orb)
        {
            return true; //orb instanceof BGLightning || orb instanceof BGVolt;
        }

        @Override
        public boolean canPlay(AbstractCard card) {
            AbstractBGSpendCard spendCard = null;
            if (card instanceof AbstractBGSpendCard) spendCard = (AbstractBGSpendCard) card;
            if (spendCard == null) return super.canPlay(card);
            if (!spendCard.isInAutoplay && spendCard.owner != null &&
                    !(spendCard.owner.orbs.stream().filter(this::canSpendOrb).count() >= this.canonicalSpendCost))
            {
                return false;
            }
            return super.canPlay(card);
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
            boolean osty = this.isInAutoplay;
            for (int i = 0; i < abstractPlayer.orbs.size(); i++){
            if (!osty && abstractPlayer.orbs.stream().filter(this::canSpendOrb).count() >= canonicalSpendCost)
            {
                beforeSpend(abstractPlayer, abstractMonster);
                for (int j = 0; j < canonicalSpendCost; j++)
                {
                    addToTop(new BGRemoveOrbAction(false, ""));
                }
                osty = true;
                if (!this.isInAutoplay)
                {
                    //RailgunTwoMainFile.AfterOrbSpent(choiceContext, play);
                    afterSpend(abstractPlayer, abstractMonster);
                }
            }
            if (osty)
            {
                myUse(abstractPlayer, abstractMonster);
            }
            }
        }

        public void beforeSpend(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
        public void afterSpend(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
        public abstract void myUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);
    }