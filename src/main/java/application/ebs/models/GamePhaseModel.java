package application.ebs.models;

import java.util.HashMap;
import java.util.Map;

public class GamePhaseModel {
    public String gamePhaseType;
    public String game;
    public Boolean resolved;
    public Map<String, Boolean> gameCommands = new HashMap<>();
    public Map<String, Boolean> phaseEndGamePlayers = new HashMap<>();
}
