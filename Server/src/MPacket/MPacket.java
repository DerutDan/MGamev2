package MPacket;


import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public abstract class MPacket {
    String type;
    MPacket() { setT(); }
    public ChannelFuture write(Channel channel, int id) {
            return channel.writeAndFlush(getT() + " " + String.valueOf(id) + " " + getContain() + " #");
    }
    public ChannelFuture write(Channel channel) {
        return write(channel,0);
    }
    public abstract String getContain();
    public abstract void setT();
    public String getT() {
        return type;
    }
}
