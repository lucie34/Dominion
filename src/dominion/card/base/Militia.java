package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {
	
	public Militia() {
		super("Milice", 4);
	}
	
	public void play(Player p) {
		p.incrementMoney(2);
		List<Player> adversaires = p.otherPlayers();
		CardList main = p.cardsInHand();
		String instruction = "Choisissez une carte de votre main � d�fausser";
		String choix;
		for(int i = 0; i<adversaires.size(); i++) {
			Moat defenseCard = new Moat();
			//V�rifie que l'adversaire en question n'a pas de carte r�action l'immunisant
			if(!adversaires.get(i).cardsInHand().contains(defenseCard)) {
				int cartesMemeNomDefausse;
				//Ram�ne la main de l'adversaire � 3 cartes
				while(main.size()>3) {
					cartesMemeNomDefausse = 0;
					choix = adversaires.get(i).chooseCard(instruction, main, false);
					for(int c = 0; c<main.size(); c++) {
						if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0/*Pb des cartes de m�me nom...*/) {
							p.gain(main.get(c));//met dans d�fausse
							p.removeFromHand(main.get(c));//l'enleve effectivement de la main du joueur ? Il faut rajouter une m�thode Setter � Player ?
							main.remove(c);
							cartesMemeNomDefausse ++;
						}
					}
				}
			}
		}
	}
}