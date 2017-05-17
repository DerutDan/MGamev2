package MClient.GameCards.Monsters;

import MClient.GameCards.GameCard;
import MClient.GameCards.Hero.Hero;

/**
 * Created by Danila on 15.02.17.
 */
public abstract class Monster extends GameCard {
    //protected final int level,chargeTime;
    protected int hp,attack,chargeLeft;
    protected Hero master,enemy;
    protected String penaltyDescription,deathDescription;
    protected boolean isAlive = false;
    @Override
    public void deserialize(String params) {
        String[] paramsS = params.split(" ");
        if(paramsS.length < 3) System.out.println("Some error here: Monster.deserialize()");
        this.name = paramsS[0];
        this.hp = Integer.parseInt(paramsS[1]);
        this.attack = Integer.parseInt(paramsS[2]);
        this.chargeLeft = Integer.parseInt(paramsS[3]);
    }

    public abstract void setDeathDescription();

    public abstract void setPenaltyDescription();
}
