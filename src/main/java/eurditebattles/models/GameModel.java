package eurditebattles.models;

import java.util.List;

public class GameModel {
    public List<String> getGamePlayersIds() {
        return gamePlayersIds;
    }

    public void setGamePlayersIds(List<String> gamePlayersIds) {
        this.gamePlayersIds = gamePlayersIds;
    }

    private List<String> gamePlayersIds;
}
