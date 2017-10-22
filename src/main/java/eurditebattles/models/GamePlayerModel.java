package eurditebattles.models;

import java.util.List;

public class GamePlayerModel {

    private List<String> deckCardIds;
    private List<String> handCardIds;
    private List<String> gameEruditeIds;
    private Integer energy;
    private String playerId;

    public List<String> getDeckCardIds() {
        return deckCardIds;
    }

    public void setDeckCardIds(List<String> deckCardIds) {
        this.deckCardIds = deckCardIds;
    }

    public List<String> getHandCardIds() {
        return handCardIds;
    }

    public void setHandCardIds(List<String> handCardIds) {
        this.handCardIds = handCardIds;
    }

    public List<String> getGameEruditeIds() {
        return gameEruditeIds;
    }

    public void setGameEruditeIds(List<String> gameEruditeIds) {
        this.gameEruditeIds = gameEruditeIds;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
