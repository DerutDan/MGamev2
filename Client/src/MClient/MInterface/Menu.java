package MClient.MInterface;

import javax.swing.*;
import MClient.StaticVar;

/**
 * Created by Danila on 01.03.17.
 */
public class Menu extends JFrame {
    public Menu() {
        setBounds(0,0,StaticVar.menuWidth,StaticVar.menuHeight);
        JPanel panel = new JPanel();
        JButton search = new JButton("Search");
        search.setBounds((StaticVar.menuWidth - StaticVar.menuButtonWidth)/2,
                0, StaticVar.menuButtonWidth, StaticVar.menuButtonHeight);
        panel.add(search);
        add(panel);
        setFocusable(true);
        setVisible(true);

    }
}
