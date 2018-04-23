package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {
	
	private String name;
	
	private int cost;
	
	private List<CardType> typesCarte = new ArrayList<CardType>();

	public CurseCard(String name, int cost) {
		super(name, cost);
		typesCarte.add(CardType.Curse);
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
	
}