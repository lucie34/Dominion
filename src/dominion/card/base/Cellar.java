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
		super("Cave", 2);
	}
	
	public void play(Player p) {
		p.incrementActions(1);
		CardList main = p.cardsInHand();
		int cartesMemeNomDefausse;
		int nombreCartesDefausse = 0;
		String question = p.getName()+" : Voulez-vous défausser une carte ? (O/N)";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("O");
		listeChoix.add("N");
		String reponse = "O";
		String instruction = "Choisissez une carte de votre main à défausser ou passez en laissant vide";
		String choix = "init";
		while(reponse.equalsIgnoreCase("O") && !choix.equals("") && !main.isEmpty()) {
			reponse = p.choose(question, listeChoix, false);
			if(reponse.compareToIgnoreCase("O") == 0) {
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