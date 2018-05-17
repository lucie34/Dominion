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
	
	//Constructeur
	public Militia() {
		super("Militia", 4);
	}

	//Méthode jouant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			//Ramène la main de l'adversaire i à 3 cartes
			while(p.cardsInHand().size() > 3) {
				String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main à défausser";
				String choix = p.chooseCard(instruction, p.cardsInHand(), false);
				Card carte = p.cardsInHand().getCard(choix);
				p.gain(carte);//met la carte choisie dans la défausse
				p.removeFromHand(carte);//l'enlève de la main du joueur
			}
			System.out.println(p.getName()+" a 3 cartes en main ou moins\n");
		}
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementMoney(2);
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null) {
				Moat douves = new Moat();
				for(int i = 0; i<adversaires.size(); i++) {
					//Gère le cas où l'adversaire i possède dans sa main et dévoile une carte Douves l'immunisant de l'attaque
					if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
						this.attaquer(adversaires.get(i));
					}
				}		
			}
		}
	}
}