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
			CardList listeCartes = new CardList();
			//Récupère les cartes disponibles de la réserve coûtant 4 pièces ou moins
			for(Card carte : p.getGame().availableSupplyCards()) {
				if(carte != null && carte.getCost()<=4) {
					listeCartes.add(carte);
				}
			}
			//Fait choisir une carte au joueur parmi les cartes valides s'il y en a
			if(!listeCartes.isEmpty()) {
				String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 4 pièces";
				String choix = p.chooseCard(instruction, listeCartes, false);
				p.gain(choix);
				System.out.println(p.getName()+" reçoit une carte "+choix+"\n");
			}
			else {
				System.out.println("Aucune carte de la réserve à moins de 5 pièces disponible");
			}			
		}
	}
}