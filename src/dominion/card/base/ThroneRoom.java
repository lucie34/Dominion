package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Salle du trÃ´ne (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() {
		super("ThroneRoom", 4);
	}

	public void play(Player p) {
		//Affiche le nombre d'actions restant à jouer
		int nbActionsRestant = 2;
		int nbCarteActionChoisie = 0;
		System.out.println("\n"+nbActionsRestant+" actions restantes\n");
		//Récupère cartes action dans la main du joueur
		CardList listeActionCards = p.getActionCards();
		String instruction = "Choisissez une carte action dans votre main à jouer 2 fois";
		String choix = "init";
		if(!listeActionCards.isEmpty()) {
			choix = p.chooseCard(instruction, listeActionCards, false);
			for(int c = 0; c<listeActionCards.size(); c++) {
				if(listeActionCards.get(c).getName().equalsIgnoreCase(choix) && nbCarteActionChoisie < 1) {
					nbCarteActionChoisie ++;
					Card cardChoosed = listeActionCards.get(c);
					if(cardChoosed.getName().equalsIgnoreCase("ThroneRoom") || cardChoosed.getName().equalsIgnoreCase("Cave") || cardChoosed.getName().equalsIgnoreCase("Espion") || cardChoosed.getName().equalsIgnoreCase("Laboratoire") || cardChoosed.getName().equalsIgnoreCase("Market")) {
						nbActionsRestant += 2;
						System.out.println("\n"+nbActionsRestant+" actions restantes\n");
					}
					else if(cardChoosed.getName().equalsIgnoreCase("Village") || cardChoosed.getName().equalsIgnoreCase("Festival")) {
						nbActionsRestant += 4;
						System.out.println("\n"+nbActionsRestant+" actions restantes\n");
					}
					cardChoosed.play(p);//carte action jouée une première fois
					p.playCard(cardChoosed);//carte action jouée une deuxième fois et mise dans pile inPlay
					nbActionsRestant -= 2;
					System.out.println("\n"+nbActionsRestant+" actions restantes\n");
				}
			}
		}
	}
	
}