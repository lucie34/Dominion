package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Village
 * 
 * +1 Carte.
 * +2 Actions.
 */
public class Village extends ActionCard {
	
	public Village() {
		super("Village", 3);
	}
	
	public void play(Player p) {
		if(p != null) {
			p.incrementHand(p.drawCard());
			p.incrementActions(2);			
		}
	}
}