package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pi√®ces.
 * Vous pouvez imm√©diatement d√©fausser votre deck.
 */
public class Chancellor extends ActionCard {
	
	public Chancellor() {
		super("Chancelier", 3);
	}
	
	public void play(Player p) {
		p.incrementMoney(2);
		CardList pioche = p.getDeck();
		String instruction = "\nVoulez-vous dÈfausser votre deck ? (O/N)\n";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("O");
		listeChoix.add("N");
		String choix = p.choose(instruction, listeChoix, false);
		if(choix.equalsIgnoreCase("O")) {
			for(int c = 0; c<pioche.size(); c++) {
				p.gain(pioche.get(c));
				p.removeFromDeck(pioche.get(c));
			}
			System.out.println("\n"+p.getName()+" a defaussÈ sa deck");
		}
	}
}