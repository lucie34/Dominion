package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {
	
	public AttackCard(String name, int cost) {
		super(name, cost);
		super.getTypes().add(CardType.Attack);
	}
	
	public String toString() {
		return super.toString()+"-"+super.getTypes().get(1);
	}
	
	public abstract void attaquer(Player p);
	
	public abstract void play(Player p);
}