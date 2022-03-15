package asteroids;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen(); // Creates basic screen
        GameScreen gameScreen = new GameScreen(); // Creates screen where we will display our game
        TextLabel welcomeText = new TextLabel("Welcome to Asteroids"); // Creates label describing our game
        screen.add(gameScreen); // Adds the game screen to the basic screen
        gameScreen.add(welcomeText); // Adds the label to the game screen
        screen.setVisible(true); // Makes the screen visible
    }
}
