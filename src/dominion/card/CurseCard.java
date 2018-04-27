package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {
	
	

	public CurseCard(String name, int cost) {
		super(name, cost);
		super.getTypes().add(CardType.Curse);
	}
	
	public String getName() {
		return super.getName();
	}
	
	public List<CardType> getTypes() {
		return super.getTypes();
	}
	
	public int getCost() {
		return super.getCost();
	}
	
	public String toString() {
		return "La carte : " + super.getName() + " est de type " + super.getTypes().get(0);
	}
	
}