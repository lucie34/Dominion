package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {
	
	//Constructeur
	public CouncilRoom() {
		super("Council Room", 5);
	}
	
	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			for(int i=0; i<4; i++) {
				p.incrementHand(p.drawCard());
			}
			p.incrementBuys(1);
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null) {
				for(int i=0; i<adversaires.size(); i++) {
					adversaires.get(i).incrementHand(adversaires.get(i).drawCard());
				}	
			}
		}
	}
}