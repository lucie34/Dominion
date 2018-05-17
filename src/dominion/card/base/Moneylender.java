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

	//Constructeur
	public Moneylender() {
		super("Moneylender", 4);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			Copper carteCuivre = new Copper();
			Card carte = p.cardsInHand().getCard(carteCuivre.getName());
			if(carte != null) {
				p.getGame().addInTrash(carte);
				p.removeFromHand(carte);
				System.out.println(p.getName()+" �carte une carte Cuivre de sa main\n");
				p.incrementMoney(3);
			}		
		}
	}
}