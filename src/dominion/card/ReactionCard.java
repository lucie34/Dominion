package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Réaction
 * Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {
	public ReactionCard(String name, int cost) {
		super(name, cost);
		super.getTypes().add(CardType.Reaction);
	}
	
	public String toString() {
		return super.toString()+"-"+super.getTypes().get(1);
	}
	
	public abstract void play(Player p);
}