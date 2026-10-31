package BoardGame.cards.BGMint;

import BoardGame.BoardGame;
import BoardGame.cards.BGGoldenTicket;
import BoardGame.characters.BGDefect;
import BoardGame.characters.BGRailgun;
import com.megacrit.cardcrawl.cards.AbstractCard;

//TODO: exact wording of Golden Ticket card seems to have changed just before printing -- check physical copies when available
//TODO: Golden Ticket is actually a TICKET card, not a POWER card

public class BGGoldenTicket_M extends BGGoldenTicket {
    public static final String ID = BoardGame.makeID("GoldenTicket_M");
    public static final CardColor COLOR = BGRailgun.Enums.BG_MINT;
    public BGGoldenTicket_M() {
        super(ID, COLOR);
    }
    public AbstractCard makeCopy() {
        return new BGGoldenTicket_M();
    }
}
