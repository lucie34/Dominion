package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Duché (Duchy)
 * 
 * 3 VP
 */
public class Duchy extends VictoryCard {
	public Duchy() { super("Duchy", 5);	}
	
	public int victoryValue(Player p) {
		if(p != null) {
			return 3;
		}
		return 0;
	}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
	}
}