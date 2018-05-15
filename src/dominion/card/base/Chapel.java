package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Ã‰cartez jusqu'Ã  4 cartes de votre main.
 */
public class Chapel extends ActionCard {

	public Chapel() {
		super("Chapel", 2);
	}

	public void play(Player p) {
		if(p != null) {
			boolean carteTrouve;
			int nombreCartesEcarte = 0;
			String instruction = "Vous pouvez écarter 4 cartes de votre main, choisissez une carte à écarter ou laissez vide";
			String choix = "init";
			while(nombreCartesEcarte < 4 && !choix.equalsIgnoreCase("")) {
				choix = p.chooseCard(instruction, p.cardsInHand(), true);
				if(!choix.equalsIgnoreCase("")) {
					carteTrouve = false;
					for(int i=0; i<p.cardsInHand().size(); i++) {
						if(!carteTrouve && p.cardsInHand().get(i).getName().equalsIgnoreCase(choix)) {
							carteTrouve = true;
							Card carte = p.cardsInHand().get(i);
							p.getGame().addInTrash(carte);
							p.removeFromHand(carte);
							nombreCartesEcarte++;
							System.out.println("\n"+p.getName()+" a écarté une carte");
						}
					}
				}
			}
			System.out.println("\n"+p.getName()+" a écarté "+nombreCartesEcarte+" cartes");
		}			
		}
}