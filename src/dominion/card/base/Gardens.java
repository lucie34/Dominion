package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi √† l'unit√© inf√©rieure).
 */
public class Gardens extends VictoryCard {

	//Constructeur
	public Gardens() {
		super("Gardens", 4);
	}

	//MÈthode jouant la carte
	public void play(Player p) {
		// TODO Auto-generated method stub
	}
	
	//MÈthode calculant la valeur de cette carte victoire
	public int victoryValue(Player p) {
		if(p != null) {
			int valeur = (p.totalCards().size())/10;
			return valeur;
		}
		return 0;
	}
}