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
			CardList listeCartes = new CardList();
			for(Card carte : p.getGame().availableSupplyCards()) {
				if(carte != null && carte.getCost()<=5) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				String choix = p.chooseCard(instruction, listeCartes, false);
				p.gain(choix);
				System.out.println(p.getName()+" re�oit la carte "+choix+"\n");
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de 6 pi�ces disponible\n");
			}			
		}
	}
}