package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {

	
	//private List<CardType> typesCarte = new ArrayList<CardType>();
	
	public TreasureCard(String name, int cost) {
		super(name, cost);
		//typesCarte.add(CardType.Treasure);
		super.getTypes().add(CardType.Treasure);
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
	
	
	/**
	 * Exécute l'effet de la carte, jouée par le joueur {@code p}
	 * 
	 * @param p joueur qui exécute l'effet de la carte
	 * 
	 * L'action de cette méthode dépend de la classe de la carte.
	 */
	public abstract void play(Player p);
	
	
}