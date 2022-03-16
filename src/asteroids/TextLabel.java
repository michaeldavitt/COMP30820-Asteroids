package asteroids;

import java.awt.Color;

import javax.swing.JLabel;

public class TextLabel extends JLabel {
    public TextLabel(String text) {
        this.setText(text);
        this.setForeground(Color.WHITE);
    }
}
