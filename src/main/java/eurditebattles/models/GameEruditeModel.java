package eurditebattles.models;

public class GameEruditeModel {
    private boolean isMainErudite;
    private Integer health;
    private Integer healthRemaining;
    private Integer attack;
    private String gamePlayer;

    public String getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(String gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public boolean isMainErudite() {
        return isMainErudite;
    }

    public void setIsMainErudite(boolean isMainErudite) {
        this.isMainErudite = isMainErudite;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHealthRemaining() {
        return healthRemaining;
    }

    public void setHealthRemaining(Integer healthRemaining) {
        this.healthRemaining = healthRemaining;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
}
