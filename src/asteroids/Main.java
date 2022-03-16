package asteroids;

import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen(); // Creates basic screen
        GameScreen gameScreen = new GameScreen(); // Creates screen where we will display our game
        Title gameTitle = new Title("Welcome to Asteroids"); // Creates title panel describing our game
        screen.add(gameTitle, BorderLayout.NORTH); // Adds the title to the screen
        screen.add(gameScreen, BorderLayout.CENTER); // Adds the game screen to the basic screen
        screen.setVisible(true); // Makes the screen visible
    }
}
