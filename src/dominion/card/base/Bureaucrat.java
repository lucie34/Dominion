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
		super("Bureaucrat", 4);
	}

	public boolean devoiler(Player p, CardList pile) {
		int nbCarteVictoryDevoile = 0;
		if(!pile.isEmpty()) {
			String rep = p.chooseCard("Choisissez une carte victoire dans votre main", pile, false);
			for(int c = 0; c<pile.size(); c++) {
				if(pile.get(c).getName().equalsIgnoreCase(rep) && nbCarteVictoryDevoile < 1) {
					Card carteVictory = pile.get(c);
					System.out.println("\n"+p.getName()+" dÈvoile une carte victoire "+carteVictory.getName()+"\n");
					p.gain(carteVictory);
					p.removeFromHand(carteVictory);
					nbCarteVictoryDevoile ++;
				}
			}
		}
		else {
			String mainDevoile = String.format("%s\n", p.cardsInHand().toString());
			System.out.println(p.getName()+" dÈvoile sa main sans carte victoire : \n"+mainDevoile);
		}
		return true;
	}

	public void play(Player p) {
		Silver silver =  new Silver();
		Card carteGain = p.gain(silver.getName());
		if(carteGain == null) {
			System.out.println("\nLa pile Silver de la rÈserve est vide\n");
		}

		List<Player> adversaires = p.otherPlayers();
		for(int i = 0; i<adversaires.size(); i++) {
			Moat douves = new Moat();
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.devoiler(adversaires.get(i), adversaires.get(i).getVictoryCards());
			}
		}
	}
}