package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Class for launching our application for the first time
// After the game window is created, the responsibility of managing the game's content is passed over to the ScreenController class
public class Main extends Application {
	
	MediaPlayer mediaPlayer;
	
	// Main method which launches our application
	public static void main(String[] args) {
		launch(args);
	}
	
	// Method which creates the game window
	// Adds the title of our application and our logo
	public Stage createWindow(Stage stage) {
		// Creates the window
		// Adds image to the window
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Adds background music
		Media backgroundMusic = new Media(getClass().getResource("background_music.mp3").toExternalForm());
		mediaPlayer = new MediaPlayer(backgroundMusic);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			};
		});
		mediaPlayer.play();
		
		// Adds title to the window and makes it re-sizable
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		
		return stage;
	}

	// Method for instantiating an instance of the SceneController class
	// Once SceneController has been instantiated, this instance manages the game content
	@Override
	public void start(Stage stage) throws Exception {
		
		// Calls a method to create the game window
		stage = createWindow(stage);
		
		// Creates an instance of the screen controller class to generate the welcome screen
		new GameController(stage);
		
	}
}
