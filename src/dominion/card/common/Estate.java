package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Domaine (Estate)
 * 
 * 1 VP
 */
public class Estate extends VictoryCard {
	
	public Estate() { 
		super("Estate", 2);	
	}
	
	public int victoryValue(Player p) {
		if(p != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
	}
	
}