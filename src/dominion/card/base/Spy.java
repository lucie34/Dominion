package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur pioche. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur la pioche.
 */
public class Spy extends AttackCard {
	
	public Spy() {
		super("Spy", 4);
	}
	
	public void attaquer(Player p) {
		Card carteDevoilee = p.drawCard();
		//R�cup�re le joueur ayant jou� la carte Espion
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		if(carteDevoilee == null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte � d�voiler\n");
		}
		else {
			System.out.println("\n"+p.getName()+" d�voile la premi�re carte de son deck : carte "+carteDevoilee.getName()+"\n");
			List<String> listeChoix= new ArrayList<String>(2);
			//Le joueur actif choisit de faire d�fausser ou non la carte d�voil�e par l'adversaire
			String instruction = joueurActif.getName()+" : Choisissez de faire d�fausser ou de faire remettre sur son deck la carte "+carteDevoilee.getName()+" d�voil�e par le joueur "+p.getName()+" (Defausser/Deck)";
			listeChoix.add("Defausser"); 
			listeChoix.add("Deck");
			String rep = joueurActif.choose(instruction, listeChoix, false);
			//La fait d�fausser
			if(rep.equalsIgnoreCase("Defausser")) {
				p.gain(carteDevoilee);
			}
			//la fait remettre sur son deck
			else if(rep.equalsIgnoreCase("Deck")) {
				p.addDraw(0, carteDevoilee);
			}
		}
	}

	public void play(Player p) {
		p.incrementHand(p.drawCard());
		p.incrementActions(1);
		this.attaquer(p);
		List<Player> adversaires = p.otherPlayers();
		Moat douves = new Moat();
		for(int i = 0; i<adversaires.size(); i++) {
			//V�rifie que l'adversaire n'a pas de carte Douves dans sa main l'immunisant
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.attaquer(adversaires.get(i));
			}
		}
	}
	
}