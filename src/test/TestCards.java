package test;

import dominion.*;
import dominion.card.*;
import dominion.card.base.*;
import dominion.card.common.*;
import java.util.*;
import java.io.ByteArrayInputStream;

public class TestCards extends Test {
	
	public void executeTests() {
		GameProxy g = new GameProxy(defaultGame());
		PlayerProxy p0 = new PlayerProxy(g.getPlayer(0));
		PlayerProxy p1 = new PlayerProxy(g.getPlayer(1));
		PlayerProxy p2 = new PlayerProxy(g.getPlayer(2));
		
		// Test de Cellar
		
		p1.clear();
		p1.addToHand(Cellar.class, 2);
		p1.addToHand(Estate.class, 3);
		p1.addToDraw(Copper.class, 2);
		System.out.print("Cellar : ");
		this.reset();
		try {
			g.setInput("Estate\nEstate\n\n");
			p1.playCard("Cellar");
			this.check(p1.getActions() == 1);
			this.check(hasCards(p1.hand, "Cellar, Copper, Copper, Estate"));
			this.check(hasCards(p1.discard, "Estate, Estate"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Chapel
		
		p1.clear();
		p1.addToHand(Chapel.class, 2);
		p1.addToHand(Estate.class, 1);
		p1.addToHand(Copper.class, 2);
		System.out.print("Chapel : ");
		this.reset();
		try {
			g.setInput("Estate\nCopper\n\n");
			p1.playCard("Chapel");
			this.check(hasCards(p1.hand, "Chapel, Copper"));
			this.check(p1.discard.isEmpty());
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Moat (Action)
		
		p1.clear();
		p1.addToHand(Moat.class, 1);
		p1.addToDraw(Copper.class, 3);
		System.out.print("Moat (Action) : ");
		this.reset();
		try {
			p1.playCard("Moat");
			this.check(hasCards(p1.hand, "Copper, Copper"));
			this.check(hasCards(p1.draw, "Copper"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Chancellor
		
		p1.clear();
		p1.addToHand(Chancellor.class, 2);
		p1.addToDraw(Estate.class, 3);
		System.out.print("Chancellor : ");
		this.reset();
		try {
			g.setInput("n\ny\n");
			p1.playCard("Chancellor");
			this.check(p1.getMoney() == 2);
			this.check(hasCards(p1.draw, "Estate, Estate"));
			this.check(p1.discard.size() == 0);
			p1.playCard("Chancellor");
			this.check(p1.getMoney() == 4);
			this.check(hasCards(p1.discard, "Estate, Estate"));
			this.check(p1.draw.size() == 0);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Village
		
		p1.clear();
		p1.addToHand(Village.class, 2);
		p1.addToHand(Copper.class, 1);
		p1.addToDraw(Estate.class, 1);
		
		int actions = p1.getActions();

		System.out.print("Village : ");
		this.reset();
		try {
			p1.playCard("Village");
			this.check(p1.getActions() == actions + 2);
			this.check(hasCards(p1.hand, "Copper, Estate, Village"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Woodcutter
		
		p1.clear();
		p1.addToHand(Woodcutter.class, 1);
		
		System.out.print("Woodcutter : ");
		this.reset();
		try {
			p1.playCard("Woodcutter");
			this.check(p1.getBuys() == 1);
			this.check(p1.getMoney() == 2);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Workshop
		
		p1.clear();
		p1.addToHand(Workshop.class, 1);
		System.out.print("Workshop : ");
		this.reset();
		try {
			g.setInput("Witch\nSilver\n");
			p1.playCard("Workshop");
			this.check(hasCards(p1.discard, "Silver"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Bureaucrat
		
		p0.clear();
		p0.addToHand(Estate.class, 1);
		p0.addToHand(Duchy.class, 1);
		p1.clear();
		p1.addToHand(Bureaucrat.class, 1);
		p2.clear();
		p2.addToHand(Village.class, 2);
		p2.addToHand(Copper.class, 2);
		
		System.out.print("Bureaucrat : ");
		this.reset();
		try {
			g.setInput("Province\nDuchy\n\n");
			p1.playCard("Bureaucrat");
			this.check(hasCards(p0.hand, "Estate"));
			this.check(hasCards(p0.draw, "Duchy"));
			this.check(p1.discard.isEmpty());
			this.check(hasCards(p1.draw, "Silver"));
			this.check(p2.draw.isEmpty());
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Gardens
		
		p1.clear();
		p1.addToHand(Gardens.class, 1);

		System.out.print("Gardens : ");
		this.reset();
		try {
			// 1 carte
			this.check(p1.hand.get(0).victoryValue(p1.player) == 0);
			p1.addToDiscard(Village.class, 5);
			p1.addToHand(Gardens.class, 3);
			// 9 cartes
			this.check(p1.hand.get(0).victoryValue(p1.player) == 0);
			p1.draw.add(new Copper());
			// 10 cartes
			this.check(p1.hand.get(0).victoryValue(p1.player) == 1);
			p1.addToDraw(Copper.class, 30);
			// 40 cartes
			this.check(p1.hand.get(0).victoryValue(p1.player) == 4);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Feast
		
		p1.clear();
		p1.addToHand(Feast.class, 1);
		System.out.print("Feast : ");
		this.reset();
		try {
			g.setInput("Gold\nFestival\n");
			p1.playCard("Feast");
			this.check(p1.draw.isEmpty());
			this.check(p1.hand.isEmpty());
			this.check(hasCards(p1.discard, "Festival"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Moneylender
		
		p1.clear();
		p1.addToHand(Moneylender.class, 2);
		p1.addToHand(Silver.class, 2);
		p1.addToHand(Copper.class, 1);
		System.out.print("Moneylender : ");
		this.reset();
		try {
			// avec Copper
			p1.playCard("Moneylender");
			this.check(p1.getMoney() == 3);
			// sans Copper
			p1.playCard("Moneylender");
			this.check(p1.getMoney() == 3);
			this.check(p1.discard.size() == 0);
			this.check(hasCards(p1.hand, "Silver, Silver"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Smithy
		
		p1.clear();
		p1.addToHand(Smithy.class, 3);
		p1.addToHand(Copper.class, 2);
		p1.addToDraw(Estate.class, 3);
		p1.addToDiscard(Village.class, 4);
		System.out.print("Smithy : ");
		this.reset();
		try {
			p1.playCard("Smithy");
			this.check(p1.hand.size() == 7);
			this.check(p1.draw.size() == 0);
			this.check(p1.discard.size() == 4);
			p1.playCard("Smithy");
			this.check(p1.hand.size() == 9);
			this.check(p1.draw.size() == 1);
			this.check(p1.discard.size() == 0);
			p1.playCard("Smithy");
			this.check(p1.hand.size() == 9);
			this.check(p1.draw.size() == 0);
			this.check(p1.discard.size() == 0);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Council Room
		
		p0.clear();
		p0.addToDraw(Copper.class, 3);
		p1.clear();
		p1.addToHand(CouncilRoom.class, 1);
		p1.addToDraw(CouncilRoom.class, 2);
		p1.addToDraw(Estate.class, 4);
		p2.clear();
		p2.addToDiscard(Estate.class, 1);
		System.out.print("Council Room : ");
		this.reset();
		try {
			p1.playCard("Council Room");
			this.check(p0.hand.size() == 1);
			this.check(p1.hand.size() == 4);
			this.check(p2.hand.size() == 1);
			p1.playCard("Council Room");
			this.check(p0.hand.size() == 2);
			this.check(p1.hand.size() == 5);
			this.check(p2.hand.size() == 1);
			p1.playCard("Council Room");
			this.check(p0.hand.size() == 3);
			this.check(p1.hand.size() == 4);
			this.check(p2.hand.size() == 1);
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
		
		// Test de Witch
		
		p0.clear();
		p1.clear();
		p1.addToHand(Witch.class, 2);
		p1.addToDiscard(Copper.class, 3);
		p2.clear();
		
		System.out.print("Witch : ");
		this.reset();
		try {
			p1.playCard("Witch");
			this.check(p1.hand.size() == 3);
			this.check(p1.draw.size() == 1);
			this.check(hasCards(p0.discard, "Curse"));
			this.check(hasCards(p2.discard, "Curse"));
			p1.playCard("Witch");
			this.check(p1.hand.size() == 3);
			this.check(p1.draw.size() == 0);
			this.check(hasCards(p0.discard, "Curse, Curse"));
			this.check(hasCards(p2.discard, "Curse, Curse"));
			if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
			else { System.out.println("[ÉCHEC]"); this.fail(); }
		} catch (Exception e) {
			System.out.println("[ERREUR]");
			this.error();
		}
	}
}