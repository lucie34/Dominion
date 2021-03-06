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

	//Constructeur
	public Adventurer() {
		super("Adventurer", 6);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			int carteTresor = 0;
			// on r�cup�re les cartes que l'on va pouvoir d�voiler
			int totalCartesDevoilables = p.getDraw().size() + p.getDiscard().size();
			for(int i=0; i < totalCartesDevoilables; i++) {
				if(carteTresor < 2) {
					Card carteDevoilee = p.drawCard();
					if(carteDevoilee != null) {
						System.out.println(p.getName()+" d�voile la premi�re carte de son deck : carte "+carteDevoilee.getName()+"\n");
						if(carteDevoilee.getTypes().get(0).equals(CardType.Treasure)) {
							carteTresor++;
							p.incrementHand(carteDevoilee);
						}
						else {
							p.gain(carteDevoilee);
						}	
					}
					else {
						System.out.println(p.getName()+" n'a pas de carte � d�voiler\n");
					}
				}
			}
		}
	}

}
