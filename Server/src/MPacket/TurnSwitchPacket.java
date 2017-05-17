package MPacket;

/**
 * Created by Danila on 14.02.17.
 */
public class TurnSwitchPacket extends MPacket {
    @Override
    public String getContain() {
        return "Its your turn";
    }

    @Override
    public void setT() {
        type = "TurnSwitchPacket";
    }
}
