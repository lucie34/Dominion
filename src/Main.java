import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

/**
 * Classe pour l'exécution d'une partie de Dominion
 */
class Main {
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le 
		// tableau)
		String[] playerNames = new String[]{"Marco", "Polo"};
		// Prépare les piles "royaume" de la réserve (hors cartes communes)
		List<CardList> kingdomStacks = new ArrayList<CardList>();
		CardList stack;
		CardList stack2;
		CardList stack3;
		CardList stack4;
		CardList stack5;
		CardList stack6;
		CardList stack7;
		CardList stack8;
		CardList stack9;
		CardList stack10;
		// Ajouter un bloc pour chaque carte royaume à utiliser
		// cartes village
		stack = new CardList();
		for (int i = 0; i < 10; i++) {
			stack.add(new Village());
		}
		kingdomStacks.add(stack);
		//cartes Cellar
		stack2 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack2.add(new Cellar());
		}
		kingdomStacks.add(stack2);
		//cartes Market
		stack3 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack3.add(new Market());
		}
		kingdomStacks.add(stack3);
		//cartes Militia
		stack4 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack4.add(new Militia());
		}
		kingdomStacks.add(stack4);
		//cartes Mine
		stack5 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack5.add(new Mine());
		}
		kingdomStacks.add(stack5);
		//cartes Moat
		stack6 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack6.add(new Moat());
		}
		kingdomStacks.add(stack6);
		//cartes Remodel
		stack7 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack7.add(new Remodel());
		}
		kingdomStacks.add(stack7);
		//cartes Smithy
		stack8 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack8.add(new Smithy());
		}
		kingdomStacks.add(stack8);
		//cartes Woodcutter
		stack9 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack9.add(new Woodcutter());
		}
		kingdomStacks.add(stack9);
		//cartes Workshop
		stack10 = new CardList();
		for (int i = 0; i < 10; i++) {
			stack10.add(new Workshop());
		}
		kingdomStacks.add(stack10);
		
		// Instancie et exécute une partie
		Game g = new Game(playerNames, kingdomStacks);
		g.run();
	}
}