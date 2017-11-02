package application.ebs.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class GamePlayerModel {
    public String eruditeName;
    public Integer baseAttack;
    public Integer baseBarrier;
    public Integer baseHealth;
    public Integer damageTaken;
    public Integer energy;
    public String battleType;
    public Map<String, Boolean> deckCards = new HashMap<>();
    //public Map<String, Boolean> handCards;
    public String player;
    public String game;

    public GamePlayerModel() {

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        return result;
    }
}