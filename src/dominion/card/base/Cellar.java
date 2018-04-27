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
		CardList main = p.cardsInHand();
		int cartesMemeNomDefausse;
		int nombreCartesDefausse = 0;
		String question = p.getName()+" : Voulez-vous défausser une carte ? (y/n)";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("y");
		listeChoix.add("n");
		String reponse = "y";
		String instruction = "Choisissez une carte de votre main à défausser ou passez en laissant vide";
		String choix = "init";
		while(reponse.equalsIgnoreCase("y") && !choix.equals("") && !main.isEmpty()) {
			reponse = p.choose(question, listeChoix, false);
			if(reponse.compareToIgnoreCase("y") == 0) {
				cartesMemeNomDefausse = 0;
				choix = p.chooseCard(instruction, main, true);
				for(int c = 0; c<main.size(); c++) {
					if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.gain(main.get(c));
						p.removeFromHand(main.get(c));
						main.remove(c);
						cartesMemeNomDefausse ++;
						nombreCartesDefausse ++;
					}
				}
			}
		}
		for(int i = 0; i<nombreCartesDefausse; i++) {
			p.incrementHand(p.drawCard());
		}
		System.out.println("\n"+p.getName()+" a défaussé "+nombreCartesDefausse+" cartes");
	}
}