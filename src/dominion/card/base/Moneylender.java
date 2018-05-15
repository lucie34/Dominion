package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte PrÃªteur sur gages (Moneylender)
 * 
 * Ã‰cartez une carte Cuivre de votre main.
 * Dans ce cas, +3 PiÃ¨ces.
 */
public class Moneylender extends ActionCard {

	//Constructeur
	public Moneylender() {
		super("Moneylender", 4);
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			Copper carteCuivre = new Copper();
			boolean copperTrouve = false;
			for(int i=0; i<p.cardsInHand().size(); i++ ) {
				//Si le joueur possède une carte Cuivre dans sa main, l'action de la carte est réalisée
				if(!copperTrouve && p.cardsInHand().get(i) != null && p.cardsInHand().get(i).getName().equalsIgnoreCase(carteCuivre.getName())){
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