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
		String instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus 4 pièces";
		boolean existe = false;
		CardList liste = p.getGame().availableSupplyCards();
		for(int i=0; i<liste.size(); i++) {
			if(liste.get(i).getCost()<=4) {
				existe = true;
			}
		}
		String choix = "init";
		int prix = 10;
		while(prix > 4 && existe && !choix.equalsIgnoreCase("")) {
			choix = p.chooseCard(instruction, liste, false);
			for(int c = 0; c<liste.size(); c++) {
				if(liste.get(c).getName().equalsIgnoreCase(choix)) {
					Card carte = liste.get(c);
					prix = carte.getCost();
				}
			}
		}
		if(!choix.equalsIgnoreCase("init") && !choix.equalsIgnoreCase("")) {
			p.gain(choix);
		}
		else {
			System.out.println("Aucune carte de la réserve à moins de 5 pièces disponible");
		}
	}
}