package CPacket;

/**
 * Created by Danila on 15.03.17.
 */
public class ErrorPacket extends CPacket {
    @Override
    void setT() {
        type = "ErrorPacket";
    }

    @Override
    Object setContent(String string) {
        return null;
    }
}
