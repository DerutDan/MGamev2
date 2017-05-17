package CPacket;

/**
 * Created by Danila on 01.04.17.
 */
public class EndTurnPacket extends CPacket {
    @Override
    void setT() {
        type = "EndTurnPacket";
    }

    @Override
    Object setContent(String string) {
        return "";
    }
}
