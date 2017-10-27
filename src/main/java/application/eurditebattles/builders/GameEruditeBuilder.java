package application.eurditebattles.builders;

import application.eurditebattles.models.GameEruditeModel;

public class GameEruditeBuilder extends AbstractBuilder {

    public GameEruditeBuilder(String gamePlayer) {
        this.parentResource = "gameErudites";
        this.generateKey();

        GameEruditeModel erudite = new GameEruditeModel();
        erudite.health = 1000;
        erudite.healthRemaining = 1000;
        erudite.attack = 300;
        erudite.type = "Logic";
        erudite.gamePlayer = gamePlayer;

        save(erudite);
    }
}
