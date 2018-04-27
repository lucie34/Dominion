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
	
	public Chapel() {
		super("Chapel", 2);
	}
	
	public void play(Player p) {
		CardList main = p.cardsInHand();
		int nombreCartesEcartees = 0;
		int cartesMemeNomDefausse;
		String instruction = "Vous pouvez �carter jusqu'� 4 cartes, voulez-vous �carter une carte ? (y/n)";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("y");
		listeChoix.add("n");
		String choix = "y";
		while(choix.equalsIgnoreCase("y") && !main.isEmpty() && nombreCartesEcartees < 4) {
			choix = p.choose(instruction, listeChoix, false);
			if(choix.equalsIgnoreCase("y")) {
				cartesMemeNomDefausse = 0;
				for(int c = 0; c<main.size(); c++) {
					if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.getGame().addInTrash(main.get(c));
						p.removeFromHand(main.get(c));
						cartesMemeNomDefausse ++;
						nombreCartesEcartees ++;
					}
				}
			}
		}
		System.out.println("\n"+p.getName()+" a �cart� "+nombreCartesEcartees+" cartes");
	}
}