package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre pioche jusqu'à ce que 2 cartes Trésor soient dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer() {
		super("Adventurer", 6);
	}

	@Override
	public void play(Player p) {
		int carteTresor = 0;
			for(int i=0; i < p.getDraw().size(); i++) {
				if(carteTresor < 2) {
					Card carteDevoilee = p.drawCard();
					System.out.println("\n"+p.getName()+" d�voile la premi�re carte de sa pioche : carte "+carteDevoilee.getName()+"\n");
					if(carteDevoilee.getTypes().get(0).equals(CardType.Treasure)) {
						carteTresor ++;
						p.incrementHand(carteDevoilee);
					}
					else {
						p.gain(carteDevoilee);
					}
				}
			}
	}
}
