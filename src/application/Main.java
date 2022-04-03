package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;


//Reference: https://www.youtube.com/watch?v=9XJicRt_FaI
public class Main extends Application {
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;
	
	// Main method which launches our application
	public static void main(String[] args) {
		launch(args);
	}

	// Method for creating our first screen
	// A stage variable is passed as an input, which represents the window on which we will view our application
	@Override
	public void start(Stage stage) throws Exception {
		
		// Creates the window
		// Adds image to the window
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Adds title to the window and makes it re-sizable
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		
		// Creates an instance of the screen controller class to generate the welcome screen
		SceneController sceneController = new SceneController();
		sceneController.switchToWelcomeScreen(stage);
		
	}
}
