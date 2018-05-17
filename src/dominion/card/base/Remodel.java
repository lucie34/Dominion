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

	//Constructeur
	public Remodel() {
		super("Remodel", 4);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			if(!p.cardsInHand().isEmpty()) {
				String instruction = "Choisissez une carte de votre main � �carter";
				String choix = p.chooseCard(instruction, p.cardsInHand(), false);
				Card carte = p.cardsInHand().getCard(choix);
				p.getGame().addInTrash(carte);
				p.removeFromHand(carte);
				
				int coutCarte = carte.getCost() +2;
				instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
				CardList listeCarte = new CardList();
				for(Card c : p.getGame().availableSupplyCards()) {
					if(c != null && c.getCost()<=coutCarte) {
						listeCarte.add(c);
					}
				}
				if(!listeCarte.isEmpty()) {
					choix = p.chooseCard(instruction, listeCarte, false);
					p.gain(choix);
					System.out.println(p.getName() +" re�oit une carte "+choix+"\n");
				}
				else {
					System.out.println("Aucune carte de la r�serve � moins de "+(coutCarte+1)+" pi�ces disponible\n");
				}
			}
			else {
				System.out.println(p.getName()+" n'a pas de carte � �carter dans sa main\n");
			}
		}
	}
}