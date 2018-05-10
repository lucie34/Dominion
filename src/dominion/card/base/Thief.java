package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur pioche. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {
	
	public Thief() {
		super("Thief", 4);
	}
	
	public void attaquer(Player p) {
		List<Card> listeCartesDevoile = new ArrayList<Card>(2);
		listeCartesDevoile.add(p.drawCard());
		listeCartesDevoile.add(p.drawCard());
		int nbCartesTresorEcarte = 0;
		//R�cup�re le joueur ayant jou� la carte Voleur
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("y"); 
		listeChoix.add("n");
		String instruction = new String();
		String rep = new String();
		//Si l'adversaire n'a plus aucune deck et aucune d�fausse � m�langer
		if(listeCartesDevoile.get(0) == null && listeCartesDevoile.get(1) == null) {
			System.out.println("\n"+p.getName()+" n'a pas de carte � d�voiler\n");
		}
		//Sinon
		else {
			for(int i = 0; i<listeCartesDevoile.size(); i++) {
				//La carte d�voil�e n'est pas une carte tr�sor
				if(listeCartesDevoile.get(i) != null && !listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
					System.out.println("\n"+p.getName()+" d�voile la carte "+listeCartesDevoile.get(i).getName()+" de son deck\n");
					p.gain(listeCartesDevoile.get(i));
				}
				//La carte d�voil�e est une carte tr�sor
				else if(listeCartesDevoile.get(i) != null && listeCartesDevoile.get(i).getTypes().get(0).equals(CardType.Treasure)) {
					System.out.println("\n"+p.getName()+" d�voile la carte tr�sor "+listeCartesDevoile.get(i).getName()+" de son deck\n");
					//Une seule carte tr�sor peut �tre �cart�e
					if(nbCartesTresorEcarte < 1) {
						//Le joueur actif d�cide de faire �carter cette carte tr�sor ou non par l'adversaire
						instruction  = "Le joueur actif "+joueurActif.getName()+" peut d�cider que "+p.getName()+" �carte cette carte tr�sor. "+joueurActif.getName()+" : voulez-vous faire �carter cette carte ? (y/n)";
						rep = joueurActif.choose(instruction, listeChoix, false);
						//Si le joueur actif d�cide de la faire �carter
						if(rep.equalsIgnoreCase("y")) {
							nbCartesTresorEcarte++;
							//le joueur actif d�cide de r�cup�rer ou non la carte tr�sor �cart�e par l'adversaire
							instruction = "Joueur actif "+joueurActif.getName()+" :  voulez-vous r�cup�rer cette carte tresor "+listeCartesDevoile.get(i).getName()+" ? (y/n)";
							rep = joueurActif.choose(instruction, listeChoix, false);
							//Si le joueur actif la r�cup�re
							if(rep.equalsIgnoreCase("y")) {
								joueurActif.gain(listeCartesDevoile.get(i));
							}
							//Sinon
							else {
								p.getGame().addInTrash(listeCartesDevoile.get(i));
							}
						}
						//Si le joueur actif ne souhaite pas faire �carter cette carte tr�sor
						else {
							p.gain(listeCartesDevoile.get(i));
						}
					}
					//Si une carte tr�sor a d�j� �t� �cart�e
					else {
						p.gain(listeCartesDevoile.get(i));
					}
				}
			}
		}
	}

	public void play(Player p) {
		List<Player> adversaires = p.otherPlayers();
		for(int i =0; i<adversaires.size(); i++) {
			//V�rifie que l'adversaire n'a pas dans sa main une carte Douves l'immunisant
			Moat douves = new Moat();
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.attaquer(adversaires.get(i));
			}
		}
	}
	
}