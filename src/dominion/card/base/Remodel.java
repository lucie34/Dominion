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
		super("Remodel", 4);
	}

	public void play(Player p) {
		CardList main = p.cardsInHand();
		int coutCarte = 0;
		int cartesMemeNomDefausse;
		String instruction = "Choisissez une carte de votre main � �carter";
		String choix = "init";
		if(main.size() > 0) {
			choix = p.chooseCard(instruction, main, false);
			cartesMemeNomDefausse = 0;
			for(int c = 0; c<main.size(); c++) {
				if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
					coutCarte = main.get(c).getCost();
					cartesMemeNomDefausse ++;
					p.getGame().addInTrash(main.get(c));
					p.removeFromHand(main.get(c));
				}
			}
			coutCarte += 2;
			choix = "init";
			instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
			boolean existe = false;
			CardList liste = p.getGame().availableSupplyCards();
			for(int i=0; i<liste.size(); i++) {
				if(liste.get(i).getCost()<=coutCarte) {
					existe = true;
				}
			}
			int prix = coutCarte + 10;//initialise variable prix pour entrer dans la boucle
			while(prix > coutCarte && existe && !choix.equalsIgnoreCase("")) {
				choix = p.chooseCard(instruction, liste, false);
				for(int c = 0; c<liste.size(); c++) {
					if(liste.get(c).getName().equalsIgnoreCase(choix)) {
						prix = liste.get(c).getCost();
					}
				}
			}
			//Ajoute la carte choisie au deck si la r�serve contenait une carte de prix valide
			if(!choix.equalsIgnoreCase("init") && !choix.equalsIgnoreCase("")) {
				p.gain(choix);
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de "+(coutCarte+1)+" pi�ces disponible");
			}
		}
	}
}