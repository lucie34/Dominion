package test;

import dominion.*;
import dominion.card.*;
import dominion.card.base.*;
import java.util.*;

public class TestGame extends Test {
	
	public void executeTests() {
		Game g = defaultGame();
		Player p = g.getPlayer(1);
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
			this.check(p.getName().equals("Titi"));
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
			this.check(g.otherPlayers(p).get(0).getName().equals("Tutu"));
			this.check(g.otherPlayers(p).get(1).getName().equals("Toto"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		CardList availableSupplies = g.availableSupplyCards();
		
		System.out.print("Nombre de piles de réserve : ");
		this.reset();
		try {
			this.check(availableSupplies.size() == 17);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		System.out.print("Trouver une carte présente dans la réserve : ");
		this.reset();
		try {
			this.check(g.getFromSupply("Festival").getName().equals("Festival"));
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

		System.out.print("Retirer une carte de la réserve : ");
		this.reset();
		try {
			this.check(g.removeFromSupply("Festival").getName().equals("Festival"));
			boolean found = false;
			for (CardList supp: g_p.supplyStacks) {
				if (!supp.isEmpty() &&
					supp.get(0).getName().equals("Festival")) {
					this.check(supp.size() == 9);
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
		
		System.out.print("Pile vide : ");
		this.reset();
		try {
			for (int i=0; i<9; i++) {
				g.removeFromSupply("Festival");
			}
			this.check(g.availableSupplyCards().size() == 16);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}

		for (int i=0; i<10; i++) {
			g.removeFromSupply("Village");
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

		availableSupplies = g.availableSupplyCards();
		System.out.print("Piles de réserve disponibles : ");
		this.reset();
		try {
			boolean found;
			found = false;
			for (Card c: availableSupplies) {
				if (c.getName().equals("Village")) {
					found = true;
				}
			}
			this.check(!found);
			found = false;
			for (Card c: availableSupplies) {
				if (c.getName().equals("Smithy")) {
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

		for (int i=0; i<10; i++) {
			g.removeFromSupply("Smithy");
		}

		System.out.print("Partie terminée : ");
		this.reset();
		try {
			this.check(g.isFinished());
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
	}
}