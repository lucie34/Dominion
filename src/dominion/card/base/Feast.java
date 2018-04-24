package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {
	
	public Feast() {
		super("Festin", 4);
	}
	
	public void play(Player p) {
		//Ecarte cette carte
		p.removeFromInPlay(this);
		p.getGame().addInTrash(this);
		//Re�oit une carte co�tant 5 pi�ces ou moins
		String instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus 5 pi�ces";
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