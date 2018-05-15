package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	//Constructeur
	public ThroneRoom() {
		super("Throne Room", 4);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Affiche le nombre d'actions restant � jouer
			int nbActionsRestant = 2;
			int nbCarteActionChoisie = 0;
			System.out.println(nbActionsRestant+" actions restantes\n");
			//R�cup�re les cartes action dans la main du joueur
			CardList listeActionCards = p.getActionCards();
			String instruction = "Choisissez une carte action dans votre main � jouer 2 fois";
			String choix;
			if(!listeActionCards.isEmpty()) {
				//Fait choisir une carte action au joueur
				choix = p.chooseCard(instruction, listeActionCards, false);
				for(int c=0; c<listeActionCards.size(); c++) {
					if(nbCarteActionChoisie < 1 && listeActionCards.get(c) != null && listeActionCards.get(c).getName().equalsIgnoreCase(choix)) {
						nbCarteActionChoisie++;
						Card cardChoosed = listeActionCards.get(c);
						System.out.println(p.getName()+" choisit de jouer 2 fois sa carte action : "+ cardChoosed.getName()+"\n");
						if(cardChoosed.getName().equalsIgnoreCase("ThroneRoom") || cardChoosed.getName().equalsIgnoreCase("Cave") || cardChoosed.getName().equalsIgnoreCase("Espion") || cardChoosed.getName().equalsIgnoreCase("Laboratoire") || cardChoosed.getName().equalsIgnoreCase("Market")) {
							nbActionsRestant += 2;
							System.out.println(nbActionsRestant+" actions restantes\n");
						}
						else if(cardChoosed.getName().equalsIgnoreCase("Village") || cardChoosed.getName().equalsIgnoreCase("Festival")) {
							nbActionsRestant += 4;
							System.out.println(nbActionsRestant+" actions restantes\n");
						}
						cardChoosed.play(p);//carte action jou�e une premi�re fois
						p.playCard(cardChoosed);//carte action jou�e une deuxi�me fois et mise dans pile inPlay
						nbActionsRestant -= 2;
						System.out.println(nbActionsRestant+" actions restantes\n");
					}
				}
			}
			else {
				System.out.println("\n"+p.getName()+" n'a aucune carte action � jouer dans sa main");
			}			
		}
	}
	
}