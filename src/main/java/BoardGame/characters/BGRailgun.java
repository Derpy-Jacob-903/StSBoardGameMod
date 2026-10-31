package BoardGame.characters;

//TODO: updateOrb incorrectly sets orb's angle1 spin rate very high (appears to be based on Ironclad animation; vanilla Defect orb angle1 does not change)
//TODO: does Recycle interact correctly with card cost changes?

import BoardGame.BoardGame;
import BoardGame.cards.BGBlue.BGDefend_Blue;
import BoardGame.cards.BGBlue.BGDualcast;
import BoardGame.cards.BGBlue.BGStrike_Blue;
import BoardGame.cards.BGBlue.BGZap;
import BoardGame.cards.BGMint.BGDefend_M;
import BoardGame.cards.BGMint.BGElectronBurst;
import BoardGame.cards.BGMint.BGStrike_M;
import BoardGame.cards.BGMint.BGTap;
import BoardGame.multicharacter.UnselectablePlayer;
import BoardGame.relics.BGCollectorCoin;
import BoardGame.relics.BGCrackedCore;
import BoardGame.relics.BGTheDieRelic;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static BoardGame.BoardGame.*;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in DefaultMod-character-Strings.json in the resources

public class BGRailgun extends AbstractBGPlayer implements UnselectablePlayer {
    public static final Logger logger = LogManager.getLogger(BoardGame.class.getName());

    // =============== CHARACTER ENUMERATORS =================

    public static class Enums {
        @SpireEnum
        public static PlayerClass BG_RAILGUN;
        @SpireEnum(name = "BG_RAILGUN_MINT_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor BG_MINT;
        @SpireEnum(name = "BG_RAILGUN_MINT_COLOR")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
        @SpireEnum
        public static DamageInfo.DamageType ORB_DAMAGE;
    }

    // =============== CHARACTER ENUMERATORS  =================

    public String getMultiSwapButtonUrl(){return "BoardGameResources/images/icons/railgun.png";}

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 9;
    public static final int MAX_HP = 9;
    public static final int STARTING_GOLD = 5;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 2;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("BGRailgun");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "BoardGameResources/images/char/railgun/orb/layer1.png",
            "BoardGameResources/images/char/railgun/orb/layer2.png",
            "BoardGameResources/images/char/railgun/orb/layer3.png",
            "BoardGameResources/images/char/railgun/orb/layer4.png",
            "BoardGameResources/images/char/railgun/orb/layer5.png",
            "BoardGameResources/images/char/railgun/orb/layer6.png",
            "BoardGameResources/images/char/railgun/orb/layer1d.png",
            "BoardGameResources/images/char/railgun/orb/layer2d.png",
            "BoardGameResources/images/char/railgun/orb/layer3d.png",
            "BoardGameResources/images/char/railgun/orb/layer4d.png",
            "BoardGameResources/images/char/railgun/orb/layer5d.png",};
    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============



    // =============== CHARACTER CLASS START =================

