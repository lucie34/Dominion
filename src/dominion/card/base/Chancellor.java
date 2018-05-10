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
	
	public Chancellor() {
		super("Chancellor", 3);
	}

	public void play(Player p) {
		p.incrementMoney(2);
		CardList pioche = p.getDraw();
		if(!pioche.isEmpty()) {
			String instruction = "\nVoulez-vous dÈfausser votre deck ? (y/n)\n";
			List<String> listeChoix = new ArrayList<String>(2);
			listeChoix.add("y");
			listeChoix.add("n");
			String choix = p.choose(instruction, listeChoix, false);
			if(choix.equalsIgnoreCase("y")) {
				for(int i=0; i<pioche.size(); i++) {
					p.gain(p.drawCard());
				}
				System.out.println("\n"+p.getName()+" a defaussÈ son deck");
			}
			else {
				System.out.println("\n"+p.getName()+" choisit de ne pas dÈfausser son deck");
			}
		}
		else {
			System.out.println("\n"+p.getName()+" n'a pas de deck ‡ dÈfausser");
		}
	}
}