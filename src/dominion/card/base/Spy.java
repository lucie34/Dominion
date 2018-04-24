package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) d√©voilent la premi√®re carte de leur deck. Vous d√©cidez ensuite si chaque carte d√©voil√©e est d√©fauss√©e ou replac√©e sur son deck.
 */
public class Spy extends AttackCard {
	
	public Spy() {
		super("Espion", 4);
	}
	
	public boolean devoiler(Player p, CardList pile) {
		Card carteDevoilee = p.drawCard();
		if(carteDevoilee == null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte ‡ dÈvoiler\n");
			return false;
		}
		System.out.println("\n"+p.getName()+" dÈvoile la premiËre carte de son deck : carte "+carteDevoilee.getName()+"\n");
		List<String> listeChoix= new ArrayList<String>(2);
		String instruction = "Choisissez de dÈfausser ou de remettre sur votre deck la carte dÈvoilÈe (Defausser/Deck)";
		listeChoix.add("Defausser"); 
		listeChoix.add("Deck");
		String rep = p.choose(instruction, listeChoix, false);
		if(rep.equalsIgnoreCase("Defausser")) {
			p.removeFromHand(carteDevoilee);
			p.gain(carteDevoilee);
		}
		else if(rep.equalsIgnoreCase("Deck")) {
			p.removeFromHand(carteDevoilee);
			p.addDeck(0, carteDevoilee);
		}
		return true;
	}

	public void play(Player p) {
		p.drawCard();
		p.incrementActions(1);
		CardList deck = p.getDeck();
		List<Player> adversaires = p.otherPlayers();
		this.devoiler(p, deck);
		for(int i = 0; i<adversaires.size(); i++) {
			deck = adversaires.get(i).getDeck();
			this.devoiler(adversaires.get(i), deck);
		}
	}
	
}