package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {

	//M�thode
	public Chapel() {
		super("Chapel", 2);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			int nombreCartesEcarte = 0;
			String instruction = "Vous pouvez �carter 4 cartes de votre main, choisissez une carte � �carter ou laissez vide";
			String choix = "init";
			while(nombreCartesEcarte < 4 && !choix.equalsIgnoreCase("")) {
				choix = p.chooseCard(instruction, p.cardsInHand(), true);
				if(!choix.equalsIgnoreCase("")) {
					Card carte = p.cardsInHand().getCard(choix);
					System.out.println(p.getName()+" a �cart� une carte\n");
					p.getGame().addInTrash(carte);
					p.removeFromHand(carte);
					nombreCartesEcarte++;
				}
			}
			System.out.println("\n"+p.getName()+" a �cart� "+nombreCartesEcarte+" cartes");
		}			
		}
}