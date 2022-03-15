package asteroids;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Screen extends JFrame {

    // Reference: https://www.youtube.com/watch?v=Kmgo00avvEw
    public Screen() {
        this.setTitle("Asteroids"); // Sets title of screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Shuts down application when user presses the "x" button
        this.setSize(500, 500); // Sets default screen size
        this.setVisible(true); // Makes screen visible

        // Changes the default icon of our screen
        ImageIcon image = new ImageIcon("resources/asteroid.jpg");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(Color.BLACK); // Sets screen background colour
    }

}
