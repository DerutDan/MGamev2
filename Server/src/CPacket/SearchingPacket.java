package CPacket;

/**
 * Created by Danila on 15.03.17.
 */
public class SearchingPacket extends CPacket {


    @Override
    void setT() {
        type = "SearchingPacket";
    }

    @Override
    Object setContent(String string) {
        return null;
    }
}
