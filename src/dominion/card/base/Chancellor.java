package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pi√®ces.
 * Vous pouvez imm√©diatement d√©fausser votre pioche.
 */
public class Chancellor extends ActionCard {
	
	//Constructeur
	public Chancellor() {
		super("Chancellor", 3);
	}

	//MÈthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementMoney(2);
			if(!p.getDraw().isEmpty()) {
				int piocheSize = p.getDraw().size();
				String instruction = "Voulez-vous dÈfausser votre deck ? (y/n)";
				List<String> listeChoix = new ArrayList<String>(2);
				listeChoix.add("y");
				listeChoix.add("n");
				String choix = p.choose(instruction, listeChoix, false);
				if(choix.equalsIgnoreCase("y")) {
					for(int i=0; i<piocheSize; i++) {
						p.gain(p.drawCard());
					}
					System.out.println(p.getName()+" a defaussÈ son deck\n");
				}
				else {
					System.out.println(p.getName()+" choisit de ne pas dÈfausser son deck\n");
				}
			}
			else {
				System.out.println(p.getName()+" n'a pas de deck ‡ dÈfausser\n");
			}
		}			
	}	
}