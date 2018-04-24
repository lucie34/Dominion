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

	public Remodel() {
		super("Renovation", 4);
	}

	public void play(Player p) {
		CardList main = p.cardsInHand();
		int nbCartesEcartees = 0;
		int coutCarte = 0;
		int cartesMemeNomDefausse;
		String instruction = "Choisissez une carte de votre main à écarter";
		String choix = "init";
		if(main.size() > 0) {
			while(nbCartesEcartees < 1) {
				choix = p.chooseCard(instruction, main, false);
				cartesMemeNomDefausse = 0;
				for(int c = 0; c<main.size(); c++) {
					if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.getGame().addInTrash(main.get(c));
						p.removeFromHand(main.get(c));
						coutCarte = main.get(c).getCost();
						nbCartesEcartees ++;
						cartesMemeNomDefausse ++;
					}
				}
			}
			coutCarte += 2;
			CardList liste = p.getGame().availableSupplyCards();
			instruction = "Sélectionnez une carte de la réserve à recevoir, elle doit coûter au plus "+coutCarte+" pièces";
			int prix = coutCarte + 10;//initialise variable prix pour entrer dans la boucle
			while(prix > coutCarte && !choix.equals("")) {
				choix = p.chooseCard(instruction, liste, false);
				for(int c = 0; c<liste.size(); c++) {
					if(liste.get(c).getName().equalsIgnoreCase(choix)) {
						prix = liste.get(c).getCost();
					}
				}
			}
			p.gain(choix);
		}
	}
}