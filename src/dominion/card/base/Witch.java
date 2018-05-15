package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Curse;

/**
 * Carte SorciÃ¨re (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	//Constructeur
	public Witch() {
		super("Witch", 5);	
	}

	//Méthode jouant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			Curse malediction = new Curse();
			p.gain(malediction.getName());
			System.out.println(p.getName()+" reçoit une carte Curse\n");
		}
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			for(int i = 0; i<2; i++) {
				p.incrementHand(p.drawCard()); 
			}
			if(p.otherPlayers() != null) {
				Moat douves = new Moat();
				for(int i=0; i < p.otherPlayers().size(); i++) {
					if(!douves.devoiler(p.otherPlayers().get(i), p.otherPlayers().get(i).cardsInHand())) {
						this.attaquer(p.otherPlayers().get(i));
					}
				}	
			}
		}
	}
}