package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Curse;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	public Witch() {
		super("Witch", 5);	
	}

	@Override
	public void attaquer(Player p) {
		Curse malediction = new Curse();
		for(int i=0; i < p.otherPlayers().size(); i++) {
			p.otherPlayers().get(i).gain(malediction.getName());
		}
	}

	@Override
	public void play(Player p) {
		for(int i = 0; i<2; i++) {
			p.incrementHand(p.drawCard()); 
		}
		Moat douves = new Moat();
		for(int i=0; i < p.otherPlayers().size(); i++) {
			if(!douves.devoiler(p.otherPlayers().get(i), p.otherPlayers().get(i).cardsInHand())) {
				this.attaquer(p.otherPlayers().get(i));
			}
		}
	}
}