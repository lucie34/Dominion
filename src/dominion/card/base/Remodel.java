package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte R√©novation (Remodel)
 * 
 * √âcartez une carte de votre main.
 * Recevez une carte co√ªtant jusqu'√† 2 Pi√®ces de plus que la carte √©cart√©e.
 */
public class Remodel extends ActionCard {

	//Constructeur
	public Remodel() {
		super("Remodel", 4);
	}

	//Constructeur jouant la carte
	public void play(Player p) {
		if(p != null) {
			int coutCarte = 0;
			boolean carteTrouve;
			String instruction = "Choisissez une carte de votre main ‡ Ècarter";
			String choix;
			if(!p.cardsInHand().isEmpty()) {
				choix = p.chooseCard(instruction, p.cardsInHand(), false);
				carteTrouve = false;
				for(int c = 0; c<p.cardsInHand().size(); c++) {
					if(!carteTrouve && p.cardsInHand().get(c) != null && p.cardsInHand().get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.cardsInHand().get(c);
						coutCarte = carte.getCost();
						p.getGame().addInTrash(carte);
						p.removeFromHand(carte);
					}
				}
				coutCarte += 2;
				instruction = "SÈlectionnez une carte de la rÈserve ‡ recevoir, elle doit co˚ter au plus "+coutCarte+" piËces";
				CardList reserve = p.getGame().availableSupplyCards();
				CardList listeCarte = new CardList();
				for(Card carte : reserve) {
					if(carte != null && carte.getCost()<=coutCarte) {
						listeCarte.add(carte);
					}
				}
				if(!listeCarte.isEmpty()) {
					choix = p.chooseCard(instruction, listeCarte, false);
					carteTrouve = false;
					for(int c=0; c<listeCarte.size(); c++) {
						//Ajoute la carte choisie au deck si la rÈserve contenait une carte de prix valide
						if(!carteTrouve && listeCarte.get(c).getName().equalsIgnoreCase(choix)) {
							carteTrouve = true;
							p.gain(choix);
							System.out.println("\n"+p.getName() +" reÁoit une carte "+choix);
						}
					}
				}
				else {
					System.out.println("Aucune carte de la rÈserve ‡ moins de "+(coutCarte+1)+" piËces disponible");
				}
			}
			else {
				System.out.println(p.getName()+" n'a pas de carte ‡ Ècarter dans sa main\n");
			}
		}
	}
}