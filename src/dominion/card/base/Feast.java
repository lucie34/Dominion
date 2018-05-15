package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {
	
	//Constructeur
	public Feast() {
		super("Feast", 4);
	}
	
	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Ecarte cette carte
			p.removeFromInPlay(this);
			p.getGame().addInTrash(this);
			//Re�oit une carte co�tant 5 pi�ces ou moins
			String instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus 5 pi�ces";
			boolean carteTrouve;
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCartes = new CardList();
			for(Card carte : reserve) {
				if(carte != null && carte.getCost()<=5) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				String choix = p.chooseCard(instruction, listeCartes, false);
				carteTrouve = false;
				for(int c = 0; c<listeCartes.size(); c++) {
					if(!carteTrouve && listeCartes.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.gain(choix);
						if(carte != null) {
							System.out.println(p.getName()+" re�oit la carte "+carte.getName()+"\n");
						}
					}
				}
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de 6 pi�ces disponible\n");
			}			
		}
	}
}