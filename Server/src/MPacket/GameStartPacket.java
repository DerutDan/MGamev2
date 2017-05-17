package MPacket;

/**
 * Created by Danila on 13.02.17.
 */
public class GameStartPacket extends MPacket {

    @Override
    public String getContain() {
        return "";
    }

    @Override
    public void setT() {
        type = "GameStartedPacket";
    }
}
