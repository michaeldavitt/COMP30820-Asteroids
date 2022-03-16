package asteroids;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GameScreen extends JPanel {
    public GameScreen() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
    }
}
