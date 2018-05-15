package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Gold;
import dominion.card.common.Silver;

/**
 * Carte Mine
 * 
 * √âcartez une carte Tr√©sor de votre main. Recevez une carte Tr√©sor co√ªtant jusqu'√† 3 Pi√®ces de plus ; ajoutez cette carte √† votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);	
	}

	@Override
	public void play(Player p) {
		int coutCarte = 0;
		boolean carteTrouve;
		String instruction = "Choisissez une carte trÈsor de votre main ‡ Ècarter";
		String choix;
		//Si le joueur possËde une carte trÈsor  ou plus dans sa main
		if(!p.getTreasureCards().isEmpty()) {
			//Il choisit laquelle Ècarter
			choix = p.chooseCard(instruction, p.getTreasureCards(), false);
			carteTrouve = false;
			for(int c = 0; c<p.getTreasureCards().size(); c++) {
				if(!carteTrouve && p.getTreasureCards().get(c).getName().equalsIgnoreCase(choix)) {
					carteTrouve = true;
					Card carte = p.getTreasureCards().get(c);
					//RÈcupËre le co˚t de la carte ÈcartÈe
					coutCarte = carte.getCost();
					//L'Ècarte
					p.getGame().addInTrash(carte);
					p.removeFromHand(carte);
				}
			}
			coutCarte += 3;
			instruction = "SÈlectionnez une carte trÈsor de la rÈserve ‡ recevoir, elle doit co˚ter au plus "+coutCarte+" piËces";
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeTreasureCards = new CardList();
			//RÈcupËre les cartes trÈsor de la rÈserve disponible, coutant 3 piËces de plus ou moins que la carte ÈcartÈe
			for(Card carte : reserve) {
				if(carte.getTypes().get(0).equals(CardType.Treasure) && carte.getCost()<=coutCarte) {
					listeTreasureCards.add(carte);
				}
			}
			//S'il y a des cartes trÈsor dans la rÈserve coutant 3 piËces de plus ou moins que la carte ÈcartÈe
			if(!listeTreasureCards.isEmpty()) {
				choix = p.chooseCard(instruction, listeTreasureCards, false);
				carteTrouve = false;
				for(int c=0; c<listeTreasureCards.size(); c++) {
					if(!carteTrouve && listeTreasureCards.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						p.incrementHand(p.getGame().removeFromSupply(choix));
						System.out.println("\n"+p.getName() +" reÁoit une carte trÈsor "+choix);
					}
				}
			}
			//Sinon
			else {
				System.out.println("Aucune carte trÈsor de la rÈserve ‡ moins de "+(coutCarte+3)+" piËces disponible");
			}
		}//Si aucune carte trÈsor dans la main ‡ Ècarter, rien
	}
}