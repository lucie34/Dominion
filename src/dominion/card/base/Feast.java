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
			boolean carteTrouve;
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCartes = new CardList();
			for(Card carte : reserve) {
				if(carte != null && carte.getCost()<=5) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				String choix = p.chooseCard(instruction, listeCartes, false);
				carteTrouve = false;
				for(int c = 0; c<listeCartes.size(); c++) {
					if(!carteTrouve && listeCartes.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.gain(choix);
						if(carte != null) {
							System.out.println(p.getName()+" reçoit la carte "+carte.getName()+"\n");
						}
					}
				}
			}
			else {
				System.out.println("Aucune carte de la réserve à moins de 6 pièces disponible\n");
			}			
		}
	}
}