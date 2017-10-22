package eurditebattles.models;

public class GameEruditeModel {
    private boolean isPlayer;
    private Integer health;
    private Integer healthRemaining;
    private Integer attack;

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
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
