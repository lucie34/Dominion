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
	
	//Constructeur
	public Militia() {
		super("Militia", 4);
	}

	//M�thode jouant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			//Ram�ne la main de l'adversaire i � 3 cartes
			while(p.cardsInHand().size() > 3) {
				String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main � d�fausser";
				String choix = p.chooseCard(instruction, p.cardsInHand(), false);
				Card carte = p.cardsInHand().getCard(choix);
				p.gain(carte);//met la carte choisie dans la d�fausse
				p.removeFromHand(carte);//l'enl�ve de la main du joueur
			}
			System.out.println(p.getName()+" a 3 cartes en main ou moins\n");
		}
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementMoney(2);
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null) {
				Moat douves = new Moat();
				for(int i = 0; i<adversaires.size(); i++) {
					//G�re le cas o� l'adversaire i poss�de dans sa main et d�voile une carte Douves l'immunisant de l'attaque
					if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
						this.attaquer(adversaires.get(i));
					}
				}		
			}
		}
	}
}