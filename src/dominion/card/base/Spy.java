package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dÃ©voilent la premiÃ¨re carte de leur pioche. Vous dÃ©cidez ensuite si chaque carte dÃ©voilÃ©e est dÃ©faussÃ©e ou replacÃ©e sur la pioche.
 */
public class Spy extends AttackCard {
	
	public Spy() {
		super("Spy", 4);
	}
	
	public void attaquer(Player p) {
		Card carteDevoilee = p.drawCard();
		//Récupère le joueur ayant joué la carte Espion
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		if(carteDevoilee == null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte à dévoiler\n");
		}
		else {
			System.out.println("\n"+p.getName()+" dévoile la première carte de son deck : carte "+carteDevoilee.getName()+"\n");
			List<String> listeChoix= new ArrayList<String>();
			//Le joueur actif choisit de faire défausser ou non la carte dévoilée par l'adversaire
			String instruction = joueurActif.getName()+" : Voulez-vous faire défausser cette carte "+carteDevoilee.getName()+" dévoilée par le joueur "+p.getName()+" (y/n)";
			listeChoix.add("y"); 
			listeChoix.add("n");
			String rep = joueurActif.choose(instruction, listeChoix, false);
			//La fait défausser
			if(rep.equalsIgnoreCase("y")) {
				p.gain(carteDevoilee);
			}
			//la fait remettre sur son deck
			else {
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
			//Vérifie que l'adversaire n'a pas de carte Douves dans sa main l'immunisant
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.attaquer(adversaires.get(i));
			}
		}
	}
	
}