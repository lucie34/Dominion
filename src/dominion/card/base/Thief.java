package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires d√©voilent les 2 premi√®res cartes de leur deck. S'ils d√©voilent des cartes Tr√©sor, ils en √©cartent 1 de votre choix. Parmi ces cartes Tr√©sor √©cart√©es, recevez celles de votre choix. Les autres cartes d√©voil√©es sont d√©fauss√©es.
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
		//RÈcupËre le joueur ayant jouÈ la carte Voleur
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		String instruction  = "Le joueur actif "+joueurActif.getName()+" peut dÈcider que "+p.getName()+" Ècarte cette carte trÈsor. "+joueurActif.getName()+" voulez-vous faire Ècarter cette carte ? (O/N)";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("O"); listeChoix.add("N");
		String rep = new String();
		//Si l'adversaire n'a plus aucune deck et aucune dÈfausse ‡ mÈlanger
		if(listeCartesDevoile.get(0) == null && listeCartesDevoile.get(1) == null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte ‡ dÈvoiler\n");
			return false;
		}
		//Sinon
		for(int i = 0; i<listeCartesDevoile.size(); i++) {
			//La carte dÈvoilÈe n'est pas une carte trÈsor
			if(listeCartesDevoile.get(i) != null && !listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
				System.out.println("\n"+p.getName()+" dÈvoile la carte "+listeCartesDevoile.get(i).getName()+" de son deck\n");
				p.gain(listeCartesDevoile.get(i));
			}
			//La carte dÈvoilÈe est une carte trÈsor
			if(listeCartesDevoile.get(i) != null && listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
				System.out.println("\n"+p.getName()+" dÈvoile la carte trÈsor "+listeCartesDevoile.get(i).getName()+" de son deck\n");
				//Une seule carte trÈsor peut Ítre ÈcartÈe
				if(nbCartesTresorEcarte < 1) {
					//Le joueur actif dÈcide de faire Ècarter cette carte trÈsor ou non par l'adversaire
					rep = joueurActif.choose(instruction, listeChoix, false);
					//Si le joueur actif dÈcide de la faire Ècarter
					if(rep.equalsIgnoreCase("O")) {
						nbCartesTresorEcarte ++;
						p.getGame().addInTrash(listeCartesDevoile.get(i));
						//le joueur actif dÈcide de rÈcupÈrer ou non la carte trÈsor ÈcartÈe par l'adversaire
						String question = "Joueur actif "+joueurActif.getName()+" :  voulez-vous rÈcupÈrer cette carte tresor "+listeCartesDevoile.get(i).getName()+" ? (O/N)";
						rep = joueurActif.choose(question, listeChoix, false);
						//Si le joueur actif la rÈcupËre
						if(rep.equalsIgnoreCase("O")) {
							joueurActif.gain(listeCartesDevoile.get(i));
							p.getGame().getTrashedCards().remove(p.getGame().getTrashedCards().size()-1);
						}
					}
					//Si le joueur actif ne souhaite pas faire Ècarter cette carte trÈsor
					else {
						p.gain(listeCartesDevoile.get(i));
					}
				}
				//Si une carte trÈsor a dÈj‡ ÈtÈ ÈcartÈe
				else {
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
			//VÈrifie que l'adversaire n'a pas dans sa main une carte Douves l'immunisant
			Moat douves = new Moat();
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.devoiler(adversaires.get(i), deck);
			}
		}
	}
	
}