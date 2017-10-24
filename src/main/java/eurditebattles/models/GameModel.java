package eurditebattles.models;

import java.util.List;
import java.util.Map;

public class GameModel {
    public Map<String, Boolean> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Map<String, Boolean> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    private Map<String, Boolean> gamePlayers;
}
