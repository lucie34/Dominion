package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {

	//Constructeur
	public Workshop() {
		super("Workshop", 3);
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			CardList listeCartes = new CardList();
			//R�cup�re les cartes disponibles de la r�serve co�tant 4 pi�ces ou moins
			for(Card carte : p.getGame().availableSupplyCards()) {
				if(carte != null && carte.getCost()<=4) {
					listeCartes.add(carte);
				}
			}
			//Fait choisir une carte au joueur parmi les cartes valides s'il y en a
			if(!listeCartes.isEmpty()) {
				String instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus 4 pi�ces";
				String choix = p.chooseCard(instruction, listeCartes, false);
				p.gain(choix);
				System.out.println(p.getName()+" re�oit une carte "+choix+"\n");
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de 5 pi�ces disponible");
			}			
		}
	}
}