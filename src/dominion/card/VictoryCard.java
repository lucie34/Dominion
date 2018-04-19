package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	private String name;
	
	private int cost;
	
	private List<CardType> typesCarte = new ArrayList<CardType>();
	
	
	public VictoryCard(String name, int cost) {
		super(name, cost);
		typesCarte.add(CardType.Victory);
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<CardType> getTypes() {
		return typesCarte;
	}
	
	public String toString() {
		return "La carte : " + this.name + " est de type " + this.typesCarte.get(0);
	}
	
	public abstract void play(Player p);
	
	
	public int victoryValue(Player p) {
		return 0;
	}
}

