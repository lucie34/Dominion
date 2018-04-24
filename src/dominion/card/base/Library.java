package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {

	public Library() {
		super("Biblioth�que", 5);
	
	}

	@Override
	public void play(Player p) {
		while(p.cardsInHand().size() < 8) {
			if(p.drawCard().getTypes().) {}
			p.incrementHand(p.drawCard()); 
		}
		
		
	}
}