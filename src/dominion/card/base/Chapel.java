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
		super("Chapelle", 2);
	}
	
	public void play(Player p) {
		CardList main = p.cardsInHand();
		int nombreCartesEcartees = 0;
		int cartesMemeNomDefausse;
		Scanner sc = new Scanner(System.in);
		String instruction = "Vous pouvez écarter jusqu'à 4 cartes, voulez-vous écarter une carte ? (O/N)";
		String choix = "O";
		while(choix.equalsIgnoreCase("O") && nombreCartesEcartees < 4) {
			System.out.println("\n"+instruction+"\n");
			choix = sc.nextLine();
			if(choix.equalsIgnoreCase("O")) {
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
		System.out.println(p.getName()+" a écarté "+nombreCartesEcartees+" cartes");
	}
}