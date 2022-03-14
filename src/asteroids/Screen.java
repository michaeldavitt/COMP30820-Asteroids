package asteroids;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen {

    private JFrame frame;
    private JPanel panel;

    // Reference: https://www.youtube.com/watch?v=5o3fMLPY7qY
    public Screen() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(800, 800, 800, 800));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Screen");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Screen();
    }
}
