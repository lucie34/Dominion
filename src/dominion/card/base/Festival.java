package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 * 
 * +2 Actions.
 * +1 Achat.
 * +2 Pi√®ces.
 */
public class Festival extends ActionCard {

	//Constructeur
	public Festival() {
		super("Festival", 5);
	}

	//MÈthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementActions(2);
			p.incrementBuys(1);
			p.incrementMoney(2);				
		}
	}
}