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
		System.out.println("\n ne contient pas de cuivre XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		for(int i=0; i<p.cardsInHand().size(); i++ ) {
			System.out.println("\n la main " + p.cardsInHand().get(i));
		}
		for(int i=0; i<p.cardsInHand().size(); i++ ) {
			if(p.cardsInHand().get(i).equals(carteCuivre)) {System.out.println("OK xxxxxxxxxxxxxxxxxxxxxxxx");}
		}
		if(p.cardsInHand().contains(carteCuivre)) {
			System.out.println("\n contient 1 cuivre XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			p.removeFromHand(carteCuivre);
			p.getGame().addInTrash(carteCuivre);
			p.incrementMoney(3);
		}
	}
	
}