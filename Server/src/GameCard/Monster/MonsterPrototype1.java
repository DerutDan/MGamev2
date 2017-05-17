package GameCard.Monster;

public class MonsterPrototype1 extends Monster {

    public MonsterPrototype1() {
        super("MonsterPrototype1", 10,  2, 2);
    }

    @Override
    public void death() {
        enemy.healUp(20);
        isAlive = false;
    }

    @Override
    public void penalty() {
        hp+=2;
        attack+=1;
        if(attack>=10)
            enemy.attackMonster(this);
    }

    @Override
    public void setDeathDescription() {
        penaltyDescription = "+2/+1. If it has 10 or more attack, attacks enemy\n";
    }

    @Override
    public void setPenaltyDescription() {
        deathDescription = "Heals enemy for 20 points\n";
    }

}
