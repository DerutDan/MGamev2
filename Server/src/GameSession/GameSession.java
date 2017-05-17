package GameSession;

import GameCard.Hero.Hero;
import GameCard.Hero.HeroPrototype1;
import GameCard.Monster.Monster;
import MPacket.*;
import GameCard.GameCard;
import StaticVars.StatVar;
import io.netty.channel.Channel;
import java.util.ArrayList;

/**
 * Created by Danila on 13.02.17.
 */
public class GameSession {
    boolean isSessionOn = false;
    int state;
    Channel channelP1,channelP2;
    private volatile int packetIdP1 = 0,packetIdP2 = 0;
    Hero heroP1,heroP2;
    private ArrayList<GameCard> deckP1,deckP2, handP1 = new ArrayList<>(),handP2 = new ArrayList<>(),
            monstersPlayedP1 = new ArrayList<>(),monstersPlayedP2 = new ArrayList<>();
    int sessionId;
    public GameSession(int sessionId) {
        this.sessionId = sessionId;
        state = 0;
    }
    int phase = 1;
    public void aquire(Channel channel)
    {
        state++;
        if(channelP1 == null)
        {
            channelP1 = channel;
            if(channelP2 != null)
            {
                initGame();
            }
        }
        else if(channelP2 == null)
        {
            channelP2 = channel;
            initGame();
        }
    }
    public void initGame()
    {
        new GameStartPacket().write(channelP1);
        new GameStartPacket().write(channelP2);
        deckP1 = StatVar.getRandomDefaultDeck();
        deckP2 = StatVar.getRandomDefaultDeck();
        heroP1 = new HeroPrototype1();
        heroP2 = new HeroPrototype1();
        new HeroPacket(heroP1,true).write(channelP1);
        new HeroPacket(heroP2,false).write(channelP1);
        new HeroPacket(heroP2,true).write(channelP2);
        new HeroPacket(heroP1,false).write(channelP2);
        for(int i = 0; i < StatVar.initialHandSize;++i)
        {
            takeCard(1);
            takeCard(2);
        }
        new TurnSwitchPacket().write(channelP1);
    }

    public void action(String string,int player)
    {
        if(player == 1 && phase == 1) {
            String[] action = string.split(" ");
            switch (action[0]) {
                case "Attack": {

                    break;
                }
            }
        }
    }
    public void disconnected(Channel channel)
    {

            if(channelP1.isWritable()) try {
                new DisconnectPacket("Your opponent left!").write(channelP1).sync();
                channelP1.disconnect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if(channelP2.isWritable()) try {
            new DisconnectPacket("Your opponent left!").write(channelP2).sync();
            channelP2.disconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //Game logic
    public void playCard(Channel channel, int cardId)
    {
        if(channel.equals(channelP1))
        {
            if(phase == 1) {
                if(cardId < handP1.size())
                {
                    if(monstersPlayedP1.size() < StatVar.maxMonstersPlayed) {
                        GameCard card = handP1.remove(cardId);
                        monstersPlayedP1.add(card);
                        new ActionRespondPacket(true, "Monster played").write(channelP1);
                        new EnemyMonsterPlayedPacket(card).write(channelP2);
                    }
                    else {
                        new ActionRespondPacket(false, "Board is full").write(channelP1);
                    }
                }
            }
            else {
                new ActionRespondPacket(false, "Not your turn").write(channelP1);
            }
        }
        else {
            if(phase == 2)
            {
                if(monstersPlayedP1.size() < StatVar.maxMonstersPlayed) {
                    GameCard card = handP2.remove(cardId);
                    monstersPlayedP2.add(card);
                    new ActionRespondPacket(true, "Monster played").write(channelP2);
                    new EnemyMonsterPlayedPacket(card).write(channelP1);
                }
                else {
                    new ActionRespondPacket(false, "Board is full").write(channelP2);
                }
            }
            else {
                new ActionRespondPacket(false, "Not your turn").write(channelP2);
            }
        }
    }
    public void endTurn(Channel channel)
    {
        if(channel.equals(channelP1))
        {
            if(phase == 1)
            {
                new ActionRespondPacket(true, "Turn switched").write(channelP1);
                switchTurn();
                new TurnSwitchPacket().write(channelP2);
            }
            else {
                new ActionRespondPacket(false, "Not your turn").write(channelP1);
            }
        }
        else {
            if(phase == 2)
            {
                new ActionRespondPacket(true, "Turn switched").write(channelP2);
                switchTurn();
                new TurnSwitchPacket().write(channelP1);
            }
            else {
                new ActionRespondPacket(false, "Not your turn").write(channelP2);
            }
        }
    }
    private void switchTurn() {
        if(phase == 1) {
            phase = 2;
            takeCard(2);
            heroP2.unexaust();
        }
        else {
            phase = 1;
            heroP1.unexaust();
            takeCard(1);
        }
    }
   private void takeCard(int player)
   {
       if(player == 1)
       {
           if(handP1.size() < StatVar.maxHandSize){
               GameCard card = deckP1.remove(deckP1.size()-1);
               handP1.add(card);
               new CardTakenPacket(card).write(channelP1);
           }

       }
       else if(player == 2) {

           if(handP2.size()<StatVar.maxHandSize) {
               GameCard card = deckP2.remove(deckP2.size()-1);
               handP2.add(card);
               new CardTakenPacket(card).write(channelP2);
           }
       }
   }
   public void heroAttack(Channel channel, int cardId) {
       if(channel.equals(channelP1))
       {
           if(phase == 1) {
               if(!heroP1.isExausted()) {
                   if (cardId < monstersPlayedP2.size()) {
                       heroP1.attackMonster((Monster)monstersPlayedP2.get(cardId));
                   }
               }
           }
       }
   }

    public int getState() {
        return state;
    }

    public int getSessionId() {
        return sessionId;
    }
}
