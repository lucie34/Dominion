package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Gold;
import dominion.card.common.Silver;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	//Constructeur
	public Mine() {
		super("Mine", 5);	
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Si le joueur poss�de une carte tr�sor  ou plus dans sa main
			if(!p.getTreasureCards().isEmpty()) {
				//Il choisit laquelle �carter
				String instruction = "Choisissez une carte tr�sor de votre main � �carter";
				String choix = p.chooseCard(instruction, p.getTreasureCards(), false);
				System.out.println(p.getName()+" �carte la carte tr�sor "+choix+"\n");
				//�carte la carte tr�sor choisie
				Card carteTresor = p.getTreasureCards().getCard(choix);
				p.getGame().addInTrash(carteTresor);
				p.removeFromHand(carteTresor);
				
				//R�cup�re le co�t de la carte �cart�e
				int coutCarte = carteTresor.getCost() +3;
				instruction = "S�lectionnez une carte tr�sor de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
				CardList listeTreasureCards = new CardList();
				//R�cup�re les cartes tr�sor de la r�serve disponible, coutant 3 pi�ces de plus ou moins que la carte �cart�e
				for(Card carte : p.getGame().availableSupplyCards()) {
					if(carte != null && carte.getTypes().get(0).equals(CardType.Treasure) && carte.getCost()<=coutCarte) {
						listeTreasureCards.add(carte);
					}
				}
				//S'il y a des cartes tr�sor dans la r�serve coutant 3 pi�ces de plus ou moins que la carte �cart�e
				if(!listeTreasureCards.isEmpty()) {
					choix = p.chooseCard(instruction, listeTreasureCards, false);
					p.incrementHand(p.getGame().removeFromSupply(choix));
					System.out.println("\n"+p.getName() +" re�oit une carte tr�sor "+choix+"\n");
				}
				else {
					System.out.println("Aucune carte tr�sor de la r�serve � moins de "+(coutCarte+3)+" pi�ces disponible");
				}
			}			
		}
	}
}