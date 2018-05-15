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
			//Re�oit une carte co�tant 4 pi�ces ou moins
			String instruction = "S�lectionnez une carte de la r�serve � recevoir, elle doit co�ter au plus 4 pi�ces";
			boolean carteTrouve;
			CardList reserve = p.getGame().availableSupplyCards();
			CardList listeCartes = new CardList();
			//R�cup�re les cartes disponibles de la r�serve co�tant 4 pi�ces ou moins
			for(Card carte : reserve) {
				if(carte != null && carte.getCost()<=4) {
					listeCartes.add(carte);
				}
			}
			if(!listeCartes.isEmpty()) {
				//Fait choisir une carte au joueur parmi les cartes valides
				String choix = p.chooseCard(instruction, listeCartes, false);
				carteTrouve = false;
				for(int c = 0; c<listeCartes.size(); c++) {
					if(!carteTrouve && listeCartes.get(c).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.gain(choix);
						if(carte != null) {
							System.out.println(p.getName()+" re�oit une carte "+carte.getName()+"\n");
						}
					}
				}
			}
			else {
				System.out.println("Aucune carte de la r�serve � moins de 5 pi�ces disponible");
			}			
		}
	}
}