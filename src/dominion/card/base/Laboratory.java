package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {

	//Constructeur
	public Laboratory() {
		super("Laboratory", 5);
	}

	//méthode play jouant la carte
	public void play(Player p) {
		if(p != null) {
			for(int i = 0; i<2; i++) {
				p.incrementHand(p.drawCard()); 
			}
			p.incrementActions(1);			
		}
	}
}