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
	
	public Feast() {
		super("Feast", 4);
	}
	
	public void play(Player p) {
		//Ecarte cette carte
		p.removeFromInPlay(this);
		p.getGame().addInTrash(this);
		//Reçoit une carte coûtant 5 pièces ou moins
		String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 5 pièces";
		CardList liste = p.getGame().availableSupplyCards();
		String choix = "init";
		int prix = 10;
		while(prix > 5 && !choix.equals("")) {
			choix = p.chooseCard(instruction, liste, false);
			for(int c = 0; c<liste.size(); c++) {
				if(liste.get(c).getName().equalsIgnoreCase(choix)) {
					Card carte = liste.get(c);
					prix = carte.getCost();
				}
			}
		}
		p.gain(choix);
	}
}