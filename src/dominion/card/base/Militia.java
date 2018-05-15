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
		super("Militia", 4);
	}

	public void attaquer(Player p) {
		if(p != null) {
			String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main à défausser";
			String choix;
			boolean carteTrouve;
			//Ramène la main de l'adversaire i à 3 cartes s'il ne dévoile aucune carte Douves
			while(p.cardsInHand().size() > 3) {
				//Empêche de défausser plusieurs fois les cartes de même nom dans la boucle for
				carteTrouve = false;
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				for(int c=0; c<p.cardsInHand().size(); c++) {
					if(!carteTrouve && p.cardsInHand().get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.cardsInHand().get(c);
						p.gain(carte);//met la carte choisie dans défausse
						p.removeFromHand(carte);//l'enleve de la main du joueur
					}
				}
			}			
		}
	}

	public void play(Player p) {
		if(p != null) {
			p.incrementMoney(2);
			List<Player> adversaires = p.otherPlayers();
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