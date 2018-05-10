package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {

	public Library() {
		super("Library", 5);
	
	}

	@Override
	public void play(Player p) {
		CardList deCote = new CardList();
		List<String> choices = new ArrayList<String>();
		choices.add("Y pour oui");
		choices.add("N pour non");
		while(p.cardsInHand().size() < 7 && !(p.getDiscard().isEmpty() && p.getDraw().isEmpty())) {
			Card carte = p.drawCard();
			if(carte.getTypes().get(0).equals(CardType.Action)) {
				String instruction = "Souhaitez-vous mettre de cot� la carte action : " + carte.getName();
				String reponse = p.choose(instruction, choices, false);
				if(reponse.equalsIgnoreCase("Y")) {
					deCote.add(carte);//met la carte action de c�t�
				}
				else {
					p.incrementHand(carte);//met la carte action dans la main
				}
			}
			else {
				p.incrementHand(carte); // met la carte dans la main
			}
		}
		for(int i=0; i<deCote.size(); i++) {
			p.gain(deCote.get(i)); //met les cartes mises de cot� dans la d�fausse
		}
		deCote.removeAll(deCote);
	}
}