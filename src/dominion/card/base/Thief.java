package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dÃ©voilent les 2 premiÃ¨res cartes de leur pioche. S'ils dÃ©voilent des cartes TrÃ©sor, ils en Ã©cartent 1 de votre choix. Parmi ces cartes TrÃ©sor Ã©cartÃ©es, recevez celles de votre choix. Les autres cartes dÃ©voilÃ©es sont dÃ©faussÃ©es.
 */
public class Thief extends AttackCard {
	//listes de cartes écartées par le joueur actif dans laquelle il peut récupérer les cartes qu'il souhaite
	private static CardList cartesEcarte = new CardList();
	
	
	//Constructeur
	public Thief() {
		super("Thief", 4);
	}
	
	
	//Méthode réalisant l'action d'attaque de la carte
	public void attaquer(Player p) {
		if(p != null) {
			CardList cartesTresor = new CardList();
			//L'adversaire p dévoile les deux premières cartes de sa pioche
			CardList listeCartesDevoile = new CardList();
			listeCartesDevoile.add(p.drawCard());
			listeCartesDevoile.add(p.drawCard());
			//Récupère le joueur ayant joué la carte Spy
			Player joueurActif = p.getGame().getPlayer(p.getGame().getCurrentPlayerIndex());
			for(Card carte : listeCartesDevoile) {
				if(carte != null) {
					System.out.println("\n"+p.getName()+" dévoile de son deck la carte "+carte.getTypes().get(0)+" : "+carte.getName());
					//Si la carte dévoilée est une carte trésor, elle est ajoutée à la liste de cartes cartesTresor
					if(carte.getTypes().get(0).equals(CardType.Treasure)) {
						cartesTresor.add(carte);
					}
					//Sinon la carte est défaussée par l'adversaire
					else {
						p.gain(carte);
					}
				}
			}//Si l'adversaire a dévoilé une ou plusieurs cartes trésor, le joueur actif choisit d'en faire écarter une
			if(!cartesTresor.isEmpty()) {
				int nbCartesTresorEcarte = 0;
				int nbCartesTresor = cartesTresor.size();
				String instruction = joueurActif.getName()+" : Choisissez de faire écarter une carte trésor à "+p.getName();
				String rep = joueurActif.chooseCard(instruction, cartesTresor, false);
				if(!rep.equalsIgnoreCase("")) {
					for(int i=0; i<nbCartesTresor; i++) {
						if(nbCartesTresorEcarte < 1 && cartesTresor.get(i).getName().equalsIgnoreCase(rep)) {
							nbCartesTresorEcarte++;
							//La carte écartée est ajoutée à la liste cartesEcarte en attribut de classe
							cartesEcarte.add(cartesTresor.get(i));
							cartesTresor.remove(i);
						}
					}
				}
				//Les cartes trésor non écartées sont aussi défaussées par l'adversaire
				for(Card carte : cartesTresor) {
					p.gain(carte);
				}
			}			
		}
	}

	
	//Méthode play jouant la carte
	public void play(Player p) {
		if(p != null) {
			List<Player> adversaires = p.otherPlayers();
			if(adversaires != null ) {
				Moat douves = new Moat();
				//effets de la carte sur tous les adversaires
				for(int i =0; i<adversaires.size(); i++) {
					//Si l'adversaire ne dévoile pas une carte Douves l'immunisant, il subit l'attaque
					if(!douves.devoiler(adversaires.get(i), adversaires.get(i).cardsInHand())) {
						this.attaquer(adversaires.get(i));
					}
				}
				//Si cartesEcarte contient des cartes, le joueur actif peut récupérer celles qu'il souhaite. Les autres sont mises dans le trash du Game
				if(!cartesEcarte.isEmpty()) {
					int nbCartesRecup = 0;
					int nbCartesEcarte = cartesEcarte.size();
					String instruction = p.getName()+" : Choisissez de récupérer une carte trésor écartée ou laissez vide";
					String rep2 = p.chooseCard(instruction, cartesEcarte, true);
					if(!rep2.equalsIgnoreCase("")) {
						for(int i=0; i<nbCartesEcarte; i++) {
							if(nbCartesRecup < 1 && cartesEcarte.get(i).getName().equalsIgnoreCase(rep2)) {
								nbCartesRecup++;
								p.gain(cartesEcarte.get(i));
								cartesEcarte.remove(i);
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