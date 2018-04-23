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
		String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main � d�fausser";
		String choix;
		int cartesMemeNomDefausse;
		for(int i = 0; i<adversaires.size(); i++) {
			//R�cup�re la main de l'adversaire i
			CardList main = adversaires.get(i).cardsInHand();
			//G�re le cas o� l'adversaire i poss�de dans sa main et d�voile une carte Douves l'immunisant de l'attaque
			Moat defenseCard = new Moat();
			if(main.contains(defenseCard) && defenseCard.choisirDevoilerDouves(adversaires.get(i))) {
				System.out.println(adversaires.get(i).getName()+" d�voile sa carte Douves, il est prot�g� de l'attaque de "+p.getName());
			}
			//Ram�ne la main de l'adversaire i � 3 cartes s'il ne d�voile aucune carte Douves
			else if(!main.contains(defenseCard) || main.contains(defenseCard) && !defenseCard.choisirDevoilerDouves(adversaires.get(i))) {
				while(main.size()>3) {
					//Emp�che de d�fausser plusieurs fois les cartes de m�me nom dans une boucle for
					cartesMemeNomDefausse = 0;
					choix = adversaires.get(i).chooseCard(instruction, main, false);
					for(int c = 0; c<main.size(); c++) {
						if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
							p.gain(main.get(c));//met la carte choisie dans d�fausse
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