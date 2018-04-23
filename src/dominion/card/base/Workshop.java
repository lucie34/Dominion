package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {
	
	public Workshop() {
		super("Atelier", 3);
	}
	
	public void play(Player p) {
		String instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter 4 pi�ces ou moins";
		CardList liste = p.getGame().availableSupplyCards();
		String choix = "init";
		int prix = 10;
		while(prix > 4 && !choix.equals("")) {
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