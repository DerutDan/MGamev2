package MClient.GameCards.Hero;

import MClient.GameCards.GameCard;

public abstract class Hero extends GameCard {
    int hp;
    int attack;
    int def;
    int maxHp;
    int bounty;
    boolean exausted = true;

    @Override
    public void deserialize(String params)
    {
        String[] paramsS = params.split(" ");
        if(paramsS.length < 5) System.out.println("Some error here: Hero.deserialize()");
        this.name = paramsS[0];
        this.hp = Integer.parseInt(paramsS[1]);
        this.attack = Integer.parseInt(paramsS[2]);
        this.def = Integer.parseInt(paramsS[3]);
        this.bounty = Integer.parseInt(paramsS[4]);
        this.exausted = (paramsS[5].equals("true"));
    }
}
