package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	private String name;
	
	private int cost;
	
	private CardType type;
	
	public VictoryCard(String name, int cost) {
		super(name, cost);
		this.type = CardType.Victory;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<CardType> getTypes() {
		List<CardType> typesCarte = new ArrayList<CardType>();
		typesCarte.add(this.type);
		return typesCarte;
	}
	
	public String toString() {
		return "La carte : " + this.name + " est de type " + this.type;
	}
}