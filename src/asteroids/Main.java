package asteroids;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen();
        GameScreen gameScreen = new GameScreen();
        screen.add(gameScreen);
    }
}
