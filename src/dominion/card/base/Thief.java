package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires d√©voilent les 2 premi√®res cartes de leur pioche. S'ils d√©voilent des cartes Tr√©sor, ils en √©cartent 1 de votre choix. Parmi ces cartes Tr√©sor √©cart√©es, recevez celles de votre choix. Les autres cartes d√©voil√©es sont d√©fauss√©es.
 */
public class Thief extends AttackCard {
	
	public Thief() {
		super("Thief", 4);
	}

	public void attaquer(Player p) {
		CardList cartesTresor = new CardList();
		CardList listeCartesDevoile = new CardList();
		listeCartesDevoile.add(p.drawCard());
		listeCartesDevoile.add(p.drawCard());
		//RÈcupËre le joueur ayant jouÈ la carte Spy
		Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
		for(Card carte : listeCartesDevoile) {
			if(carte != null) {
				System.out.println("\n"+p.getName()+" dÈvoile de son deck la carte "+carte.getTypes().get(0)+" : "+carte.getName());
				if(carte.getTypes().get(0).equals(CardType.Treasure)) {
					cartesTresor.add(carte);
				}
				else {
					p.gain(carte);
				}
			}
		}
		System.out.println("\n"+cartesTresor.toString()+"TTTTTTTTTTTTTTTTTTT 1");
		System.out.println("\nDÈfausse P 1 : "+p.getDiscard().toString());
		if(!cartesTresor.isEmpty()) {
			CardList cartesEcarte = new CardList();
			int nbCartesTresorEcarte = 0;
			int nbCartesTresor = cartesTresor.size();
			String instruction = joueurActif.getName()+" : Choisissez de faire Ècarter une carte trÈsor ‡ "+p.getName();
			String rep = joueurActif.chooseCard(instruction, cartesTresor, false);
			for(int i=0; i<nbCartesTresor; i++) {
				if(nbCartesTresorEcarte < 1 && cartesTresor.get(i).getName().equalsIgnoreCase(rep)) {
					nbCartesTresorEcarte++;
					cartesEcarte.add(cartesTresor.get(i));
					cartesTresor.remove(i);
				}
			}
			System.out.println(cartesEcarte.toString()+"EEEEEEEEEEEEEEEEEEEEE 1");
			System.out.println(cartesTresor.toString()+"TTTTTTTTTTTTTTTTTTTTT 2");
			for(Card carte : cartesTresor) {
				p.gain(carte);
			}
			System.out.println("DÈfausse P 2 : "+p.getDiscard().toString());
			System.out.println("DÈfausse JA 1 : "+joueurActif.getDiscard().toString());
			if(!cartesEcarte.isEmpty()) {
				int nbCartesRecup = 0;
				int nbCartesEcarte = cartesEcarte.size();
				instruction = joueurActif.getName()+" : Choisissez de rÈcupÈrer une carte trÈsor ÈcartÈe ou laissez vide";
				System.out.println("OK");
				rep = joueurActif.chooseCard(instruction, cartesEcarte, true);
				System.out.println("\nREP = "+rep);
				if(!rep.equalsIgnoreCase("")) {
					for(int i=0; i<nbCartesEcarte; i++) {
						if(nbCartesRecup < 1 && cartesEcarte.get(i).getName().equalsIgnoreCase(rep)) {
							nbCartesRecup++;
							joueurActif.gain(cartesEcarte.get(i));
							cartesEcarte.remove(i);
						}
					}
				}
				System.out.println("DÈfausse JA 2 : "+joueurActif.getDiscard().toString());
				System.out.println(cartesEcarte.toString()+"EEEEEEEEEEEEEEEEEEEEE 2");
				for(Card carte : cartesEcarte) {
					p.getGame().addInTrash(carte);
				}
			}
		}
	}

	public void play(Player p) {
		List<Player> adversaires = p.otherPlayers();
		Moat douves = new Moat();
		for(int i =0; i<adversaires.size(); i++) {
			//VÈrifie que l'adversaire n'a pas dans sa main une carte Douves l'immunisant
			if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
				this.attaquer(adversaires.get(i));
			}
		}
	}
}