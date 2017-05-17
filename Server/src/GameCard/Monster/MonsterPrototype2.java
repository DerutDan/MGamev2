package GameCard.Monster;

public class MonsterPrototype2 extends Monster {
    public MonsterPrototype2() {
        super("MonsterPrototype2", 30,  4, 3);
    }

    @Override
    public void death() {
        master.takeDamage(15);
        isAlive = false;
    }

    @Override
    public void penalty() {
        enemy.takeDamage(5);
    }

    @Override
    public void setDeathDescription() {
        deathDescription = "Master takes 15 damage\n";
    }

    @Override
    public void setPenaltyDescription() {
        penaltyDescription = "Enemy takes 5 damage\n";
    }
}
