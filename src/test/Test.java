package test;

import java.io.*;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

public abstract class Test {
	// Titre du test
	private String title;
	// Nombre total de tests réussis
	private int nb_pass = 0;
	// Nombre total de tests ratés
	public int nb_fail = 0;
	// Nombre total d'exceptions levées
	private int nb_error = 0;
	// Indique si le test courant est correct
	private boolean testOk;
	
	public void reset() {
		this.testOk = true;
	}
	
	public void check(boolean test) {
		this.testOk &= test;
	}
	
	public boolean isPassed() {
		return this.testOk;
	}
	
	public void pass() {
		this.nb_pass += 1;
	}
	
	public void fail() {
		this.nb_fail += 1;
	}
	
	public void error() {
		this.nb_error += 1;
	}
	
	public int nb_test() {
		return this.nb_pass + this.nb_fail + this.nb_error;
	}
	
	public abstract void executeTests();
	
	public void run() {
		this.executeTests();
		System.out.println("--");
		System.out.println("Tests effectués : " + this.nb_test());
		System.out.println("Succès : " + this.nb_pass);
		System.out.println("Échecs : " + nb_fail);
		System.out.println("Erreurs : " + nb_error);
		System.out.println();
	}
	
	/*** Méthodes statiques ***/
	
	/**
	 * Convertit une CardList en liste de chaînes de caractères (les noms des
	 * cartes)
	 */
	public static String[] cardsToString (CardList l) {
		String[] result = new String[l.size()];
		for (int i = 0; i < l.size(); i++) {
			result[i] = l.get(i).getName();
		}
		return result;
	}
	
	/**
	 * Teste si une CardList contient exactement les cartes indiquées dans la
	 * chaîne de caractères `namesString` (noms de cartes séparées par des
	 * virgules)
	 */
	public static boolean isList(CardList cards, String namesString) {
		String[] names = namesString.split(",\\s*");
		String[] cardNames = cardsToString(cards);
		for (int i = 0; i < names.length; i++) {
			if (!names[i].equals(cardNames[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Teste si une CardList contient exactement les cartes indiquées dans la
	 * chaîne `namesString` (noms de cartes séparées par des virgules). Les deux
	 * listes doivent avoir les mêmes éléments, avec les mêmes multiplicités
	 * mais l'ordre n'a pas d'importance.
	 */
	public static boolean hasCards(CardList cards, String namesString) {
		String[] names = namesString.split(",\\s*");
		Arrays.sort(names);
		String[] cardNames = cardsToString(cards);
		Arrays.sort(cardNames);
		for (int i = 0; i < names.length; i++) {
			if (!names[i].equals(cardNames[i])) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Teste si une CardList contient au moins le nom indiqué dans chaîne `namesString` (un nom de carte). 
	 */

	public static boolean hasThisCard(CardList cards, String name) {
		List<String> l = Arrays.asList(cardsToString(cards));
		return l.contains(name);
	}

	/**
	 * Renvoie une instance de Game correspondant à un jeu à 3 joueurs utilisant
	 * les cartes Kingdom
	 * - Village
	 * - Woodcutter
	 * - Gardens
	 * - Moneylender
	 * - Smithy
	 * - Council Room
	 * - Festival
	 * - Laboratory
	 * - Market
	 * - Witch
	 */
	public static Game defaultGame() {
		String[] player_names = new String[]{"Toto", "Titi", "Tutu"};
		List<CardList> kingdomStacks = new ArrayList<>();
		kingdomStacks.add(makeStack(Village.class, 10));
		kingdomStacks.add(makeStack(Woodcutter.class, 10));
		kingdomStacks.add(makeStack(Gardens.class, 10));
		kingdomStacks.add(makeStack(Moneylender.class, 10));
		kingdomStacks.add(makeStack(Smithy.class, 10));
		kingdomStacks.add(makeStack(CouncilRoom.class, 10));
		kingdomStacks.add(makeStack(Festival.class, 10));
		kingdomStacks.add(makeStack(Laboratory.class, 10));
		kingdomStacks.add(makeStack(Market.class, 10));
		kingdomStacks.add(makeStack(Witch.class, 10));
		return new Game(player_names, kingdomStacks);
	}

	public static Game minimalGame() {
		String[] player_names = new String[]{"Toto", "Titi", "Tutu"};
		List<CardList> kingdomStacks = new ArrayList<>();
		return new Game(player_names, kingdomStacks);
	}

	/**
	 * Renvoie une CardList contenant `nb_copies` exemplaires de la carte 
	 * passée en argument
	 * 
	 * @param c: classe de carte à instancier
	 * @param nb_copies: nombre d'exemplaires à mettre dans la pile
	 * @return une liste de cartes
	 */
	public static CardList makeStack(Class<?> c, int nb_copies) {
		CardList stack = new CardList();
		for (int i = 0; i < nb_copies; i++) {
			try {
				stack.add((Card) c.getConstructor().newInstance());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return stack;
	}

	/**
	 * Flux de sortie vide, permettant d'ignorer la sortie standard
	 */
	public final static PrintStream nullOut = new PrintStream(new OutputStream() {
		public void close() {}
		public void flush() {}
		public void write(byte[] b) {}
		public void write(byte[] b, int off, int len) {}
		public void write(int b) {}
	});

	public static void main(String[] args) {
		System.out.println("  -- Tests simples de Game --");
		(new TestGame()).run();
		System.out.println("  -- Tests sur Game minimale (seulement cartes argent et victoire) --");
		(new TestMinimalGame()).run();
		System.out.println("  -- Tests simples des cartes --");
		(new TestCards()).run();
		System.out.println("  -- Test d'une partie complète --");
		(new TestFullGame()).run();
	}
}
