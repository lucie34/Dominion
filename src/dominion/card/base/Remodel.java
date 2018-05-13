package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {

	public Remodel() {
		super("Remodel", 4);
	}

	public void play(Player p) {
		int coutCarte = 0;
		boolean carteTrouve;
		String instruction = "Choisissez une carte de votre main � �carter";
		String choix;
		if(!p.cardsInHand().isEmpty()) {
			choix = p.chooseCard(instruction, p.cardsInHand(), false);
			carteTrouve = false;
			for(int c = 0; c<p.cardsInHand().size(); c++) {
				if(!carteTrouve && p.cardsInHand().get(c).getName().equalsIgnoreCase(choix)) {
					carteTrouve = true;
					Card carte = p.cardsInHand().get(c);
					coutCarte = carte.getCost();
					p.getGame().addInTrash(carte);
					p.removeFromHand(carte);
				}
			}
			coutCarte += 2;
			instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCarte = new CardList();
			for(Card carte : reserve) {
				if(carte.getCost()<=coutCarte) {
					listeCarte.add(carte);
				}
			}
			if(!listeCarte.isEmpty()) {
				choix = p.chooseCard(instruction, listeCarte, false);
				carteTrouve = false;
				for(int c=0; c<listeCarte.size(); c++) {
					//Ajoute la carte choisie au deck si la r�serve contenait une carte de prix valide
					if(!carteTrouve && listeCarte.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						p.gain(choix);
						System.out.println("\n"+p.getName() +" re�oit une carte "+choix);
					}
				}
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de "+(coutCarte+1)+" pi�ces disponible");
			}
		}
	}
}