package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Ã‰cartez jusqu'Ã  4 cartes de votre main.
 */
public class Chapel extends ActionCard {
	
	public Chapel() {
		super("Chapel", 2);
	}
	
	public void play(Player p) {
		int nombreCartesEcartees = 0;
		int cartesMemeNomDefausse;
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("y");
		listeChoix.add("n");
		String instruction;
		String reponse = "y";
		String choix = "init";
		while(reponse.equalsIgnoreCase("y") && !choix.equalsIgnoreCase("") && nombreCartesEcartees < 4) {
			instruction = p.getName()+" : Vous pouvez écarter jusqu'à 4 cartes, voulez-vous écarter une carte ? (y/n)";
			System.out.println("Chapel oui1");
			reponse = p.choose(instruction, listeChoix, true);
			System.out.println("Chapel oui2");
			System.out.println("\n la réponse de Chapel est : " + reponse);
			if(reponse.equalsIgnoreCase("y")) {
				cartesMemeNomDefausse = 0;
				instruction = "Choisissez une carte de votre main à écarter";
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				for(int c = 0; c < p.cardsInHand().size(); c++) {
					if(p.cardsInHand().get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.getGame().addInTrash(p.cardsInHand().get(c));
						p.removeFromHand(p.cardsInHand().get(c));
						cartesMemeNomDefausse ++;
						nombreCartesEcartees ++;
					}
				}
			}
		}
		System.out.println("\n"+p.getName()+" a écarté "+nombreCartesEcartees+" cartes");
	}
}