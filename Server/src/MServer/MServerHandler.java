package MServer;

import CPacket.CPacket;
import GameSession.GameSession;
import MPacket.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Queue;

public class MServerHandler extends ChannelInboundHandlerAdapter {
    private GameSession session;
    private ISearchGame isg;
    private boolean inSession;
    private Queue<CPacket> queue;
    MServerHandler(ISearchGame isg,Queue<CPacket> queue) {
        this.isg = isg;
        this.queue = queue;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Someone connected");
        inSession = false;

    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Someone disconnected");
        if(inSession) isg.disconnectFromGameSession(ctx.channel(),session);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof CPacket) {
                CPacket packet = ((CPacket)msg);

                switch(packet.getT()){
                    case "SearchingPacket" : {
                        System.out.println("Someone searching the game");
                        session = isg.addToGameSession(ctx.channel());
                        if(session != null) inSession = true;
                        if(session.getState() == 2)
                        {
                            session.initGame();
                        }
                        break;
                    }
                    case  "CancelPacket" : {
                        System.out.println("Someone canceled searching the game");
                        if(inSession) isg.disconnectFromGameSession(ctx.channel(),session);
                        break;
                    }
                    default: {
                        packet.bound(session,ctx.channel());
                        queue.add(packet);
                    }
                    /*case "CardClickedPacket": {
                        System.out.println("CardClicked");
                        break;
                    }*/
                }
            }


    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
