package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Gold;
import dominion.card.common.Silver;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);	
	}

	@Override
	public void play(Player p) { 
		CardList carteTr�sor = p.getTreasureCards();
		int indice = -1;
		int i =0;
		while(i == -1) {
			if(carteTr�sor.get(i).getName().equalsIgnoreCase("Copper")) {
				indice = i;
			}
			else if(carteTr�sor.get(i).getName().equalsIgnoreCase("Silver")) {
				indice = i;
			}
		}
		if(indice != -1) {
			Gold or = new Gold();
			Silver argent = new Silver();
			if(p.getGame().availableSupplyCards().contains(or) && carteTr�sor.get(i).getName().equalsIgnoreCase("Silver")) {
				Card c = p.getGame().getFromSupply("Gold");
				p.incrementHand(c); 
				p.getGame().removeFromSupply("Gold");
				p.removeFromHand(carteTr�sor.get(indice));
			}
			else if(p.getGame().availableSupplyCards().contains(argent) && carteTr�sor.get(i).getName().equalsIgnoreCase("Copper")) {
				Card c = p.getGame().getFromSupply("Silver");
				p.incrementHand(c);; 
				p.getGame().removeFromSupply("Silver");
				p.removeFromHand(carteTr�sor.get(indice));
			}
		}	// si il n'y a plus de pi�ces or ou argent dans la reserve on ne fait rien
	}
	
}