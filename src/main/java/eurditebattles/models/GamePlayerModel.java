package eurditebattles.models;

import java.util.Map;

public class GamePlayerModel {
    private Map<String, Boolean> deckCards;
    private Map<String, Boolean> handCards;
    private Map<String, Boolean> gameErudites;
    private Integer energy;
    private String player;
    private String game;

    public Map<String, Boolean> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Map<String, Boolean> deckCards) {
        this.deckCards = deckCards;
    }

    public Map<String, Boolean> getHandCards() {
        return handCards;
    }

    public void setHandCards(Map<String, Boolean> handCards) {
        this.handCards = handCards;
    }

    public Map<String, Boolean> getGameErudites() {
        return gameErudites;
    }

    public void setGameErudites(Map<String, Boolean> gameErudites) {
        this.gameErudites = gameErudites;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
