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
		CardList listeVictoryCards = p.getVictoryCards();
		int nbCarteVictoryDevoile = 0;
		if(!listeVictoryCards.isEmpty()) {
			String rep = p.chooseCard("Choisissez une carte victoire dans votre main", listeVictoryCards, false);
			for(int c = 0; c<listeVictoryCards.size(); c++) {
				if(listeVictoryCards.get(c).getName().equalsIgnoreCase(rep) && nbCarteVictoryDevoile < 1) {
					Card carteVictory = listeVictoryCards.get(c);
					System.out.println("\n"+p.getName()+" dÈvoile une carte victoire "+carteVictory.getName()+"\n");
					p.addDeck(0, carteVictory);
					p.removeFromHand(carteVictory);
					nbCarteVictoryDevoile ++;
				}
			}
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
		p.addDeck(0, carteGain);
		if(carteGain == null) {
			System.out.println("\nLa pile Silver de la rÈserve est vide\n");
		}

		List<Player> adversaires = p.otherPlayers();
		for(int i = 0; i<adversaires.size(); i++) {
			CardList main = adversaires.get(i).cardsInHand();
			Moat douves = new Moat();
			if(!douves.devoiler(adversaires.get(i), main)) {
				this.devoiler(adversaires.get(i), main);
			}
		}
	}
}