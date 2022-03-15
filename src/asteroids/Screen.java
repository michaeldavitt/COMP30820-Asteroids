package asteroids;

import javax.swing.JFrame;

public class Screen extends JFrame {

    // Reference: https://www.youtube.com/watch?v=Kmgo00avvEw
    public Screen() {
        this.setTitle("Asteroids"); // Sets title of screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Shuts down application when user presses the "x" button
        this.setSize(500, 500); // Sets default screen size
        this.setVisible(true); // Makes screen visible
    }

    public static void main(String[] args) {
        Screen frame = new Screen();
    }
}
