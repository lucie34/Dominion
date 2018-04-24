package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {
	
	public Thief() {
		super("Voleur", 4);
	}
	
	public boolean devoiler(Player p, CardList pile) {
		List<Card> listeCartesDevoile = new ArrayList<Card>(2);
		listeCartesDevoile.add(p.drawCard());
		listeCartesDevoile.add(p.drawCard());
		int nbCartesTresorEcarte = 0;
		//R�cup�re le joueur ayant jou� la carte Voleur
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		String instruction  = "Le joueur actif "+joueurActif.getName()+" peut d�cider que "+p.getName()+" �carte cette carte tr�sor. "+joueurActif.getName()+" voulez-vous faire �carter cette carte ? (O/N)";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("O"); listeChoix.add("N");
		String rep = new String();
		if(listeCartesDevoile.get(0) != null && listeCartesDevoile.get(1) != null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte � d�voiler\n");
			return false;
		}
		for(int i = 0; i<listeCartesDevoile.size(); i++) {
			if(listeCartesDevoile.get(i) != null && !listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
				System.out.println("\n"+p.getName()+" d�voile la carte "+listeCartesDevoile.get(i).getName()+" de son deck\n");
				p.removeFromHand(listeCartesDevoile.get(i));
				p.gain(listeCartesDevoile.get(i));
			}
			if(listeCartesDevoile.get(i) != null && listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
				System.out.println("\n"+p.getName()+" d�voile la carte tr�sor "+listeCartesDevoile.get(i).getName()+" de son deck\n");
				if(nbCartesTresorEcarte < 1) {
					rep = joueurActif.choose(instruction, listeChoix, false);
					if(rep.equalsIgnoreCase("O")) {
						nbCartesTresorEcarte ++;
						p.removeFromHand(listeCartesDevoile.get(i));
						p.getGame().addInTrash(listeCartesDevoile.get(i));
						String question = "Joueur actif "+joueurActif.getName()+" :  voulez-vous r�cup�rer cette carte tresor "+listeCartesDevoile.get(i).getName()+" ? (O/N)";
						rep = joueurActif.choose(question, listeChoix, false);
						if(rep.equalsIgnoreCase("O")) {
							joueurActif.gain(listeCartesDevoile.get(i));
							p.getGame().getTrashedCards().remove(p.getGame().getTrashedCards().size()-1);
						}
					}
					else {
						p.removeFromHand(listeCartesDevoile.get(i));
						p.gain(listeCartesDevoile.get(i));
					}
				}
				else {
					p.removeFromHand(listeCartesDevoile.get(i));
					p.gain(listeCartesDevoile.get(i));
				}
			}
		}
		return true;
	}
	
	public void play(Player p) {
		List<Player> adversaires = p.otherPlayers();
		CardList deck;
		for(int i =0; i<adversaires.size(); i++) {
			deck = adversaires.get(i).getDeck();
			this.devoiler(adversaires.get(i), deck);
		}
	}
	
}