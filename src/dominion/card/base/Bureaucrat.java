package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	//Constructeur
	public Bureaucrat() {
		super("Bureaucrat", 4);
	}

	//M�thode jouant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			if(!p.getVictoryCards().isEmpty()) {
				int nbCarteVictoryDevoile = 0;
				String rep = p.chooseCard("Choisissez une carte victoire dans votre main � d�voiler", p.getVictoryCards(), false);
				for(int c = 0; c<p.getVictoryCards().size(); c++) {
					if(nbCarteVictoryDevoile < 1 && p.getVictoryCards().get(c) != null && p.getVictoryCards().get(c).getName().equalsIgnoreCase(rep)) {
						Card carteVictory = p.getVictoryCards().get(c);
						System.out.println("\n"+p.getName()+" d�voile une carte victoire : carte "+carteVictory.getName()+"\n");
						p.addDraw(0, carteVictory);
						p.removeFromHand(carteVictory);
						nbCarteVictoryDevoile ++;
					}
				}
			}
			else {
				String mainDevoile = String.format("%s\n", p.cardsInHand().toString());
				System.out.println(p.getName()+" d�voile sa main sans carte victoire :\n"+mainDevoile);
			}			
		}
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			//Le joueur actif re�oit une carte argent si la pile correspondante dans la r�serve n'est pas vide
			Silver silver =  new Silver();
			Card carteGain = p.getGame().removeFromSupply(silver.getName());
			if(carteGain != null) {
				p.addDraw(0, carteGain);
			}
			else {
				System.out.println("\nLa pile Silver de la r�serve est vide\n");
			}
			Moat douves = new Moat();
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null) {
				//Si l'adversaire i ne d�voile pas une carte Moat, il subit l'attaque
				for(int i = 0; i<adversaires.size(); i++) {
					if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
						this.attaquer(adversaires.get(i));
					}
				}	
			}
		}
	}
}