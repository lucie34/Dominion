package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * D√©faussez autant de cartes que vous voulez.
 * +1 Carte par carte d√©fauss√©e.
 */
public class Cellar extends ActionCard {
	
	public Cellar() {
		super("Cellar", 2);
	}
	
	public void play(Player p) {
		p.incrementActions(1);
		boolean carteTrouve;
		int nombreCartesDefausse = 0;
		String instruction = "Choisissez une carte de votre main ‡ dÈfausser ou laissez vide";
		String choix = "init";
		while(!choix.equalsIgnoreCase("")) {
			choix = p.chooseCard(instruction, p.cardsInHand(), true);
			if(!choix.equalsIgnoreCase("")) {
				carteTrouve = false;
				for(int i=0; i<p.cardsInHand().size(); i++) {
					if(!carteTrouve && p.cardsInHand().get(i).getName().equalsIgnoreCase(choix)) {
						carteTrouve = true;
						Card carte = p.cardsInHand().get(i);
						p.gain(carte);
						p.removeFromHand(carte);
						nombreCartesDefausse++;
						System.out.println("\n"+p.getName()+" a defaussÈ une carte");
					}
				}
			}
		}
		for(int i=0; i<nombreCartesDefausse; i++) {
			p.incrementHand(p.drawCard());
		}
		System.out.println("\n"+p.getName()+" a dÈfaussÈ "+nombreCartesDefausse+" cartes");
	}
}