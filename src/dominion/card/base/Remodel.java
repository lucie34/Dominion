package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte RÃ©novation (Remodel)
 * 
 * Ã‰cartez une carte de votre main.
 * Recevez une carte coÃ»tant jusqu'Ã  2 PiÃ¨ces de plus que la carte Ã©cartÃ©e.
 */
public class Remodel extends ActionCard {

	//Constructeur
	public Remodel() {
		super("Remodel", 4);
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			if(!p.cardsInHand().isEmpty()) {
				String instruction = "Choisissez une carte de votre main à écarter";
				String choix = p.chooseCard(instruction, p.cardsInHand(), false);
				Card carte = p.cardsInHand().getCard(choix);
				p.getGame().addInTrash(carte);
				p.removeFromHand(carte);
				
				int coutCarte = carte.getCost() +2;
				instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus "+coutCarte+" pièces";
				CardList listeCarte = new CardList();
				for(Card c : p.getGame().availableSupplyCards()) {
					if(c != null && c.getCost()<=coutCarte) {
						listeCarte.add(c);
					}
				}
				if(!listeCarte.isEmpty()) {
					choix = p.chooseCard(instruction, listeCarte, false);
					p.gain(choix);
					System.out.println(p.getName() +" reçoit une carte "+choix+"\n");
				}
				else {
					System.out.println("Aucune carte de la réserve à moins de "+(coutCarte+1)+" pièces disponible\n");
				}
			}
			else {
				System.out.println(p.getName()+" n'a pas de carte à écarter dans sa main\n");
			}
		}
	}
}