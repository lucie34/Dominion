package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre pioche.
 */
public class Chancellor extends ActionCard {
	
	public Chancellor() {
		super("Chancellor", 3);
	}

	public void play(Player p) {
		p.incrementMoney(2);
		CardList pioche = new CardList();
		for(Card carte : p.getDraw()) {
			pioche.add(carte);
		}	
		if(!pioche.isEmpty()) {
			String instruction = "\nVoulez-vous d�fausser votre deck ? (y/n)\n";
			List<String> listeChoix = new ArrayList<String>(2);
			listeChoix.add("y");
			listeChoix.add("n");
			String choix = p.choose(instruction, listeChoix, false);
			if(choix.equalsIgnoreCase("y")) {
				for(int i=0; i<pioche.size(); i++) {
					p.gain(p.drawCard());
				}
				System.out.println("\n"+p.getName()+" a defauss� son deck");
			}
			else {
				System.out.println("\n"+p.getName()+" choisit de ne pas d�fausser son deck");
			}
		}
		else {
			System.out.println("\n"+p.getName()+" n'a pas de deck � d�fausser");
		}
	}
}