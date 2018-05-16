package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Malédiction (Curse)
 * 
 * -1 VP
 */
public class Curse extends CurseCard {
	public Curse() { super("Curse", 0);	
	}
	
	public int victoryValue(Player p) {
		if(p != null) {
			return -1;			
		}
		return 0;
	}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
		
	}
}