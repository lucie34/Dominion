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
	
	//Constructeur
	public Chancellor() {
		super("Chancellor", 3);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementMoney(2);
			if(!p.getDraw().isEmpty()) {
				int piocheSize = p.getDraw().size();
				String instruction = "Voulez-vous d�fausser votre deck ? (y/n)";
				List<String> listeChoix = new ArrayList<String>(2);
				listeChoix.add("y");
				listeChoix.add("n");
				String choix = p.choose(instruction, listeChoix, false);
				if(choix.equalsIgnoreCase("y")) {
					for(int i=0; i<piocheSize; i++) {
						p.gain(p.drawCard());
					}
					System.out.println(p.getName()+" a defauss� son deck\n");
				}
				else {
					System.out.println(p.getName()+" choisit de ne pas d�fausser son deck\n");
				}
			}
			else {
				System.out.println(p.getName()+" n'a pas de deck � d�fausser\n");
			}
		}			
	}	
}