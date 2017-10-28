package application.eurditebattles.models;

import java.util.Map;
import java.util.UUID;

public class GamePlayerModel {
    public String eruditeName;
    public Integer baseAttack;
    public Integer baseBarrier;
    public Integer baseHealth;
    public Integer damageTaken;
    public Integer energy;
    public String battleType;
    public String player;
    public Map<String, Boolean> deckCards;
    public Map<String, Boolean> handCards;
    public String game;
}
