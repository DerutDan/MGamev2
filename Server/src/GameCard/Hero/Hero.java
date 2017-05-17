package GameCard.Hero;

import GameCard.GameCard;
import GameCard.Monster.Monster;

/**
 * Created by Danila on 11.02.17.
 */
public abstract class Hero extends GameCard implements Cloneable{
    int hp;
    int attack;
    int def;
    int maxHp;
    int bounty;
    boolean exausted = true;
    Hero(String name,int hp, int attack, int def)
    {
        setT();
        setName(name);
        this.maxHp = this.hp = hp;
        this.attack = attack;
        this.def = def;
        this.bounty = 0;
    }
    @Override
    public void setT() {
        type = "Hero";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void healUp(int healValue)
    {
        hp+=healValue;
        if(hp > maxHp) hp = maxHp;
    }

    public void exaust()
    {
        exausted = true;
    }
    public void unexaust() { exausted = true; }

    public void attackMonster(Monster monster)
    {
        //takeDamage(monster.getAttack());
        monster.takeDamage(attack);
        exaust();
    }

    public void takeDamage(int attackValue)
    {
        int hit;
        if((hit = attackValue - def) > 0)
            hp -= hit;
        //if(hp<=0) death();
    }

    public boolean isExausted() {
        return exausted;
    }

    @Override
    public String serialize() {
        StringBuilder s = new StringBuilder(type);
        s.append(" ");
        s.append(name);
        s.append(" ");
        s.append(hp);
        s.append(" ");
        s.append(attack);
        s.append(" ");
        s.append(def);
        s.append(" ");
        s.append(bounty);
        s.append(" ");
        s.append(exausted?"true":"false");


        return s.toString();
    }


}
