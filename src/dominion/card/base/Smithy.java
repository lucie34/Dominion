package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Forgeron (Smithy)
 * 
 * +3 Cartes.
 */
public class Smithy extends ActionCard {
	
	public Smithy() {
		super("Forgeron", 4);
	}
	
	public void play(Player p) {
		for(int i = 0; i<3; i++) {
			p.drawCard();
		}
	}
	
}