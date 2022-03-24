package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
//import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Pane root;
	private final int screenX = 800;
	private final int screenY = 600;
	private int currentLevel = 1;
	
	public void switchToWelcomeScreen(ActionEvent event) throws IOException {
		// Create the welcome screen
		root = new Pane();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		root.setPrefSize(screenX, screenY);
		
		// Style the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Adds buttons to the screen to switch scenes
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToGameScreen(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		launchGameButton.setTranslateX(screenX / 2);
		launchGameButton.setTranslateY(screenY / 2);
		root.getChildren().add(launchGameButton);
		
		Button viewControlsButton = new Button("Controls");
		viewControlsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToControlsScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		viewControlsButton.setTranslateX(screenX / 2);
		viewControlsButton.setTranslateY(screenY / 4);
		root.getChildren().add(viewControlsButton);
		
		// Put welcome screen onto the main screen
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchToControlsScreen(ActionEvent event) throws IOException {
		// Generate the controls screen
		root = new Pane();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		root.setPrefSize(screenX, screenY);
		
		// Add styling to the controls screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the controls screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
		
		// Adds buttons to the screen to switch scenes
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToLevelScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		launchGameButton.setTranslateX(screenX / 2);
		launchGameButton.setTranslateY(screenY / 2);
		root.getChildren().add(launchGameButton);
		
		Button returnToWelcomeButton = new Button("Return to Main Menu");
		returnToWelcomeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToWelcomeScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		returnToWelcomeButton.setTranslateX(screenX / 2);
		returnToWelcomeButton.setTranslateY(screenY / 4);
		root.getChildren().add(returnToWelcomeButton);
		
	}
	
	public void switchToGameScreen(ActionEvent event) throws IOException {
		// Generate the game screen
		root = new Pane();
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
		
		// Adds buttons to the screen to switch scenes
		Button switchLevelButton = new Button("View Level Screen");
		switchLevelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToLevelScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		switchLevelButton.setTranslateX(screenX / 3);
		switchLevelButton.setTranslateY(screenY / 3);
		root.getChildren().add(switchLevelButton);
		
		Button switchToGameOverScreenButton = new Button("End game");
		switchToGameOverScreenButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToGameOverScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		switchToGameOverScreenButton.setTranslateX(screenX / 2);
		switchToGameOverScreenButton.setTranslateY(screenY / 4);
		root.getChildren().add(switchToGameOverScreenButton);
				
		
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
		root = new Pane();
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
		
		// Adds buttons to the screen to switch scenes
		Button switchToEnterNameButton = new Button("Enter Name");
		switchToEnterNameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToEnterNameScreen(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		switchToEnterNameButton.setTranslateX(screenX / 2);
		switchToEnterNameButton.setTranslateY(screenY / 2);
		root.getChildren().add(switchToEnterNameButton);
	}
	
	public void switchToEnterNameScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = new Pane();
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
		
		// Adds buttons to the screen to switch scenes
		Button switchToHighScoresButton = new Button("High Scores");
		switchToHighScoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToViewHighScoreScreen(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		switchToHighScoresButton.setTranslateX(screenX / 2);
		switchToHighScoresButton.setTranslateY(screenY / 2);
		root.getChildren().add(switchToHighScoresButton);
	}
	
	public void switchToViewHighScoreScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = new Pane();
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
		
		// Adds buttons to the screen to switch scenes
		Button switchToWelcomeButton = new Button("Return to main menu");
		switchToWelcomeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToWelcomeScreen(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		switchToWelcomeButton.setTranslateX(50);
		switchToWelcomeButton.setTranslateY(50);
		root.getChildren().add(switchToWelcomeButton);
	}
	
	public void switchToLevelScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add text representing the user's current level
		Text levelText = new Text();
		levelText.setText("Level " + currentLevel);
		levelText.setX(screenX / 4);
		levelText.setY(screenY / 4);
		levelText.setFont(Font.font("Verdana", 100));
		levelText.setFill(Color.WHITE);
		root.getChildren().add(levelText);
		
		// Update level
		currentLevel += 1;
		
		// Add text prompting the user to hit enter to begin next level
		Text nextLevelText = new Text();
		nextLevelText.setText("Press Enter to continue");
		nextLevelText.setX(screenX / 4);
		nextLevelText.setY(screenY / 2);
		nextLevelText.setFont(Font.font("Verdana", 30));
		nextLevelText.setFill(Color.WHITE);
		
		Text pressEnterText = new Text();
		pressEnterText.setText("Level " + currentLevel);
		pressEnterText.setX(screenX / 2);
		pressEnterText.setY(screenY * (3/4));
		pressEnterText.setFont(Font.font("Verdana", 30));
		pressEnterText.setFill(Color.WHITE);
		
		root.getChildren().add(nextLevelText);
		root.getChildren().add(pressEnterText);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
		
		// Adds buttons to the screen to switch scenes
//		Button switchToGameButton = new Button("Begin Game");
//		switchToGameButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				try {
//					switchToGameScreen(arg0);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}	
//			}
//		});
//		
//		switchToGameButton.setTranslateX(50);
//		switchToGameButton.setTranslateY(50);
//		root.getChildren().add(switchToGameButton);
		
		// Change scene when the user presses Enter
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
}
