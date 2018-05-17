package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Ã‰cartez cette carte.
 * Recevez une carte coÃ»tant jusqu'Ã  5 PiÃ¨ces.
 */
public class Feast extends ActionCard {
	
	//Constructeur
	public Feast() {
		super("Feast", 4);
	}
	
	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Ecarte cette carte
			p.removeFromInPlay(this);
			p.getGame().addInTrash(this);
			//Reçoit une carte coûtant 5 pièces ou moins
			String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 5 pièces";
			CardList listeCartes = new CardList();
			for(Card carte : p.getGame().availableSupplyCards()) {
				if(carte != null && carte.getCost()<=5) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				String choix = p.chooseCard(instruction, listeCartes, false);
				p.gain(choix);
				System.out.println(p.getName()+" reçoit la carte "+choix+"\n");
			}
			else {
				System.out.println("Aucune carte de la réserve à moins de 6 pièces disponible\n");
			}			
		}
	}
}