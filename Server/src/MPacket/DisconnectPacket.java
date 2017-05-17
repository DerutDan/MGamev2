package MPacket;

//When disconnecting user
public class DisconnectPacket extends MPacket {
    String reason;
    public DisconnectPacket(String reason)
    {
        this.reason = reason;
    }
    @Override
    public String getContain() {
        return "Disconneted due to:" + reason;
    }

    @Override
    public void setT() {
        type = "DisconnectPacket";
    }
}
