package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {
	
	//Constructeur
	public Moat() {
		super("Moat", 2);
	}
	
	//permet au joueur poss�dant cette carte de choisir s'il veut la d�voiler ou non en cas d'attaque d'un autre joueur
	public boolean devoiler(Player p, CardList pile) {
		if(p != null && pile != null) {
			if(pile.getCard(this.getName()) != null) {
				String instruction = p.getName()+" : Voulez-vous montrer votre carte Douves ? (y/n)\n";
				List<String> listeChoix = new ArrayList<String>();
				listeChoix.add("y");
				listeChoix.add("n");
				String rep = p.choose(instruction, listeChoix, false);
				if(rep.equalsIgnoreCase("y")) {
					System.out.println("\n"+p.getName()+" d�voile sa carte Douves\n");
					return true;
				}	
			}
		}
		return false;
	}

	//M�thode jouant la carte
	public void play(Player p) {
		if(p != null) {
			for(int i = 0; i<2; i++) {
				p.incrementHand(p.drawCard());
			}			
		}
	}
}