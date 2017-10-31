package application.ebs.models;

import java.util.Map;

public class GamePlayerModel {
    public String eruditeName;
    public Integer baseAttack;
    public Integer baseBarrier;
    public Integer baseHealth;
    public Integer damageTaken;
    public Integer energy;
    public String battleType;
    public Map<String, Boolean> deckCards;
    public Map<String, Boolean> handCards;
    public String player;
    public String game;
}
