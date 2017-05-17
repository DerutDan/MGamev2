package CPacket;

/**
 * Created by Danila on 14.04.17.
 */
public class HeroAttackedPacket extends CPacket {
    @Override
    void setT() {
        type = "HeroAttackedPacket";
    }

    @Override
    Object setContent(String string) {
        return Integer.valueOf(string);
    }
}
