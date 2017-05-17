package CPacket;

/**
 * Created by Danila on 15.03.17.
 */
public class CancelPacket extends CPacket {
    @Override
    void setT() {
        type = "CancelPacket";
    }

    @Override
    Object setContent(String string) {
        return "";
    }
}
