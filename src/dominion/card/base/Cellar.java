package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * DÃ©faussez autant de cartes que vous voulez.
 * +1 Carte par carte dÃ©faussÃ©e.
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
			instruction = p.getName()+" : Voulez-vous défausser une carte ? (y/n)";
			reponse = p.choose(instruction, listeChoix, false);
			if(reponse.equalsIgnoreCase("y")) {
				cartesMemeNomDefausse = 0;
				instruction = "Choisissez une carte de votre main à défausser";
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				for(int i=0; i<p.cardsInHand().size(); i++) {
					if(p.cardsInHand().get(i).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.gain(p.cardsInHand().get(i));
						p.removeFromHand(p.cardsInHand().get(i));
						cartesMemeNomDefausse++;
						nombreCartesDefausse++;
						System.out.println("\n"+p.getName()+" a defaussé une carte");
					}
				}
			}
		}
		for(int i=0; i<nombreCartesDefausse; i++) {
			p.incrementHand(p.drawCard());
		}
		System.out.println("\n"+p.getName()+" a défaussé "+nombreCartesDefausse+" cartes");
	}
}