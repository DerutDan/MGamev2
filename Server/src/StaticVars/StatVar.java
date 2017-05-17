package StaticVars;

import GameCard.GameCard;
import GameCard.Monster.*;

import java.util.ArrayList;
import java.util.Random;


public class StatVar {
    public static int defaultHeroHp = 100, defaultHeroAttack = 5, defaultHeroDef = 0, defaultHeroMana = 1;
    public static ArrayList<Monster> defaultMonsters = new ArrayList<>();
    public final static int defaultMonsterCount = 2;
    public final static int defaultDeckSize = 10;
    public final static int initialHandSize = 3;
    public final static int maxHandSize = 5,maxMonstersPlayed = 3;
    public final static int maxSessionsCount = 10;

    public static boolean isDefaultDeckInited = false;
    static Random r = new Random();

    public static void initMonsterDeck() {
        defaultMonsters.add(new MonsterPrototype1());
        defaultMonsters.add(new MonsterPrototype2());

        //defaultMonsters.add(new MonsterProto3());
    }

    public static ArrayList<GameCard> getRandomDefaultDeck() {
        ArrayList<GameCard> monsters = new ArrayList<>(defaultDeckSize);
        if (!isDefaultDeckInited) {
            for (int i = 0; i < defaultDeckSize; ++i) {
                int k = Math.abs(i + r.nextInt()) % defaultMonsterCount;
                switch (k) {
                    case 0: {
                        monsters.add(new MonsterPrototype1());
                        break;
                    }
                    case 1: {
                        monsters.add(new MonsterPrototype2());
                        break;
                    }
                    case 2: {
                        //monsters.add(new MonsterProto3());
                        break;
                    }
                }
            }
        }
        return monsters;
    }
}
