package test;

import java.util.*;
import dominion.*;
import dominion.card.*;


class ScriptableGame extends Game {
    public ScriptableGame(String[] playerNames, List<CardList> kingdomStacks) {
        super(playerNames, kingdomStacks);
    }

    private String bigMoney() {
        GameProxy g = GameProxy.shared;
        PlayerProxy p = new PlayerProxy(g.players[g.getCurrentPlayerIndex()]);
        if (p.getMoney() >= 8) {
            return "Province\n";
        } else if (p.getMoney() >= 6) {
            return "Gold\n";
        } else if (p.getMoney() >= 3) {
            return "Silver\n";
        } else {
            return "\n";
        }
    }

    public String readLine() {
        GameProxy.shared.writeToPipedInput(this.bigMoney());
        return super.readLine();
    }
}
