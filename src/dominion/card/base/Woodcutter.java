package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bûcheron (Woodcutter)
 * 
 * +1 Achat.
 * +2 Pièces.
 */
public class Woodcutter extends ActionCard {
	
	//Constructeur
	public Woodcutter() {
		super("Woodcutter", 3);
	}
	
	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementBuys(1);
			p.incrementMoney(2);			
		}
	}
}