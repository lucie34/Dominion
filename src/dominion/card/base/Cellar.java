package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {
	
	public Cellar() {
		super("Cellar", 2);
	}
	
	public void play(Player p) {
		p.incrementActions(1);
		int cartesMemeNomDefausse;
		int nombreCartesDefausse = 0;
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("y");
		listeChoix.add("n");
		String instruction;
		String reponse = "y";
		String choix = "init";
		while(reponse.equalsIgnoreCase("y") && !choix.equalsIgnoreCase("")) {
			instruction = p.getName()+" : Voulez-vous d�fausser une carte ? (y/n)";
			System.out.println("Cellar oui1");
			reponse = p.choose(instruction, listeChoix, true);
			System.out.println("Cellar oui2");
			System.out.println("\n la r�ponse de Cellar est : " + reponse);
			if(reponse.equalsIgnoreCase("y")) {
				cartesMemeNomDefausse = 0;
				instruction = "Choisissez une carte de votre main � d�fausser";
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				for(int i=0; i<p.cardsInHand().size(); i++) {
					if(p.cardsInHand().get(i).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.gain(p.cardsInHand().get(i));
						p.removeFromHand(p.cardsInHand().get(i));
						cartesMemeNomDefausse++;
						nombreCartesDefausse++;
						System.out.println("\n"+p.getName()+" a defauss� une carte");
					}
				}
			}
		}
		for(int i=0; i<nombreCartesDefausse; i++) {
			p.incrementHand(p.drawCard());
		}
		System.out.println("\n"+p.getName()+" a d�fauss� "+nombreCartesDefausse+" cartes");
	}
}