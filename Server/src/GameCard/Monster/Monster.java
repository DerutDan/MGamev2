package GameCard.Monster;

import GameCard.GameCard;
import GameCard.Hero.Hero;

/**
 * Created by Danila on 11.02.17.
 */
public abstract class Monster extends GameCard {
    protected  final int level,chargeTime;
    protected int hp,attack,chargeLeft;
    protected Hero master,enemy;
    protected String penaltyDescription,deathDescription;
    protected boolean isAlive = false;
    public Monster(String name, int hp, int level, int chargeTime)
    {
        setT();
        setName(name);
        this.hp = hp;
        //this.attack = attack;
        this.level = level;
        this.chargeTime = chargeTime;
        this.chargeLeft = chargeTime;
        this.isAlive = true;
    }

    @Override
    public void setT()
    {
        type = "Monster";
    }
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    public void setMaster(Hero master) {
        this.master = master;
    }

    public void setEnemy(Hero enemy) {
        this.enemy = enemy;
    }

    public void chargeUp()
    {
        chargeLeft--;
    }

    public void takeDamage(int attackValue)
    {
        hp -= attackValue;
        if(hp<=0) death();
    }

    public int getHp() {
        return hp;
    }

    /*public int getAttack() {
        return attack;
    }*/

    public int getLevel() {
        return level;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    public int getChargeLeft() {
        return chargeLeft;
    }

    public String getPenaltyDescription() {
        return penaltyDescription;
    }

    public String getDeathDescription() {
        return deathDescription;
    }

    @Override
    public String serialize() {
        StringBuilder s = new StringBuilder(type);
        s.append(" ");
        s.append(name);
        s.append(" ");
        s.append(hp);
        s.append(" ");
        //s.append(attack);
        //s.append(" ");
        s.append(chargeLeft);
        return s.toString();
    }

    public abstract void  death();
    public abstract void penalty();
    public abstract void setDeathDescription();
    public abstract void setPenaltyDescription();
}
