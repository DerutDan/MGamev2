package CPacket;

import GameSession.GameSession;
import io.netty.channel.Channel;

/**
 * Created by Danila on 15.03.17.
 */
public abstract class CPacket implements Comparable<CPacket> {
    int id;
    Object content;
    String type;
    GameSession gameSession;
    Channel channel;
    CPacket() {
        setT();
    }
    public String encode(String string)
    {
        int k = hasEnd(string);
        if(k != -1) {
            String s[] = string.substring(1,k).split(" ");
            if (s.length < 1) return string;
            else {
                id = Integer.parseInt(s[0]);
                StringBuilder strb = new StringBuilder();
                for (int i = 1; i < s.length; ++i)
                    strb.append(s[i]);
                content = setContent(strb.toString());
                return string.substring(k+1);
            }
        }
        else return string;
    }
    int hasEnd(String s) {

        return s.indexOf('#');
    }
    public Object getContent() {
        return content;
    }

    public void bound(GameSession gameSession, Channel channel) {
        this.gameSession = gameSession;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(CPacket o) {
        return id < o.getId() ? 1 : -1;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public Channel getChannel() {
        return channel;
    }

    abstract void setT();
    abstract Object setContent(String string);


    public String getT()
    {
        return type;
    }
}
