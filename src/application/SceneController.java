package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.scene.Group;
import java.lang.Math;
import java.util.Random;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Pane root;
	private final int screenX = 800;
	private final int screenY = 600;
	private int currentLevel = 1;
	public boolean rotateLeft = false;
	public boolean rotateRight = false;
	
	public void switchToWelcomeScreen(ActionEvent event) throws IOException {
		// Create the welcome screen
		root = new Pane();
		Scene scene = new Scene(root);
		root.setPrefSize(screenX, screenY);
		
		// Style the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Reset current level to 1
		currentLevel = 1;
		
		// Add title to welcome users to the game
		Text welcomeText = new Text("Welcome To Asteroids!");
		welcomeText.setTranslateX(170);
		welcomeText.setTranslateY(200);
		welcomeText.setFill(Color.WHITE);
		welcomeText.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(welcomeText);
		
		// Adds buttons to the screen to switch scenes
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToLevelScreen(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		launchGameButton.setTranslateX(250);
		launchGameButton.setTranslateY(300);
		launchGameButton.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
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
		
		viewControlsButton.setTranslateX(275);
		viewControlsButton.setTranslateY(400);
		viewControlsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(viewControlsButton);
		
		// Put welcome screen onto the main screen
		stage.setScene(scene);
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
		
		// Add the controls screen to the window and show the window
		stage.setScene(scene);
		
		Text controlsText = new Text("Game Controls");
		controlsText.setTranslateX(250);
		controlsText.setTranslateY(100);
		controlsText.setFill(Color.WHITE);
		controlsText.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(controlsText);
		
		// Add controls
		// Rotate the ship anti-clockwise
		Control leftArrowKey = new Control("LEFT ARROW KEY (<-): Press and hold the left arrow key to rotate the ship in an anti-clockwise direction");
		leftArrowKey.setTranslateX(50);
		leftArrowKey.setTranslateY(150);
		root.getChildren().add(leftArrowKey);
		
		// Rotate the ship clockwise
		Control rightArrowKey = new Control("RIGHT ARROW KEY (->): Press and hold the right arrow key to rotate the ship in a clockwise direction");
		rightArrowKey.setTranslateX(50);
		rightArrowKey.setTranslateY(210);
		root.getChildren().add(rightArrowKey);
		
		// Accelerate
		Control upArrowKey = new Control("UP ARROW KEY: Press and hold the up arrow key to accelerate the ship. Note that the ship will continue moving in the same direction until thrust is applied in the opposite direction");
		upArrowKey.setTranslateX(50);
		upArrowKey.setTranslateY(270);
		root.getChildren().add(upArrowKey);
		
		// Shoot
		Control space = new Control("SPACE: Press and hold the space bar to shoot at enemies");
		space.setTranslateX(50);
		space.setTranslateY(355);
		root.getChildren().add(space);
		
		// Hyperspace jump
		Control sKey = new Control("S: Press S to perform a hyperspace jump (disappear and reappear in a random location)");
		sKey.setTranslateX(50);
		sKey.setTranslateY(390);
		root.getChildren().add(sKey);
		
		
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
		
		launchGameButton.setTranslateX(300);
		launchGameButton.setTranslateY(450);
		launchGameButton.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
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
		
		returnToWelcomeButton.setTranslateX(230);
		returnToWelcomeButton.setTranslateY(500);
		returnToWelcomeButton.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
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
		
		int x = 10;
		
		Asteroid[] asteroids = new Asteroid[x];

		Group[] groups = new Group[x];
		Path[] paths = new Path[x];
		PathTransition[] pathTransitions = new PathTransition[x];
		RotateTransition[] rotateTransitions = new RotateTransition[x];
		
		for (int i = 0; i < x; i++) {
		    //get input variables;
		    asteroids[i] = new Asteroid();
		
	        groups[i] = new Group(asteroids[i]);
	
			int[] randomOfTwoInts = new int[8];
			
			for (int j = 0; j < 4; j++) {  
				int a = -50;
		        int b = 850;
				randomOfTwoInts[j] = new Random().nextBoolean() ? a : b;
			}
	
	        paths[i] = new Path();
	        paths[i].getElements().add(new MoveTo(randomOfTwoInts[0], Math.random()*1000));
	        paths[i].getElements().add(new LineTo(randomOfTwoInts[1], Math.random()*1000));
	        paths[i].getElements().add(new LineTo(randomOfTwoInts[2], Math.random()*1000));
	        paths[i].getElements().add(new LineTo(randomOfTwoInts[3], Math.random()*1000));
	       
	        paths[i].setOpacity(0);
	
	        groups[i].getChildren().add(paths[i]);
	
	        pathTransitions[i] = new PathTransition();
	
	        pathTransitions[i].setDuration(Duration.seconds(10.0));
	        pathTransitions[i].setPath(paths[i]);
	        pathTransitions[i].setNode(asteroids[i]); 
	        pathTransitions[i].setCycleCount(Timeline.INDEFINITE);
	        pathTransitions[i].setAutoReverse(true);
	        pathTransitions[i].play();
	        
	        
	        rotateTransitions[i] = new RotateTransition();
	        rotateTransitions[i].setDuration(Duration.seconds(10.0));
	        rotateTransitions[i].setNode(asteroids[i]); 
	        rotateTransitions[i].setCycleCount(Timeline.INDEFINITE);
	        rotateTransitions[i].setByAngle(360);
	        rotateTransitions[i].play();
			
			// Add objects to scene
			root.getChildren().add(asteroids[i]);
		}
		
		root.getChildren().add(playerShip);
		
		// Add the game screen to the window and show the window
		stage.setScene(scene);
		
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
				rotateRight = true;
			} else if (e.getCode() == KeyCode.LEFT) {
				rotateLeft = true;
			}
		});
		
		stage.getScene().setOnKeyReleased(e -> {
			e.consume();
			if (e.getCode() == KeyCode.RIGHT) {
				rotateRight = false;
			} else if (e.getCode() == KeyCode.LEFT) {
				rotateLeft = false;
			}
		});
		
		AnimationTimer timer = new AnimationTimer() {

			int delta = 2;
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if (rotateLeft == true) {
					playerShip.rotateLeft(delta);
				}
				if (rotateRight == true) {
					playerShip.rotateRight(delta);
				}
			}
			
		};
		
		timer.start();
		
		stage.show();
		
		// Request focus on the player ship, so that it will react to key events
		playerShip.requestFocus();
	}
	
	
	public void switchToGameOverScreen(ActionEvent event) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
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
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
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
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
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
		// Generate the screen
		root = new Pane();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		root.setPrefSize(screenX, screenY);
		scene = new Scene(root);
		
		// Add styling to the screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add text representing the user's current level
		Text levelText = new Text();
		levelText.setText("Level " + currentLevel);
		levelText.setX(200);
		levelText.setY(250);
		levelText.setFont(Font.font("Verdana", 100));
		levelText.setFill(Color.WHITE);
		root.getChildren().add(levelText);
		
		// Update level
		currentLevel += 1;
		
		// Add text prompting the user to hit enter to begin next level
		Text nextLevelText = new Text();
		nextLevelText.setText("Press Enter to continue");
		nextLevelText.setX(200);
		nextLevelText.setY(400);
		nextLevelText.setFont(Font.font("Verdana", 30));
		nextLevelText.setFill(Color.WHITE);
		
		root.getChildren().add(nextLevelText);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
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
