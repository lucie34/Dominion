package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * DÃ©faussez autant de cartes que vous voulez.
 * +1 Carte par carte dÃ©faussÃ©e.
 */
public class Cellar extends ActionCard {
	
	//Constructeur
	public Cellar() {
		super("Cellar", 2);
	}
	
	//Méthode jouant la carte
	public void play(Player p) {
		if(p != null) {
			p.incrementActions(1);
			//Récupère le nombre de cartes défaussées pour pouvoir en piocher la même quantité par la suite
			int nombreCartesDefausse = 0;
			String instruction = "Choisissez une carte de votre main à défausser ou laissez vide";
			String choix = "init";
			//Permet au joueur de choisir une carte à défausser tant qu'il le souhaite ou tant que sa main n'est pas vide
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
			System.out.println(p.getName()+" a défaussé "+nombreCartesDefausse+" cartes\n");			
		}
	}
}