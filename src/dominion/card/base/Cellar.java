package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {
	
	//Constructeur
	public Cellar() {
		super("Cellar", 2);
	}
	
	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementActions(1);
			//R�cup�re le nombre de cartes d�fauss�es pour pouvoir en piocher la m�me quantit� par la suite
			int nombreCartesDefausse = 0;
			String instruction = "Choisissez une carte de votre main � d�fausser ou laissez vide";
			String choix = "init";
			//Permet au joueur de choisir une carte � d�fausser tant qu'il le souhaite ou tant que sa main n'est pas vide
			while(!choix.equalsIgnoreCase("")) {
				choix = p.chooseCard(instruction, p.cardsInHand(), true);
				if(!choix.equalsIgnoreCase("")) {
					Card carte = p.cardsInHand().getCard(choix);
					p.gain(carte);
					p.removeFromHand(carte);
					nombreCartesDefausse++;
				}
			}
			for(int i=0; i<nombreCartesDefausse; i++) {
				p.incrementHand(p.drawCard());
			}
			System.out.println(p.getName()+" a d�fauss� "+nombreCartesDefausse+" cartes\n");			
		}
	}
}