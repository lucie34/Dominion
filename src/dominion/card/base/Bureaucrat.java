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

	public void attaquer(Player p) {
		CardList pileCartesVictory = p.getVictoryCards();
		int nbCarteVictoryDevoile = 0;
		if(!pileCartesVictory.isEmpty()) {
			String rep = p.chooseCard("Choisissez une carte victoire dans votre main", pileCartesVictory, false);
			for(int c = 0; c<pileCartesVictory.size(); c++) {
				if(pileCartesVictory.get(c).getName().equalsIgnoreCase(rep) && nbCarteVictoryDevoile < 1) {
					Card carteVictory = pileCartesVictory.get(c);
					System.out.println("\n"+p.getName()+" dÈvoile une carte victoire : carte "+carteVictory.getName()+"\n");
					p.addDraw(0, carteVictory);
					p.removeFromHand(carteVictory);
					pileCartesVictory.remove(c);
					nbCarteVictoryDevoile ++;
				}
			}
		}
		else {
			String mainDevoile = String.format("%s\n", p.cardsInHand().toString());
			System.out.println(p.getName()+" dÈvoile sa main sans carte victoire : \n"+mainDevoile);
		}
	}

	public void play(Player p) {
		Silver silver =  new Silver();
		Card carteGain = p.getGame().removeFromSupply(silver.getName());
		if(carteGain != null) {
			p.addDraw(0, carteGain);
		}
		else {
			System.out.println("\nLa pile Silver de la rÈserve est vide\n");
		}
		Moat douves = new Moat();
		List<Player> adversaires = p.otherPlayers();
		for(int i = 0; i<adversaires.size(); i++) {
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.attaquer(adversaires.get(i));
			}
		}
	}
}