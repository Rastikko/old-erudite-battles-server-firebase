package application.ebs.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class GamePlayerModel {
    public String eruditeName;
    public Integer attack;
    public Integer barrier;
    public Integer totalHealth;
    public Integer health;
    public Integer energy;
    public String battleType;
    public Map<String, Boolean> deckCards = new HashMap<>();
    public Map<String, Boolean> handCards = new HashMap<>();
    public String player;
    public String game;
    public String key;

    public GamePlayerModel() {

    }
}