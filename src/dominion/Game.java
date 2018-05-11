package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Class représentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;
	
	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;
	
	/**
	 * Liste des piles dans la réserve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la même
	 * carte.
	 * Ces piles peuvent être vides en cours de partie si toutes les cartes de 
	 * la pile ont été achetées ou gagnées par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont été écartées (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrées au clavier
	 */
	private Scanner scanner;
	
	
	private int indiceProvincesupplyStacks;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent à la 
	 * partie. Le constructeur doit créer les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de réserve à utiliser correspondant 
	 * aux cartes "royaume" à utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		this.supplyStacks = new ArrayList<CardList>();
		this.trashedCards = new CardList();
		this.scanner = new Scanner(System.in);
		int nombreJoueur = playerNames.length;
		//initialise le joueur courant
		this.currentPlayerIndex = 0;
		//Initialise le tableau de joueurs de la partie
		this.players = new Player[nombreJoueur];
		//Game game; // on doit cr��r des player mais dans les param�tres du constructeur de player il y a un objet Game.... On defini un autre constructeur sans Game???
		//Pas n�cessaire, le constructeur de Player a un objet game pour utiliser les m�thodes removeFromSupply, utiliser "this"
		CardList copper = new CardList();
		CardList silver = new CardList();
		CardList gold = new CardList();
		CardList estate = new CardList();
		CardList duchy = new CardList();
		CardList province = new CardList();
		CardList curse = new CardList();
		for(int i=0; i<60; i++) {
			copper.add(new Copper());	
		}
		for(int i=0; i<40; i++) {
			silver.add(new Silver());	
		}
		for(int i=0; i<30; i++) {
			gold.add(new Gold());	
		}
		if(nombreJoueur == 2) {
			for(int i=0; i<8; i++) {
				estate.add(new Estate());	
			}
			for(int i=0; i<8; i++) {
				duchy.add(new Duchy());	
			}
			for(int i=0; i<8; i++) {
				province.add(new Province());	
			}
			for(int i = 0; i<10; i++) { 
				curse.add(new Curse());
			}
		}
		else if(nombreJoueur >2) {
			for(int i=0; i<12; i++) {
				estate.add(new Estate());	
			}
			for(int i=0; i<12; i++) {
				duchy.add(new Duchy());	
			}
			for(int i=0; i<12; i++) {
				province.add(new Province());	
			}
			for(int i =0; i < 10*(nombreJoueur-1); i++) { 
				curse.add(new Curse());
			}
		}
		this.supplyStacks.addAll(kingdomStacks);
		this.supplyStacks.add(copper);
		this.supplyStacks.add(silver);
		this.supplyStacks.add(gold);
		this.supplyStacks.add(estate);
		this.supplyStacks.add(duchy);
		this.supplyStacks.add(province);
		this.supplyStacks.add(curse);
		// permet de connaitre l'emplacement de la pile province
		for(int i=0; i<this.supplyStacks.size(); i++) {
			if(!this.supplyStacks.get(i).isEmpty() && this.supplyStacks.get(i).get(0).getName().equalsIgnoreCase("province")) {
				this.indiceProvincesupplyStacks = i;
			}
		}
		//Cr��e les joueurs
		for(int i=0; i<nombreJoueur; i++) {
			this.players[i] = new Player(playerNames[i], this);
		}
	}
	
	
	//R�cup�re l'index du joueur actif dans le tableau players
	public int getCurrentPlayerIndex() {
		return this.currentPlayerIndex;
	}
	
	
	/**
	 * Renvoie le joueur correspondant à l'indice passé en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur à renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	
	/**
	 * Renvoie le nombre de joueurs participant à la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passé en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player p) {
		for(int i=0; i<this.players.length; i++) {
			if(this.players[i].equals(p)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Renvoie la liste des adversaires du joueur passé en argument, dans 
	 * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commençant par celui qui se trouve juste après {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considéré 
	 * comme cyclique c'est-à-dire qu'après le premier élément on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		ArrayList <Player> autresJoueurs = new ArrayList<Player>();
		int indice = indexOfPlayer(p);
		for(int i=indice +1 ; i < this.numberOfPlayers(); i++) {
			autresJoueurs.add(this.players[i]);
		}
		for(int i=0; i < indice; i++) {
			autresJoueurs.add(this.players[i]);
		}
		return autresJoueurs;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles à l'achat dans la 
	 * réserve.
	 * 
	 * @return une liste de cartes contenant la première carte de chaque pile 
	 * non-vide de la réserve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList carteReserve = new CardList();

		for(int i =0; i < this.supplyStacks.size(); i++) {
			if(!this.supplyStacks.get(i).isEmpty() && this.supplyStacks.get(i).get(0) != null) 
			{
				carteReserve.add(this.supplyStacks.get(i).get(0));
			}
		}	
		return carteReserve;	
	}
	
	/**
	 * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
	 * de caractères.
	 * 
	 * Cette représentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la réserve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player currentPlayer = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}
	
	/**
	 * Renvoie une carte de la réserve dont le nom est passé en argument.
	 * 
	 * @param cardName nom de la carte à trouver dans la réserve
	 * @return la carte trouvée dans la réserve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		//CardList reserve = this.availableSupplyCards();
		if(cardName != null) {
			for(int i =0; i< this.supplyStacks.size(); i++) {
				if(!this.supplyStacks.get(i).isEmpty() && this.supplyStacks.get(i).get(0).getName().equalsIgnoreCase(cardName)) {
					return this.supplyStacks.get(i).get(0);
				}
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Retire et renvoie une carte de la réserve
	 * 
	 * @param cardName nom de la carte à retirer de la réserve
	 * @return la carte retirée de la réserve ou {@code null} si aucune carte
	 * ne correspond au nom passé en argument
	 */
	public Card removeFromSupply(String cardName) {
		//CardList reserve = this.availableSupplyCards();
		if(cardName != null) {
			for(int i =0; i< this.supplyStacks.size(); i++) {// en fait la cardlist availableSupplyCards �tait pas forc�ment de la m�me taille que supplyStacks si une pile �tait vide, du coup le i ne correspondait pas ^^
				if(!this.supplyStacks.get(i).isEmpty() && this.supplyStacks.get(i).get(0).getName().equalsIgnoreCase(cardName)) {
					return this.supplyStacks.get(i).remove(0);
				}
			}
			return null;
		}
		return null;
	}
	
	//M�thode n�cessaire pour �carter une carte
	public void addInTrash(Card c) {
		if(c != null) {
			this.trashedCards.add(c);
		}
	}
	
	/**
	 * Teste si la partie est terminée
	 * 
	 * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la réserve sont vides
	 *  - la pile de Provinces de la réserve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la réserve n'est une pile de Provinces, 
	 * c'est que la partie est terminée)
	 */
	public boolean isFinished() {
		if(this.supplyStacks.get(indiceProvincesupplyStacks).isEmpty()) {
			return true;
		}
		int nbPileVide = 0;
		for(int i=0; i<this.supplyStacks.size(); i++) {
			if(this.supplyStacks.get(i).isEmpty()) {
				nbPileVide++;
			}
			if(nbPileVide>2) { 
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Boucle d'exécution d'une partie.
	 * 
	 * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
	 * terminée. Lorsque la partie se termine, la méthode affiche le score 
	 * final et les cartes possédées par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}

	/**
	 * Lit une ligne de l'entrée standard
	 * 
	 * C'est cette méthode qui doit être appelée à chaque fois qu'on veut lire
	 * l'entrée clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaîne de caractères correspondant à la ligne suivante de
	 * l'entrée standard (sans le retour à la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
}