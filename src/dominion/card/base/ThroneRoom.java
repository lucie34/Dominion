package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Salle du tr√¥ne (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {
	
	public ThroneRoom() {
		super("ThroneRoom", 4);
	}
	
	public void play(Player p) {
		//Affiche le nombre d'actions restant ‡ jouer
		int nbActionsRestant = 2;
		System.out.println("\n"+nbActionsRestant+" actions restantes\n");
		CardList main = p.cardsInHand();
		Card cardChoosed = new Estate();//initialisation pour entrer dans la boucle while
		String choix = "init";
		String instruction = "Choisissez une carte action dans votre main ‡ jouer 2 fois";
		while(!cardChoosed.getTypes().get(0).equals(CardType.Action)) {
			choix = p.chooseCard(instruction, main, false);
			for(int c = 0; c<main.size(); c++) {
				if(main.get(c).getName().equalsIgnoreCase(choix)) {
					cardChoosed = main.get(c);
				}
			}
		}
		if(cardChoosed.getName().equalsIgnoreCase("ThroneRoom") || cardChoosed.getName().equalsIgnoreCase("Cave") || cardChoosed.getName().equalsIgnoreCase("Espion") || cardChoosed.getName().equalsIgnoreCase("Laboratoire") || cardChoosed.getName().equalsIgnoreCase("Market")) {
			nbActionsRestant += 2;
			System.out.println("\n"+nbActionsRestant+" actions restantes\n");
		}
		else if(cardChoosed.getName().equalsIgnoreCase("Village") || cardChoosed.getName().equalsIgnoreCase("Festival")) {
			nbActionsRestant += 4;
			System.out.println("\n"+nbActionsRestant+" actions restantes\n");
		}
		cardChoosed.play(p);//carte action jouÈe une premiËre fois
		p.playCard(cardChoosed);//carte action jouÈe une deuxiËme fois et mise dans pile inPlay
		nbActionsRestant -= 2;
		System.out.println("\n"+nbActionsRestant+" actions restantes\n");
	}
	
}