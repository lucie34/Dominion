package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Gold;
import dominion.card.common.Silver;

/**
 * Carte Mine
 * 
 * Ã‰cartez une carte TrÃ©sor de votre main. Recevez une carte TrÃ©sor coÃ»tant jusqu'Ã  3 PiÃ¨ces de plus ; ajoutez cette carte Ã  votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);	
	}

	@Override
	public void play(Player p) { 
		CardList carteTrésor = p.getTreasureCards();
		int indice = -1;
		int i =0;
		while(i == -1) {
			if(carteTrésor.get(i).getName().equalsIgnoreCase("Copper")) {
				indice = i;
			}
			else if(carteTrésor.get(i).getName().equalsIgnoreCase("Silver")) {
				indice = i;
			}
		}
		if(indice != -1) {
			Gold or = new Gold();
			Silver argent = new Silver();
			if(p.getGame().availableSupplyCards().contains(or) && carteTrésor.get(i).getName().equalsIgnoreCase("Silver")) {
				Card c = p.getGame().getFromSupply("Gold");
				p.incrementHand(c); 
				p.getGame().removeFromSupply("Gold");
				p.removeFromHand(carteTrésor.get(indice));
			}
			else if(p.getGame().availableSupplyCards().contains(argent) && carteTrésor.get(i).getName().equalsIgnoreCase("Copper")) {
				Card c = p.getGame().getFromSupply("Silver");
				p.incrementHand(c);; 
				p.getGame().removeFromSupply("Silver");
				p.removeFromHand(carteTrésor.get(indice));
			}
		}	// si il n'y a plus de pièces or ou argent dans la reserve on ne fait rien
	}
	
}