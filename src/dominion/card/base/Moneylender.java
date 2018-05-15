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
		if(p != null) {
			Copper carteCuivre = new Copper();
			boolean copperTrouve = false;
			for(int i=0; i<p.cardsInHand().size(); i++ ) {
				if(!copperTrouve && p.cardsInHand().get(i).getName().equalsIgnoreCase(carteCuivre.getName())){
					copperTrouve = true;
					Card carteCopper = p.cardsInHand().get(i);
					p.getGame().addInTrash(carteCopper);
					p.removeFromHand(carteCopper);
					p.incrementMoney(3);
				}
			}			
		}
	}
}