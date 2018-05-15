package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 * 
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends ActionCard {

	public Market() {
		super("Market", 5);
		
	}

	@Override
	public void play(Player p) {
		if(p != null) {
			p.incrementHand(p.drawCard()); // pioche une carte
			p.incrementActions(1);
			p.incrementBuys(1);
			p.incrementMoney(1);			
		}
	}
}