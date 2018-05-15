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
	//listes de cartes �cart�es par le joueur actif dans laquelle il peut r�cup�rer les cartes qu'il souhaite
	private static CardList cartesEcarte = new CardList();
	
	//Constructeur
	public Thief() {
		super("Thief", 4);
	}
	
	//M�thode r�alisant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			CardList cartesTresor = new CardList();
			//L'adversaire p d�voile les deux premi�res cartes de sa pioche
			CardList listeCartesDevoile = new CardList();
			listeCartesDevoile.add(p.drawCard());
			listeCartesDevoile.add(p.drawCard());
			//R�cup�re le joueur ayant jou� la carte Spy
			Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
			for(Card carte : listeCartesDevoile) {
				if(carte != null) {
					System.out.println("\n"+p.getName()+" d�voile de son deck la carte "+carte.getTypes().get(0)+" : "+carte.getName());
					//Si la carte d�voil�e est une carte tr�sor, elle est ajout�e � la liste de cartes cartesTresor
					if(carte.getTypes().get(0).equals(CardType.Treasure)) {
						cartesTresor.add(carte);
					}
					//Sinon la carte est d�fauss�e par l'adversaire
					else {
						p.gain(carte);
					}
				}
			}//Si l'adversaire a d�voil� une ou plusieurs cartes tr�sor, le joueur actif choisit d'en faire �carter une
			if(!cartesTresor.isEmpty()) {
				int nbCartesTresorEcarte = 0;
				int nbCartesTresor = cartesTresor.size();
				String instruction = joueurActif.getName()+" : Choisissez de faire �carter une carte tr�sor � "+p.getName();
				String rep = joueurActif.chooseCard(instruction, cartesTresor, false);
				if(!rep.equalsIgnoreCase("")) {
					for(int i=0; i<nbCartesTresor; i++) {
						if(nbCartesTresorEcarte < 1 && cartesTresor.get(i).getName().equalsIgnoreCase(rep)) {
							nbCartesTresorEcarte++;
							//La carte �cart�e est ajout�e � la liste cartesEcarte en attribut de classe
							cartesEcarte.add(cartesTresor.get(i));
							System.out.println(joueurActif.getName()+" fait �carter la carte "+cartesTresor.get(i).getName()+" au joueur "+p.getName()+"\n");
							cartesTresor.remove(i);
						}
					}
				}
				//Les cartes tr�sor non �cart�es sont aussi d�fauss�es par l'adversaire
				for(Card carte : cartesTresor) {
					p.gain(carte);
				}
			}			
		}
	}
	
	//M�thode play jouant la carte
	public void play(Player p) {
		if(p != null) {
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null ) {
				Moat douves = new Moat();
				//effets de la carte sur tous les adversaires
				for(int i =0; i<adversaires.size(); i++) {
					//Si l'adversaire ne d�voile pas une carte Douves l'immunisant, il subit l'attaque
					if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
						this.attaquer(adversaires.get(i));
					}
				}
				//Si cartesEcarte contient des cartes, le joueur actif peut r�cup�rer celles qu'il souhaite. Les autres sont mises dans le trash du Game
				if(!cartesEcarte.isEmpty()) {
					boolean carteTrouve;
					int nbCartesEcarte;
					String instruction = p.getName()+" : Choisissez de r�cup�rer une carte tr�sor �cart�e ou laissez vide";
					String rep2="init";
					while(!rep2.equalsIgnoreCase("")) {
						rep2 = p.chooseCard(instruction, cartesEcarte, true);
						if(!rep2.equalsIgnoreCase("")) {
							nbCartesEcarte = cartesEcarte.size();
							carteTrouve = false;
							for(int i=0; i<nbCartesEcarte; i++) {
								if(!carteTrouve && cartesEcarte.get(i).getName().equalsIgnoreCase(rep2)) {
									carteTrouve = true;
									p.gain(cartesEcarte.get(i));
									System.out.println(p.getName()+" r�cup�re la carte tr�sor "+cartesEcarte.get(i).getName()+"\n");
									cartesEcarte.remove(i);
								}
							}
						}
					}
					for(Card carte : cartesEcarte) {
						p.getGame().addInTrash(carte);
					}
				}			
			}
		}
	}
}