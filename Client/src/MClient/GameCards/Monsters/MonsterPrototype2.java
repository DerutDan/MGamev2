package MClient.GameCards.Monsters;

/**
 * Created by Danila on 15.02.17.
 */
public class MonsterPrototype2 extends Monster {

    @Override
    public void setDeathDescription() {
        deathDescription = "Master takes 15 damage\n";
    }

    @Override
    public void setPenaltyDescription() {
        penaltyDescription = "Enemy takes 5 damage\n";
    }
}
