package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	
	public void switchToWelcomeScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Welcome_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchToControlsScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Controls_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchToGameScreen(ActionEvent event) throws IOException {
		// Generate the game screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Game_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the game screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Create player ship
		Ship playerShip = new Ship();
		
		// Create asteroid
		Asteroid firstAsteroid = new Asteroid();
		
		// Add objects to scene
		root.getChildren().add(playerShip);
		root.getChildren().add(firstAsteroid);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the game screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	
	public void switchToGameOverScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Game_Over_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchToEnterNameScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Enter_Name_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchToViewHighScoreScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("View_High_Score_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add high scores to the screen
		// Create scores object and new user score
		ScoresList scores = new ScoresList();
		Score newScore = new Score("HEM", 444);
		
		// Update scores with new score
		scores.updateHighScore(newScore);
		
		// Get high scores
		Score[] highScores = scores.getHighScores();
		
		// Get high scores
		for (int i = 0; i < highScores.length; i++) {
			Label newHighScoreLabel = new Label(highScores[i].getName() + " - " + highScores[i].getScore());
			newHighScoreLabel.setLayoutX(250);
			newHighScoreLabel.setLayoutY(150 + i * 20);
			root.getChildren().add(newHighScoreLabel);
		}
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
}
