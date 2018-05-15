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

	public Mine() {
		super("Mine", 5);	
	}

	@Override
	public void play(Player p) {
		int coutCarte = 0;
		boolean carteTrouve;
		String instruction = "Choisissez une carte tr�sor de votre main � �carter";
		String choix;
		//Si le joueur poss�de une carte tr�sor  ou plus dans sa main
		if(!p.getTreasureCards().isEmpty()) {
			//Il choisit laquelle �carter
			choix = p.chooseCard(instruction, p.getTreasureCards(), false);
			carteTrouve = false;
			for(int c = 0; c<p.getTreasureCards().size(); c++) {
				if(!carteTrouve && p.getTreasureCards().get(c).getName().equalsIgnoreCase(choix)) {
					carteTrouve = true;
					Card carte = p.getTreasureCards().get(c);
					//R�cup�re le co�t de la carte �cart�e
					coutCarte = carte.getCost();
					//L'�carte
					p.getGame().addInTrash(carte);
					p.removeFromHand(carte);
				}
			}
			coutCarte += 3;
			instruction = "S�lectionnez une carte tr�sor de la r�serve � recevoir, elle doit co�ter au plus "+coutCarte+" pi�ces";
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeTreasureCards = new CardList();
			//R�cup�re les cartes tr�sor de la r�serve disponible, coutant 3 pi�ces de plus ou moins que la carte �cart�e
			for(Card carte : reserve) {
				if(carte.getTypes().get(0).equals(CardType.Treasure) && carte.getCost()<=coutCarte) {
					listeTreasureCards.add(carte);
				}
			}
			//S'il y a des cartes tr�sor dans la r�serve coutant 3 pi�ces de plus ou moins que la carte �cart�e
			if(!listeTreasureCards.isEmpty()) {
				choix = p.chooseCard(instruction, listeTreasureCards, false);
				carteTrouve = false;
				for(int c=0; c<listeTreasureCards.size(); c++) {
					if(!carteTrouve && listeTreasureCards.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						p.incrementHand(p.getGame().removeFromSupply(choix));
						System.out.println("\n"+p.getName() +" re�oit une carte tr�sor "+choix);
					}
				}
			}
			//Sinon
			else {
				System.out.println("Aucune carte tr�sor de la r�serve � moins de "+(coutCarte+3)+" pi�ces disponible");
			}
		}//Si aucune carte tr�sor dans la main � �carter, rien
	}
}