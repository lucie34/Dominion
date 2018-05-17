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
			Card carte = p.cardsInHand().getCard(carteCuivre.getName());
			if(carte != null) {
				p.getGame().addInTrash(carte);
				p.removeFromHand(carte);
				System.out.println(p.getName()+" écarte une carte Cuivre de sa main\n");
				p.incrementMoney(3);
			}		
		}
	}
}