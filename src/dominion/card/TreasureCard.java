package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {

	private String name;
	
	private int cost;
	
	private List<CardType> typesCarte = new ArrayList<CardType>();
	
	public TreasureCard(String name, int cost) {
		super(name, cost);
		typesCarte.add(CardType.Treasure);
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
	
	
	/**
	 * Exécute l'effet de la carte, jouée par le joueur {@code p}
	 * 
	 * @param p joueur qui exécute l'effet de la carte
	 * 
	 * L'action de cette méthode dépend de la classe de la carte.
	 */
	public abstract void play(Player p);
	
	
}