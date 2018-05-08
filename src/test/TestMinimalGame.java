package test;

import dominion.*;
import dominion.card.*;
import dominion.card.base.*;
import dominion.card.common.*;
import java.util.*;
import java.io.*;



public class TestMinimalGame extends Test {
	
	public void executeTests() {
		Game g = minimalGame();
		Player p = g.getPlayer(0);
		GameProxy g_p = new GameProxy(g);
		PlayerProxy p_p = new PlayerProxy(p);
		
		System.out.print("Nombre de joueurs : ");
		this.reset();
		try {
			this.check(g.numberOfPlayers() == 3);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		System.out.print("Accès aux joueurs : ");
		this.reset();
		try {
			this.check(p.getName().equals("Toto"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		System.out.print("Autres joueurs (taille) : ");
		this.reset();
		try {
			this.check(g.otherPlayers(p).size() == 2);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		System.out.print("Autres joueurs (noms) : ");
		this.reset();
		try {
			this.check(g.otherPlayers(p).get(0).getName().equals("Titi"));
			this.check(g.otherPlayers(p).get(1).getName().equals("Tutu"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		CardList availableSupplies = g.availableSupplyCards();
		//je ne teste pas le nombre de piles de resa, car ça les obligerait à coder curse pour avoir bien 3
		
		System.out.print("Trouver une carte Gold présente dans la réserve : ");
		this.reset();
		try {
			this.check(g.getFromSupply("Gold").getName().equals("Gold"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		System.out.print("Trouver une carte absente de la réserve : ");
		this.reset();
		try {
			this.check(g.getFromSupply("Blop") == null);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		System.out.print("Retirer une carte Gold de la réserve : ");
		this.reset();
		try {
		    int tailleGold = 0;
			for (CardList supp: g_p.supplyStacks) {
				if (!supp.isEmpty() &&
					supp.get(0).getName().equals("Gold")) {
				    tailleGold = supp.size();
				}
			}

			this.check(g.removeFromSupply("Gold").getName().equals("Gold"));
			boolean found = false;
			for (CardList supp: g_p.supplyStacks) {
			    
			   
				if (!supp.isEmpty() &&
				    supp.get(0).getName().equals("Gold")) {
				    this.check(supp.size() == tailleGold-1);
				    found = true;
				}
			}
			this.check(found);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		System.out.print("Retirer une carte absente de la réserve : ");
		this.reset();
		try {
			this.check(g.removeFromSupply("Blop") == null);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		

		System.out.print("Partie non terminée : ");
		this.reset();
		try {
			this.check(!g.isFinished());
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}



		System.out.print("Test start turn player : ");
		this.reset();
		try {
		    //Player p_cour = g_p.players[g_p.getCurrentPlayerIndex()];
		    p_p.player.startTurn();
		    this.check(p_p.player.getActions()==1);
		    this.check(p_p.player.getMoney()==0);
		    this.check(p_p.player.getBuys()==1);
		    if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		
		System.out.print("Test de playTurn() (achat possible) : "); 
		/* si la situation au début du tour est 
		      défausse vide 
		      la main contient 3 copper et 2 estate,
		  et si l'on simule une entrée au clavier \"Silver\n\", 
		  
		  alors à playTurn() doit
		       ne pas proposer de jouer une carte action,
		       jouer automatiquement les 3 copper,
		       acheter un Silver, et finir le tour 

		  le test vérifie donc que playTurn termine, et qu'on retrouve bien un Silver 
		  (soit dans la pioche, soit dans la main du joueur, étant donné qu'à la fin du tour playTurn appelle endTurn() qui defausse tout et repioche 5 cartes)
		*/


		this.reset();
		try {
		    g_p.setInput("Silver\n");
		    PrintStream outs = System.out;
		    System.setOut(Test.nullOut);
		    p_p.clear();
		    p_p.player.startTurn();		    
		    p_p.addToHand(Copper.class, 3);
		    p_p.addToHand(Estate.class, 2);
		    p_p.player.playTurn();
		    System.setOut(outs);		 
		    this.check(hasThisCard(p_p.hand, "Silver")|| hasThisCard(p_p.draw, "Silver"));
		    
		    if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}




		System.out.print("Test de playTurn() (tentative d'achat impossible, puis pas d'achat) : "); 
		/* si la situation au début du tour est 
		      défausse vide 
		      la main contient 3 copper et 2 estate,
		  et si l'on simule une entrée au clavier \"Gold\n\", puis "\n"
		  
		  alors à playTurn() doit
		       ne pas proposer de jouer une carte action,
		       jouer automatiquement les 3 copper,
		       ne pas réussir à acheter le Gold, puis abandonner l'achat, et finir le tour 

		  le test vérifie donc que playTurn termine, et qu'on retrouve aucun Gold 
		  (soit dans la pioche, soit dans la main du joueur, étant donné qu'à la fin du tour playTurn appelle endTurn() qui defausse tout et repioche 5 cartes)
		*/
		this.reset();
		try {
		    g_p.setInput("Gold\n\n");
		    PrintStream outs = System.out;
		    System.setOut(Test.nullOut);
		    p_p.clear();
		    p_p.player.startTurn();		    
		    p_p.addToHand(Copper.class, 3);
		    p_p.addToHand(Estate.class, 2);
		    p_p.player.playTurn();
		    System.setOut(outs);		 
		    this.check(!hasThisCard(p_p.hand, "Gold")&& !hasThisCard(p_p.draw, "Gold"));
		    if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}


	}
}
