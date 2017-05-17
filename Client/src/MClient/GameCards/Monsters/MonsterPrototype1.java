package MClient.GameCards.Monsters;

/**
 * Created by Danila on 15.02.17.
 */
public class MonsterPrototype1 extends Monster {

    @Override
    public void setDeathDescription() {
        penaltyDescription = "+2/+1. If it has 10 or more attack, attacks enemy\n";
    }

    @Override
    public void setPenaltyDescription() {
        deathDescription = "Heals enemy for 20 points\n";
    }
}
