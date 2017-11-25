package application.ebs.builders;

//public class GameCommandModel {
//    public String gameCommandType;
//    public boolean resolved;
//    public String gamePhase;
//    public String gamePlayer;
//    public String game;
//}

import application.ebs.models.GameCommandModel;

public class GameCommandBuilder extends AbstractBuilder {
    public GameCommandBuilder(String gameId, String gamePhase, String gamePlayer, String gameCommandType) {
        this.parentResource = "gameCommands";
        generateKey();

        GameCommandModel command = new GameCommandModel();
        command.game = gameId;
        command.gamePhase = gamePhase;
        command.gamePlayer = gamePlayer;
        command.gameCommandType = gameCommandType;
        command.resolved = false;

        save(command);
    }
}