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
		super("Militia", 4);
	}

	public void attaquer(Player p) {
		if(p != null) {
			String instruction = "Vous devez avoir seulement 3 cartes en main, choisissez une carte de votre main � d�fausser";
			String choix;
			boolean carteTrouve;
			//Ram�ne la main de l'adversaire i � 3 cartes s'il ne d�voile aucune carte Douves
			while(p.cardsInHand().size() > 3) {
				//Emp�che de d�fausser plusieurs fois les cartes de m�me nom dans la boucle for
				carteTrouve = false;
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				for(int c=0; c<p.cardsInHand().size(); c++) {
					if(!carteTrouve && p.cardsInHand().get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.cardsInHand().get(c);
						p.gain(carte);//met la carte choisie dans d�fausse
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
				//G�re le cas o� l'adversaire i poss�de dans sa main et d�voile une carte Douves l'immunisant de l'attaque
				if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
					this.attaquer(adversaires.get(i));
				}
			}			
		}
	}
}