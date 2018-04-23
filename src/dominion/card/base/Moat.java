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
	public Moat() {
		super("Douves", 2);
	}
	
	//permet au joueur poss�dant cette carte de choisir s'il veut la d�voiler ou non en cas d'attaque d'un autre joueur
	public boolean choisirDevoilerDouves(Player p) {
		String instruction = p.getName()+" : Voulez-vous montrer votre carte Douves ? (O/N)\n";
		List<String> listeChoix = new ArrayList<String>(2);
		listeChoix.add("O");
		listeChoix.add("N");
		String rep = p.choose(instruction, listeChoix, false);
		if(rep.compareToIgnoreCase("N") == 0) {
			return false;
		}
		return true;
	}
	
	
	public void play(Player p) {
		for(int i = 0; i<2; i++) {
			p.drawCard();
		}
	}
}