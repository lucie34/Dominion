package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Gold;
import dominion.card.common.Silver;

/**
 * Carte Mine
 * 
 * Ã‰cartez une carte TrÃ©sor de votre main. Recevez une carte TrÃ©sor coÃ»tant jusqu'Ã  3 PiÃ¨ces de plus ; ajoutez cette carte Ã  votre main.
 */
public class Mine extends ActionCard {

	//Constructeur
	public Mine() {
		super("Mine", 5);	
	}

	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Si le joueur possède une carte trésor  ou plus dans sa main
			if(!p.getTreasureCards().isEmpty()) {
				//Il choisit laquelle écarter
				String instruction = "Choisissez une carte trésor de votre main à écarter";
				String choix = p.chooseCard(instruction, p.getTreasureCards(), false);
				System.out.println(p.getName()+" écarte la carte trésor "+choix+"\n");
				//Écarte la carte trésor choisie
				Card carteTresor = p.getTreasureCards().getCard(choix);
				p.getGame().addInTrash(carteTresor);
				p.removeFromHand(carteTresor);
				
				//Récupère le coût de la carte écartée
				int coutCarte = carteTresor.getCost() +3;
				instruction = "Sélectionnez une carte trésor de la réserve à recevoir, elle doit coûter au plus "+coutCarte+" pièces";
				CardList listeTreasureCards = new CardList();
				//Récupère les cartes trésor de la réserve disponible, coutant 3 pièces de plus ou moins que la carte écartée
				for(Card carte : p.getGame().availableSupplyCards()) {
					if(carte != null && carte.getTypes().get(0).equals(CardType.Treasure) && carte.getCost()<=coutCarte) {
						listeTreasureCards.add(carte);
					}
				}
				//S'il y a des cartes trésor dans la réserve coutant 3 pièces de plus ou moins que la carte écartée
				if(!listeTreasureCards.isEmpty()) {
					choix = p.chooseCard(instruction, listeTreasureCards, false);
					p.incrementHand(p.getGame().removeFromSupply(choix));
					System.out.println("\n"+p.getName() +" reçoit une carte trésor "+choix+"\n");
				}
				else {
					System.out.println("Aucune carte trésor de la réserve à moins de "+(coutCarte+3)+" pièces disponible");
				}
			}			
		}
	}
}