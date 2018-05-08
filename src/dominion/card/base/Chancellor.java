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
		super("Chancellor", 3);
	}

	public void play(Player p) {
		p.incrementMoney(2);
		if(!p.totalCards().isEmpty()) {
			String instruction = "\nVoulez-vous dÈfausser votre deck ? (y/n)\n";
			List<String> listeChoix = new ArrayList<String>(2);
			listeChoix.add("y");
			listeChoix.add("n");
			String choix = p.choose(instruction, listeChoix, false);
			if(choix.equalsIgnoreCase("y")) {
				CardList deck = p.removeDeck();
				for(int c=0; c<deck.size(); c++) {
					p.gain(deck.get(c));
				}
				System.out.println("\n"+p.getName()+" a defaussÈ sa deck");
			}
		}
	}
}