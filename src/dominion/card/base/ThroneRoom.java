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

	//Constructeur
	public ThroneRoom() {
		super("Throne Room", 4);
	}

	//MÈthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			if(!p.getActionCards().isEmpty()) {
				//Affiche le nombre d'actions restant ‡ jouer
				int nbActionsRestant = 2;
				System.out.println(nbActionsRestant+" actions restantes\n");
				//Fait choisir une carte action au joueur
				String instruction = "Choisissez une carte action dans votre main ‡ jouer 2 fois";
				String choix = p.chooseCard(instruction, p.getActionCards(), false);
				System.out.println(p.getName()+" choisit de jouer 2 fois sa carte action : "+choix+"\n");
				if(choix.equalsIgnoreCase("ThroneRoom") || choix.equalsIgnoreCase("Cave") || choix.equalsIgnoreCase("Espion") || choix.equalsIgnoreCase("Laboratoire") || choix.equalsIgnoreCase("Market")) {
					nbActionsRestant += 2;
					System.out.println(nbActionsRestant+" actions restantes\n");
				}
				else if(choix.equalsIgnoreCase("Village") || choix.equalsIgnoreCase("Festival")) {
					nbActionsRestant += 4;
					System.out.println(nbActionsRestant+" actions restantes\n");
				}
				//Joue la carte action 2 fois
				Card cardChoosed = p.getActionCards().getCard(choix);
				cardChoosed.play(p);
				p.playCard(cardChoosed);
				nbActionsRestant -= 2;
				System.out.println(nbActionsRestant+" actions restantes\n");
			}
			else {
				System.out.println("\n"+p.getName()+" n'a aucune carte action ‡ jouer dans sa main");
			}			
		}
	}
	
}