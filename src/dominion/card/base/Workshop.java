package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coÃ»tant jusqu'Ã  4 PiÃ¨ces.
 */
public class Workshop extends ActionCard {

	public Workshop() {
		super("Workshop", 3);
	}

	public void play(Player p) {
		if(p != null) {
			//Reçoit une carte coûtant 4 pièces ou moins
			String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 4 pièces";
			boolean carteTrouve;
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCartes = new CardList();
			for(Card carte : reserve) {
				if(carte.getCost()<=4) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				String choix = p.chooseCard(instruction, listeCartes, false);
				carteTrouve = false;
				for(int c = 0; c<listeCartes.size(); c++) {
					if(!carteTrouve && listeCartes.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						p.gain(choix);
					}
				}
			}
			else {
				System.out.println("Aucune carte de la réserve à moins de 5 pièces disponible");
			}			
		}
	}
}