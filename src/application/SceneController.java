package application;

import java.io.IOException;

import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
//import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	private final int screenX = 800;
	private final int screenY = 600;
	private int currentLevel = 1;
	
	public void switchToWelcomeScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Welcome_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root.setPrefSize(screenX, screenY);
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
		root.setPrefSize(screenX, screenY);
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
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the game screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Create player ship
		Ship playerShip = new Ship(screenX / 2, screenY / 2);
		
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
		
		// Get ship to rotate
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.RIGHT) {
				playerShip.rotateRight();
			} else if (e.getCode() == KeyCode.LEFT) {
				playerShip.rotateLeft();
			}
		});
		
		stage.show();
		
		// Request focus on the player ship, so that it will react to key events
		playerShip.requestFocus();
	}
	
	
	public void switchToGameOverScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Game_Over_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root.setPrefSize(screenX, screenY);
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
		root.setPrefSize(screenX, screenY);
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
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add high scores to the screen
		// Create scores object and new user score
		ScoresList scores = new ScoresList();
		Score newScore = new Score("TRK", 5000);
		
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
		
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.ENTER) {
				try {
					switchToGameScreen(event);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void switchToLevelScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = FXMLLoader.<AnchorPane>load(getClass().getResource("Level_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add text representing the user's current level
		Text levelText = new Text();
		levelText.setText("Level " + currentLevel + " complete!");
		levelText.setX(screenX / 4);
		levelText.setY(screenY / 4);
		levelText.setFont(Font.font("Verdana", 50));
		levelText.setFill(Color.WHITE);
		root.getChildren().add(levelText);
		
		// Update level
		currentLevel += 1;
		
		// Add text prompting the user to hit enter to begin next level
		Text nextLevelText = new Text();
		nextLevelText.setText("Press Enter to begin Level " + currentLevel);
		nextLevelText.setX(screenX / 4);
		nextLevelText.setY(screenY / 2);
		nextLevelText.setFont(Font.font("Verdana", 30));
		nextLevelText.setFill(Color.WHITE);
		root.getChildren().add(nextLevelText);
		
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
