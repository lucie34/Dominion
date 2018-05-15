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

	//Constructeur
	public Workshop() {
		super("Workshop", 3);
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Reçoit une carte coûtant 4 pièces ou moins
			String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 4 pièces";
			boolean carteTrouve;
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCartes = new CardList();
			//Récupère les cartes disponibles de la réserve coûtant 4 pièces ou moins
			for(Card carte : reserve) {
				if(carte != null && carte.getCost()<=4) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				//Fait choisir une carte au joueur parmi les cartes valides
				String choix = p.chooseCard(instruction, listeCartes, false);
				carteTrouve = false;
				for(int c = 0; c<listeCartes.size(); c++) {
					if(!carteTrouve && listeCartes.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.gain(choix);
						if(carte != null) {
							System.out.println(p.getName()+" reçoit une carte "+carte.getName()+"\n");
						}
					}
				}
			}
			else {
				System.out.println("Aucune carte de la réserve à moins de 5 pièces disponible");
			}			
		}
	}
}