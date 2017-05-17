package MServer;

import GameSession.GameSession;
import io.netty.channel.Channel;

/**
 * Created by Danila on 19.03.17.
 */
public interface ISearchGame {
    public GameSession addToGameSession(Channel channel);
    public void disconnectFromGameSession(Channel channel, GameSession session);
}
