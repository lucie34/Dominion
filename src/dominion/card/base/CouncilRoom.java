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
	
	public CouncilRoom() {
		super("CouncilRoom", 5);
	}
	
	public void play(Player p) {
		p.incrementBuys(1);
		for(int i = 0; i<4; i++) {
			p.incrementHand(p.drawCard());
		}
		List<Player> adversaires = p.otherPlayers();
		for(int i = 0; i<adversaires.size(); i++) {
			Card cartePioche = adversaires.get(i).drawCard();
			if(cartePioche != null) {
				adversaires.get(i).incrementHand(cartePioche);
			}
			else {
				System.out.println("\n"+adversaires.get(i).getName()+" n'a pas de carte à piocher");
			}
		}
	}
	
}