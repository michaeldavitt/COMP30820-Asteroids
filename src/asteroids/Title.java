package asteroids;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel {
    public Title(String text) {
        JLabel titleName = new TextLabel(text);
        this.add(titleName);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(100, 100));
    }
}
