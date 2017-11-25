package application.ebs.phaser;

import application.ebs.builders.GameCommandBuilder;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GameModel;
import application.ebs.models.GamePhaseModel;
import application.ebs.models.GamePlayerModel;
import application.ebs.models.PlayerModel;
import application.ebs.types.GameCommandsTypes;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPhase {
    Phaser phaser;
    DataSnapshot gamePhaseDataSnapshot;
    GamePhaseModel gamePhaseModel;
    GameModel gameModel;
    List<GamePlayerModel> gamePlayerModels;
    List<GameCommandModel> gameCommandModels;

    public AbstractPhase(Phaser phaser, DataSnapshot gamePhaseDataSnapshot) {
        this.phaser = phaser;
        this.gamePhaseDataSnapshot = gamePhaseDataSnapshot;
        this.gamePhaseModel = gamePhaseDataSnapshot.getValue(GamePhaseModel.class);
        this.gameModel = retrieveGame();
        this.gameCommandModels = retrieveGameCommands();
        this.gamePlayerModels = retrieveGamePlayerModels();
    }

    List<GameCommandModel> retrieveGameCommands() {
        List<String> commandKeys = new ArrayList<>(this.gamePhaseModel.gameCommands.keySet());
        List<GameCommandModel> gameCommands = new ArrayList<>();

        commandKeys.forEach(gameCommandKey -> {
            DataSnapshot commandSnapshot = phaser.gameCommandHandler.getSubresourceDataSnapshot(gameCommandKey);
            GameCommandModel command = commandSnapshot.getValue(GameCommandModel.class);
            gameCommands.add(command);
        });

        return gameCommands;
    }

    GameModel retrieveGame() {
        DataSnapshot snapshot = phaser.gameHandler.getSubresourceDataSnapshot(gamePhaseModel.game);
        return snapshot.getValue(GameModel.class);
    }

    List<GamePlayerModel> retrieveGamePlayerModels() {
        List<GamePlayerModel> gamePlayers = new ArrayList<>();
        List<String> gamePlayerKeys = new ArrayList<>(this.gameModel.gamePlayers.keySet());

        gamePlayerKeys.forEach(gamePlayerKey -> {
            DataSnapshot snapshot = phaser.gamePlayerHandler.getSubresourceDataSnapshot(gamePlayerKey);
            GamePlayerModel gamePlayer = snapshot.getValue(GamePlayerModel.class);
            gamePlayer.key = gamePlayerKey;
            gamePlayers.add(gamePlayer);

        });

        return gamePlayers;
    }

    GamePlayerModel getBotPlayer() {
        GamePlayerModel botGamePlayer = null;

        for (GamePlayerModel gamePlayer : this.gamePlayerModels) {
            if (gamePlayer.player.equals("BOT")) {
                botGamePlayer = gamePlayer;
            }
        }

        return botGamePlayer;
    }

    // public GameCommandBuilder(String gameId, String gamePhase, String gamePlayer, String gameCommandType) {

    Boolean getPhaseIsDone() {
        List<GameCommandModel> gameEndCommands = this.gameCommandModels.stream()
                .filter(gameCommand -> gameCommand.gameCommandType.equals(GameCommandsTypes.END_PHASE.name()))
                .collect(Collectors.toList());
        return gameEndCommands.size() == 2;
    }

    void buildBotEndCommand(GamePlayerModel botGamePlayer) {
        String game = this.gamePhaseModel.game;
        String gamePhase = this.gamePhaseDataSnapshot.getKey();
        String gamePlayer = botGamePlayer.key;
        String gameCommandType = GameCommandsTypes.END_PHASE.name();
        GameCommandBuilder gameCommand = new GameCommandBuilder(game, gamePhase, gamePlayer, gameCommandType);

        Map<String, Boolean> gameCommands = this.gamePhaseModel.gameCommands;

        gameCommands.put(gameCommand.getReference().getKey(), true);


        Map<String, Object> phaseUpdate = new HashMap<>();
        phaseUpdate.put("gameCommands", gameCommands);
        phaser.gamePhaseHandler.updateSubresource(gamePhaseDataSnapshot.getKey(), phaseUpdate) ;

//        this.commander.gamePlayerHandler.updateSubresource(gamePlayerDataSnapshot.getKey(), playerUpdate);

    }

    void ensureBotIsDone() {

        if (getPhaseIsDone()) {
            return;
        }

        List<GameCommandModel> gameEndCommands = this.gameCommandModels.stream()
                .filter(gameCommand -> gameCommand.gameCommandType.equals(GameCommandsTypes.END_PHASE.name()))
                .collect(Collectors.toList());

        GamePlayerModel botGamePlayer = getBotPlayer();

        if (botGamePlayer == null) {
            return;
        }

        for (GameCommandModel gameCommand : gameEndCommands) {
            Boolean isEndCommand = gameCommand.gameCommandType.equals(GameCommandsTypes.END_PHASE.name());
            Boolean isBot = gameCommand.gamePlayer.equals(botGamePlayer.key);
            if (isEndCommand && isBot) {
                return;
            }
        }

        buildBotEndCommand(botGamePlayer);

    }
}
