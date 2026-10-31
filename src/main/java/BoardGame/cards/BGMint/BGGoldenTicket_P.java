package BoardGame.cards.BGMint;

import BoardGame.BoardGame;
import BoardGame.cards.BGGoldenTicket;
import BoardGame.characters.BGJester;
import com.megacrit.cardcrawl.cards.AbstractCard;

//TODO: exact wording of Golden Ticket card seems to have changed just before printing -- check physical copies when available
//TODO: Golden Ticket is actually a TICKET card, not a POWER card

public class BGGoldenTicket_P extends BGGoldenTicket {
    public static final String ID = BoardGame.makeID("GoldenTicket_M");
    public static final CardColor COLOR = BGJester.Enums.BG_PINK;
    public BGGoldenTicket_P() {
        super(ID, COLOR);
    }
    public AbstractCard makeCopy() {
        return new BGGoldenTicket_P();
    }
}