    public BGRailgun(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "BoardGameResources/images/char/defaultCharacter/orb/vfx.png",
                new SpriterAnimation(
                        "BoardGameResources/images/char/railgun/standing/Standing.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================

//        initializeClass(null, // required call to load textures and setup energy/loadout.
//                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
//                THE_DEFAULT_SHOULDER_2, // campfire pose
//                THE_DEFAULT_SHOULDER_1, // another campfire pose
//                THE_DEFAULT_CORPSE, // dead corpse
        initializeClass((String)null, "BoardGameResources/images/char/railgun/shoulder2.png", "BoardGameResources/images/char/railgun/shoulder.png", "BoardGameResources/images/char/railgun/corpse.png",
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================

        /*loadAnimation(
                BGDEFECT_SKELETON_ATLAS,
                BGDEFECT_SKELETON_JSON,
                1.0f);
        //loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0F);
        // AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTimeScale(0.6F);*/
        //e.setTime(e.getEndTime() * MathUtils.random());



        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================


        energyOrb = (EnergyOrbInterface)new EnergyOrbBlue();
    }

    public Texture getEnergyImage() {
        return ImageMaster.BLUE_ORB_FLASH_VFX;
    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(BGStrike_M.ID);
        retVal.add(BGStrike_M.ID);
        retVal.add(BGStrike_M.ID);
        retVal.add(BGStrike_M.ID);
        retVal.add(BGDefend_M.ID);
        retVal.add(BGDefend_M.ID);
        retVal.add(BGDefend_M.ID);
        retVal.add(BGDefend_M.ID);
        retVal.add(BGTap.ID);
        retVal.add(BGElectronBurst.ID);

        return retVal;
    }

    // Starting Relics
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("getStartingRelics: "+BGTheDieRelic.ID+" + BGCollectorCoin");
        retVal.add(BGTheDieRelic.ID);
        retVal.add(BGCollectorCoin.ID);

        // Mark relics as seen - makes it visible in the compendium immediately
        // If you don't have this it won't be visible in the compendium until you see them in game
        UnlockTracker.markRelicAsSeen(BGTheDieRelic.ID);
        UnlockTracker.markRelicAsSeen(BGCollectorCoin.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);

    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_MAGIC_BEAM_SHORT";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 1;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.BG_MINT;
    }

    // Railgun's color
    public static final Color DEFAULT_GRAY = CardHelper.getColor(51, 255, 235);

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return DEFAULT_GRAY.cpy();
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new BGStrike_M();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new BGRailgun(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return DEFAULT_GRAY;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    public Color getSlashAttackColor() {
        return DEFAULT_GRAY;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.LIGHTNING,
                AbstractGameAction.AttackEffect.LIGHTNING,
                AbstractGameAction.AttackEffect.LIGHTNING
        };
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampire event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }


    protected Color blockTextColor = new Color(0.9F, 0.9F, 0.9F, 0.0F);
    protected float blockScale = 1.0F;


    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("images/scenes/blueBg.jpg");
    }
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("images/scenes/defect1.png", "ATTACK_MAGIC_BEAM_SHORT"));
        panels.add(new CutscenePanel("images/scenes/defect2.png"));
        panels.add(new CutscenePanel("images/scenes/defect3.png"));
        return panels;
    }


    public void applyStartOfTurnRelics() {
        super.applyStartOfTurnRelics();
        this.shivsPlayedThisTurn=0;
        this.stanceChangedThisTurn=false;
    }

    //TODO: move addBlock to CustomBoardGameCreature class (which itself will require various sweeping changes to implement)
    public void addBlock(int blockAmount) {
        float tmp = blockAmount;

        if (this.isPlayer) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                tmp = r.onPlayerGainedBlock(tmp);
            }
            if (tmp > 0.0F) {
                for (AbstractPower p : this.powers) {
                    p.onGainedBlock(tmp);
                }
            }
        }
        boolean effect = false;
        if (this.currentBlock == 0) {
            effect = true;
        }
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            for (AbstractPower p : m.powers) {
                tmp = p.onPlayerGainedBlock(tmp);
            }
        }
        this.currentBlock += MathUtils.floor(tmp);


//        if (this.currentBlock >= 99 && this.isPlayer) {
//            UnlockTracker.unlockAchievement("IMPERVIOUS");
//        }


        if (this.currentBlock > 20) {
            this.currentBlock = 20;
        }


        //if (this.currentBlock == 20 && this.isPlayer) {
            //UnlockTracker.unlockAchievement("BG_BARRICADED");
        //}

        if (effect && this.currentBlock > 0) {
            gainBlockAnimation();
        } else if (blockAmount > 0) {
            Color tmpCol = Settings.GOLD_COLOR.cpy();
            tmpCol.a = this.blockTextColor.a;
            this.blockTextColor = tmpCol;
            this.blockScale = 5.0F;
        }
    }







}
