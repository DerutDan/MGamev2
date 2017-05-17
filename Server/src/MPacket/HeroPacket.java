package MPacket;

import GameCard.Hero.Hero;

/**
 * Created by Danila on 19.03.17.
 */
public class HeroPacket extends MPacket {
    Hero hero;
    boolean isMine;
    public HeroPacket(Hero hero,boolean isMine) {
        this.hero = hero;
        this.isMine = isMine;
    }
    @Override
    public String getContain() {
        return isMine + " " +  hero.serialize();
    }

    @Override
    public void setT() {
        type = "HeroPacket";
    }
}
