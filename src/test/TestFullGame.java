package test;

import java.util.*;
import dominion.card.*;
import dominion.card.base.*;

public class TestFullGame extends Test {
	public void executeTests() {
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
		GameProxy g = new GameProxy(new ScriptableGame(player_names, kingdomStacks));
		
		g.setPipedInput();
        System.out.print("Big Money : ");
        this.reset();
        try {
            int nbProvinces = 0;
            for (CardList stack: g.supplyStacks) {
                if (!stack.isEmpty()) {
                    Card c = stack.get(0);
                    if (c.getName().equals("Province")) {
                        nbProvinces = stack.size();
                        break;
                    }
                }
            }
            this.check(nbProvinces >= 8);
            this.check(nbProvinces <= 12);
            g.run();
            int score0 = g.players[0].victoryPoints();
            int score1 = g.players[1].victoryPoints();
            int score2 = g.players[2].victoryPoints();
            this.check(score0 % 6 == 3);
            this.check(score1 % 6 == 3);
            this.check(score2 % 6 == 3);
            this.check(score0 + score1 + score2 == 9 + 6 * nbProvinces);
        	if (this.isPassed()) { System.out.println("[OK]"); this.pass(); }
        	else { System.out.println("[ÉCHEC]"); this.fail(); }
        } catch (Exception e) {
        	System.out.println("[ERREUR]");
        	this.error();
        }
	}
	
	public static void main(String[] args) {
		(new TestFullGame()).run();
	}
}