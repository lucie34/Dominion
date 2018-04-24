package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires d√©voilent une carte Victoire et la placent sur leur deck (sinon ils d√©voilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {
	
	public Bureaucrat() {
		super("Bureaucrate", 4);
	}
	
	public boolean devoiler(Player p, CardList pile) {
		boolean possedeVictoryCard = false;
		for(int c = 0; c<pile.size(); c++) {
			if(pile.get(c).getTypes().get(0).equals(CardType.Victory)) {
				possedeVictoryCard = true;
			}
		}
		if(possedeVictoryCard) {
			Card carteVictory = this;//initialisation
			while(!carteVictory.getTypes().get(0).equals(CardType.Victory)) {
				String rep = p.chooseCard("Choisissez une carte victoire dans votre main", pile, false);
				for(int c = 0; c<pile.size(); c++) {
					if(pile.get(c).getName().equalsIgnoreCase(rep)) {
						carteVictory = pile.get(c);
					}
				}
			}
			System.out.println(p.getName()+" dÈvoile une carte victoire "+carteVictory.getName());
			p.addDeck(carteVictory);
			p.removeFromHand(carteVictory);
		}
		else {
			String mainDevoile = String.format("Hand: %s\n", pile.toString());
			System.out.println(p.getName()+" dÈvoile sa main sans carte victoire : \n"+mainDevoile);
		}
		return true;
	}

	public void play(Player p) {
		Silver silver =  new Silver();
		Card carteGain = p.getGame().removeFromSupply(silver.getName());
		p.addDeck(carteGain);
		if(carteGain == null) {
			System.out.println("\nLa pile Silver de la rÈserve est vide\n");
		}
		
		CardList main = p.cardsInHand();
		List<Player> adversaires = p.otherPlayers();
		for(int i = 0; i<adversaires.size(); i++) {
			this.devoiler(adversaires.get(i), main); 
		}
	}
}