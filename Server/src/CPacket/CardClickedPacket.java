package CPacket;

/**
 * Created by Danila on 18.03.17.
 */
public class CardClickedPacket extends CPacket {
    @Override
    void setT() {
        type = "CardClickedPacket";
    }

    @Override
    Object setContent(String string) {
        return Integer.valueOf(string);
    }


}
