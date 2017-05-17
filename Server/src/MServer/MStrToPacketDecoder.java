package MServer;

import CPacket.*;
import MPacket.CardTakenPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


import java.util.List;

/**
 * Created by Danila on 15.03.17.
 */
public class MStrToPacketDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
        int k = s.indexOf(' ');
        String string = s.substring(0,k);
        CPacket packet;
        switch(string)
        {
            case "SearchingPacket": {
                packet = new SearchingPacket();

                break;
            }
            case "CancelPacket": {
                packet = new CancelPacket();
                break;
            }
            case "CardClickedPacket": {
                packet=new CardClickedPacket();
                break;
            }
            case "EndTurnPacket" : {
                packet = new EndTurnPacket();
                break;
            }
            case "CardPlayedPacket" : {
                packet = new CardPlayedPacket();
                break;
            }
            case "HeroAttackPacket" : {
                packet = new HeroAttackedPacket();
                break;
            }
            default: packet = new ErrorPacket();
        }
        String regex = packet.encode(s.substring(k));
        if(!regex.equals(""))
            list.add(regex);
        list.add(packet);
    }
}
