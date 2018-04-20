package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {
		
	public ActionCard(String name, int cost) {
		super(name, cost);
		super.getTypes().add(CardType.Action);
	}
	
	public String toString() {
		return super.toString()+", de type : "+super.getTypes().get(0);
	}
	
	public abstract void play(Player p);
}