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
		Scanner scan = new Scanner(System.in);
		String reponse = "O";
		int cartesMemeNomDefausse;
		int nombreCartesDefausse = 0;
		String instruction = "Choisissez une carte de votre main à défausser ou passez en laissant vide";
		String choix = "init";
		while(reponse.equalsIgnoreCase("O") && !choix.equals("")) {
			System.out.println("\n"+p.getName()+" : Voulez-vous défausser une carte ? (O/N)\n");
			reponse = scan.nextLine();
			if(reponse.compareToIgnoreCase("O") == 0) {
				cartesMemeNomDefausse = 0;
				choix = p.chooseCard(instruction, main, true);
				for(int c = 0; c<main.size(); c++) {
					if(main.get(c).getName().equalsIgnoreCase(choix) && cartesMemeNomDefausse == 0) {
						p.gain(main.get(c));
						p.removeFromHand(main.get(c));
						cartesMemeNomDefausse ++;
						nombreCartesDefausse ++;
					}
				}
			}
		}
		for(int i = 0; i<nombreCartesDefausse; i++) {
			p.drawCard();
		}
		System.out.println(p.getName()+" a défaussé "+nombreCartesDefausse+" cartes");
	}
}