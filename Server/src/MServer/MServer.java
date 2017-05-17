package MServer;


import GameSession.GameSession;
import StaticVars.StatVar;
import CPacket.CPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Thread.sleep;


public class MServer {
    final int port = 5556;
    static final Queue<CPacket> packetQueue = new PriorityQueue<>();
    volatile ArrayList<GameSession> readySessions = new ArrayList<>();
    volatile Queue<Integer> sessionsAllowed = new PriorityQueue<>();
    volatile Queue<GameSession> waitingSessions = new PriorityQueue<>();
    Thread packetPicker;

    public void run()
    {
        for(int i = 0; i < StatVar.maxSessionsCount;++i)
            sessionsAllowed.add(i);
        ISearchGame isg = new ISearchGame() {
            @Override
            public GameSession addToGameSession(Channel channel) {
                if(waitingSessions.isEmpty())
                {
                    if(!sessionsAllowed.isEmpty()) {
                        GameSession gs = new GameSession(sessionsAllowed.poll());
                        gs.aquire(channel);
                        waitingSessions.add(gs);
                        return gs;
                    }
                }
                else {
                    GameSession gs = waitingSessions.poll();
                    readySessions.add(gs);
                    gs.aquire(channel);

                    return gs;
                }
                return null;
            }

            @Override
            public void disconnectFromGameSession(Channel channel,GameSession session) {
                //session.disconnected(player);
                int k = session.getState();
                if(k == 1) {
                    waitingSessions.remove(session);
                    sessionsAllowed.add(session.getSessionId());
                }
                else if(k == 2) {
                    readySessions.remove(session);
                    sessionsAllowed.add(session.getSessionId());
                    session.disconnected(channel);
                }
            }
        };

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup io = new NioEventLoopGroup(2);
        try {
            ServerBootstrap server = new ServerBootstrap()
                    .group(boss, io)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new MServerInitializer(isg,packetQueue));
            //sessions.add(new GameSession());
            StatVar.initMonsterDeck();

            ChannelFuture chf = server.bind(port).sync();
            packetPicker = packetPicker();
            packetPicker.start();

            chf.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally
        {
            io.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
    public static void main(String args[])
    {
        MServer serv = new MServer();
        serv.run();
    }
    Thread packetPicker() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (packetQueue.isEmpty())
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    else {
                        CPacket packet = packetQueue.poll();
                        GameSession session = packet.getGameSession();
                        Channel channel = packet.getChannel();
                        switch (packet.getT()) {
                            case "CardPlayedPacket": {
                                session.playCard(channel,(int)packet.getContent());
                                break;
                            }
                            case "EndTurnPacket": {
                                session.endTurn(channel);
                                break;
                            }
                            case "HeroAttacedPacket" : {
                                session.heroAttack(channel, (int)packet.getContent());
                            }
                            case "ErrorPacket" : {
                                System.out.println("ErrorPacketOccured");
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

}
