package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * D√©voilez des cartes de votre pioche jusqu'√† ce que 2 cartes Tr√©sor soient d√©voil√©es. Ajoutez ces cartes Tr√©sor √† votre main et d√©faussez les autres cartes d√©voil√©es.
 */
public class Adventurer extends ActionCard {

	//Constructeur
	public Adventurer() {
		super("Adventurer", 6);
	}

	//MÈthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			int carteTresor = 0;
			// on rÈcupËre les cartes que l'on va pouvoir dÈvoiler
			int totalCartesDevoilables = p.getDraw().size() + p.getDiscard().size();
			for(int i=0; i < totalCartesDevoilables; i++) {
				if(carteTresor < 2) {
					Card carteDevoilee = p.drawCard();
					if(carteDevoilee != null) {
						System.out.println(p.getName()+" dÈvoile la premiËre carte de son deck : carte "+carteDevoilee.getName()+"\n");
						if(carteDevoilee.getTypes().get(0).equals(CardType.Treasure)) {
							carteTresor++;
							p.incrementHand(carteDevoilee);
						}
						else {
							p.gain(carteDevoilee);
						}	
					}
					else {
						System.out.println(p.getName()+" n'a pas de carte ‡ dÈvoiler\n");
					}
				}
			}
		}
	}

}
