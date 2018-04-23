package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
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
		String instruction = "Choisissez une carte de votre main � �carter";
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
			instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
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