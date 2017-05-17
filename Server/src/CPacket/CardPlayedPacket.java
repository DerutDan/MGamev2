package CPacket;

/**
 * Created by Danila on 01.04.17.
 */
public class CardPlayedPacket extends CPacket {
    @Override
    void setT() {
        type = "CardPlayedPacket";
    }

    @Override
    Object setContent(String string) {
        return Integer.valueOf(string);
    }
}
