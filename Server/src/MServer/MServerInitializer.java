package MServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Queue;

public class MServerInitializer extends ChannelInitializer<SocketChannel> {
    ISearchGame isg;
    Queue queue;
    MServerInitializer(ISearchGame isg, Queue queue) {
        this.isg = isg;
        this.queue = queue;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipe = socketChannel.pipeline();

        pipe.addLast("MtSencoder", new ObjectEncoder());
        pipe.addLast("Sencoder", new StringEncoder());
        pipe.addLast("decoder", new StringDecoder());
        pipe.addLast("StrToPack", new MStrToPacketDecoder());
        pipe.addLast("handler", new MServerHandler(isg,queue));
    }
}
