package MServer;

import GameCard.GameCard;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by Danila on 11.02.17.
 */
public class MObjToStringEncoder extends MessageToMessageEncoder<GameCard> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GameCard gameCard, List<Object> list) throws Exception {
            list.add(gameCard.serialize());
    }
}
