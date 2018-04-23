package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 PiÃ¨ces.
 * Tous vos adversaires dÃ©faussent leurs cartes de faÃ§on Ã  n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {
	
	public Militia() {
		super("Milice", 4);
	}
	
	public void play(Player p) {
		p.incrementMoney(2);
		List<Player> adversaires = p.otherPlayers();
		String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main à défausser";
		String choix;
		int cartesMemeNomDefausse;
		for(int i = 0; i<adversaires.size(); i++) {
			//Récupère la main de l'adversaire i
			CardList main = adversaires.get(i).cardsInHand();
			//Gère le cas où l'adversaire i possède dans sa main et dévoile une carte Douves l'immunisant de l'attaque
			Moat defenseCard = new Moat();
			if(main.contains(defenseCard) && defenseCard.choisirDevoilerDouves(adversaires.get(i))) {
				System.out.println(adversaires.get(i).getName()+" dévoile sa carte Douves, il est protégé de l'attaque de "+p.getName());
			}
			//Ramène la main de l'adversaire i à 3 cartes s'il ne dévoile aucune carte Douves
			else if(!main.contains(defenseCard) || main.contains(defenseCard) && !defenseCard.choisirDevoilerDouves(adversaires.get(i))) {
				while(main.size()>3) {
					//Empêche de défausser plusieurs fois les cartes de même nom dans une boucle for
					cartesMemeNomDefausse = 0;
					choix = adversaires.get(i).chooseCard(instruction, main, false);
					for(int c = 0; c<main.size(); c++) {
						if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
							p.gain(main.get(c));//met la carte choisie dans défausse
							p.removeFromHand(main.get(c));//l'enleve de la main du joueur
							main.remove(c);
							cartesMemeNomDefausse ++;
						}
					}
				}
			}
		}
	}
}