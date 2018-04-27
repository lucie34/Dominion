package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	//private String name;
	
	//private int cost;
	
	//private List<CardType> typesCarte = new ArrayList<CardType>();
	
	
	public VictoryCard(String name, int cost) {
		super(name, cost);
		super.getTypes().add(CardType.Victory);
	}
	
	public int getCost() {
		return super.getCost();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public List<CardType> getTypes() {
		return super.getTypes();
	}
	
	public String toString() {
		return "La carte : " + super.getName() + " est de type " + super.getTypes().get(0);
	}
	
	public abstract void play(Player p);
	
	
	public int victoryValue(Player p) {
		return 0;
	}
}

