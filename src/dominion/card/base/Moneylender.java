package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main.
 * Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {
	
	public Moneylender() {
		super("Moneylender", 4);
	}
	
	public void play(Player p) {
		Copper carteCuivre = new Copper();
		if(p.cardsInHand().contains(carteCuivre)) {
			p.removeFromHand(carteCuivre);
			p.getGame().addInTrash(carteCuivre);
			p.incrementMoney(3);
		}
	}
	
}