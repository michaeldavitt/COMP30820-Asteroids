package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class SceneController {
	private Scene scene;
	private Pane root;
	private String css;
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;
	private int currentLevel = 0;
	
	public void switchToWelcomeScreen(Stage stage) throws IOException {
		// Create the welcome screen
		Pane root = new Pane(); // Root node onto which we will add objects
		Scene scene = new Scene(root); // A drawing surface - will be different for each screen type (welcome screen, game screen etc.)
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT); // Set preferred screen size
		
		// Style the screen using CSS
		css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add title to welcome users to the game
		Text welcomeText = new Text("Welcome To Asteroids!");
		welcomeText.setTranslateX(170);
		welcomeText.setTranslateY(200);
		welcomeText.setFill(Color.WHITE);
		welcomeText.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(welcomeText);
		
		// Adds buttons to the screen to switch scenes
		SceneController sceneController = new SceneController();
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					sceneController.switchToLevelScreen(stage);
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
					sceneController.switchToControlsScreen(stage);
				} catch (IOException e) {
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
		stage.show();
	}
	
	public void switchToControlsScreen(Stage stage) throws IOException {
		// Generate the controls screen
		root = new Pane();
		scene = new Scene(root);
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		
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
					switchToLevelScreen(stage);
				} catch (IOException e) {
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
					switchToWelcomeScreen(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		returnToWelcomeButton.setTranslateX(230);
		returnToWelcomeButton.setTranslateY(500);
		returnToWelcomeButton.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		root.getChildren().add(returnToWelcomeButton);
		
	}
	
	
	// Method to display the main game screen, where the user will play the game
	public void switchToGameScreen(Stage stage) throws IOException {
		// Generate the game screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Adds styling to the game screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Creates the player ship and place it in the centre of the screen
		Ship playerShip = new Ship(SCREENWIDTH / 2, SCREENHEIGHT / 2);
		root.getChildren().add(playerShip.getCharacter());
		
//		// Creates integer representing the number of asteroids that are spawned
//		int numAsteroids = 10;
//		
//		// Creates an array to store the asteroids that are present on the screen
//		AsteroidMedium[] asteroids = new AsteroidMedium[numAsteroids];
//
//		// Creates a group for the asteroids
//		Group[] groups = new Group[numAsteroids];
//
//		// Creates a rotate transition for the asteroids
//		RotateTransition[] rotateTransitions = new RotateTransition[numAsteroids];
//		
//		// Loops through each asteroid on the screen
//		for (int i = 0; i < numAsteroids; i++) {
//		
//			// Generates a random number between 0 and 10
//			Random r = new Random();
//			int randInt = r.nextInt(10-1) + 1;
// 
//			// Generates a random integer which will either be 1 or -1
//			int a = 1;
//	        int b = -1;
//			int randInt2 = new Random().nextBoolean() ? a : b;
//			
//			// Generates a random integer which will either be 850 or -50
//			int a2 = 850;
//	        int b2 = -50;
//			int randInt3 = new Random().nextBoolean() ? a2 : b2;
//				 
//			// Adds each asteroid to the asteroids array
//		    asteroids[i] = new AsteroidMedium(randInt3, SCREENHEIGHT / randInt);
//		    final int x3 = i;
//
//		    // Adds the asteroid to a group object and adds this to the groups array
//	        groups[i] = new Group(asteroids[i]);
//	        
//	        // Creates an animation timer to handle the movement and collisions
//	        AnimationTimer timer = new AnimationTimer(){
//	        	
//	            @Override
//	            public void handle(long now) {
//	            	
//	            	// Moves the asteroid
//	                asteroids[x3].setTranslateX(asteroids[x3].getTranslateX() + randInt2);
//	                asteroids[x3].setTranslateY(asteroids[x3].getTranslateY() + randInt2);
//	                
//	                
//	                // Puts the asteroid back on the screen if it exits the screen
//	                if (asteroids[x3].getTranslateX() < 0) {
//	                    asteroids[x3].setTranslateX(asteroids[x3].getTranslateX() + 850);
//	                    asteroids[x3].setTranslateY(asteroids[x3].getTranslateY() + 850);
//	                }
//	                if (asteroids[x3].getTranslateY() < 0) {
//	                    asteroids[x3].setTranslateX(asteroids[x3].getTranslateX() + 850);
//	                    asteroids[x3].setTranslateY(asteroids[x3].getTranslateY() + 850);
//	                }
//	                
//	                if (asteroids[x3].getTranslateX() > SCREENWIDTH) {
//	                    asteroids[x3].setTranslateX(asteroids[x3].getTranslateX() - 850);
//	                    asteroids[x3].setTranslateY(asteroids[x3].getTranslateY() - 850);
//	                }
//	                
//	                if (asteroids[x3].getTranslateY() > SCREENHEIGHT) {
//	                    asteroids[x3].setTranslateX(asteroids[x3].getTranslateX() - 850);
//	                    asteroids[x3].setTranslateY(asteroids[x3].getTranslateY() - 850);
//	                }
//	                
//	                Shape intersect = Shape.intersect(playerShip.getCharacter(), asteroids[x3]);
//	
//	                if(intersect.getBoundsInLocal().getWidth() != -1) {
//	                	root.getChildren().remove(playerShip.getCharacter());
//	                }
//	            }
//	        };
//	
//	        timer.start();
//	               
//	        rotateTransitions[i] = new RotateTransition();
//	        rotateTransitions[i].setDuration(Duration.seconds(5.0));
//	        rotateTransitions[i].setNode(asteroids[x3]); 
//	        rotateTransitions[i].setCycleCount(Timeline.INDEFINITE);
//	        rotateTransitions[i].setByAngle(360);
//	        rotateTransitions[i].play();
//	        
//	        // Add objects to scene
//			root.getChildren().add(asteroids[i]);
//		}
		
		// Creates the asteroids arrays
		List<Asteroid> largeAsteroids = new ArrayList<>();
		List<Asteroid> medAsteroids = new ArrayList<>();
		List<Asteroid> smallAsteroids = new ArrayList<>();
		
		for (int i = 0; i < currentLevel; i++) {
		    Random rnd = new Random();
		    Asteroid asteroid = new Asteroid(rnd.nextInt(SCREENWIDTH), rnd.nextInt(SCREENHEIGHT), "Large");
		    largeAsteroids.add(asteroid);
		}

		// Adds the large asteroids to the screen
		largeAsteroids.forEach(asteroid -> root.getChildren().add(asteroid.getCharacter()));
		
		// Adds an empty bullet list
		List<Bullet> bullets = new ArrayList<>();
		
		
		// Add the game screen to the window and show the window
		stage.setScene(scene);
		
		// Adds buttons to the screen to switch scenes (temporary, in the final version, the scene will be switched once the player dies)
//		Button switchLevelButton = new Button("View Level Screen");
//		switchLevelButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				try {
//					switchToLevelScreen(stage);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
//			}
//		});
//		
//		switchLevelButton.setTranslateX(620);
//		switchLevelButton.setTranslateY(0);
//		root.getChildren().add(switchLevelButton);
		
		Button switchToGameOverScreenButton = new Button("End game");
		switchToGameOverScreenButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToGameOverScreen(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		switchToGameOverScreenButton.setTranslateX(730);
		switchToGameOverScreenButton.setTranslateY(0);
		root.getChildren().add(switchToGameOverScreenButton);
		
		// Creates a dictionary/hash map to store the keys that have been pressed for the ship movement/rotation
		Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
		
		// Add onKeyPressed events to trigger certain events (shooting, turning etc.)
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			pressedKeys.put(e.getCode(), Boolean.TRUE);
		});
		
		stage.getScene().setOnKeyReleased(e -> {
			e.consume();
			pressedKeys.put(e.getCode(), Boolean.FALSE);
		});
		
		
		// Main animation timer for defining the reactions to key events
		new AnimationTimer() {

			// variable that defines how much the ship rotation should change when the user hits LEFT or RIGHT
			int delta = 3;
			@Override
			public void handle(long arg0) {
				if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
					playerShip.rotateLeft(delta);
				}
				
				if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
					playerShip.rotateRight(delta);
				}
				
				// Accelerate when the user presses the up arrow key
				if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
		            playerShip.accelerate();
		        }
				
				// Fire a bullet when the user presses SPACE
				if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && bullets.size() < 1) {
				    // we shoot
				    Bullet bullet = new Bullet((int) playerShip.getCharacter().getTranslateX(), (int) playerShip.getCharacter().getTranslateY());
				    bullet.getCharacter().setRotate(playerShip.getCharacter().getRotate());
				    bullets.add(bullet);

				    bullet.accelerate();
				    bullet.setMovement(bullet.getMovement().normalize().multiply(3));

				    root.getChildren().add(bullet.getCharacter());
				}
				
				// Enables game characters to move
				playerShip.move();
		        largeAsteroids.forEach(asteroid -> asteroid.move());
		        medAsteroids.forEach(asteroid -> asteroid.move());
		        smallAsteroids.forEach(asteroid -> asteroid.move());
		        bullets.forEach(bullet -> bullet.move());
		        
		        // Changes the is alive status of the asteroid and the bullet when the bullet hits the asteroid
		        bullets.forEach(bullet -> {
		        	
		        	// Collision with large asteroids
		            largeAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                	
		                	// Change alive status of bullet and hit asteroid
		                    bullet.setAlive(false);
		                    asteroid.setAlive(false);
		                    
		                    // Spawn two new medium asteroids
	                    	for (int i = 0; i < 2; i++) {
		            		    Asteroid medAsteroid = new Asteroid(asteroid.getCharacter().getTranslateX(), asteroid.getCharacter().getTranslateY(), "Medium");
	                    		medAsteroids.add(medAsteroid);
	                    		root.getChildren().add(medAsteroid.getCharacter());
		            		} 
		                }
		            });
		            
		            
		            // Collision with medium asteroids
		            medAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                	
		                	// Change alive status of bullet and asteroid
		                    bullet.setAlive(false);
		                    asteroid.setAlive(false);
		                    
		                    // Spawn new small asteroids
	                    	for (int i = 0; i < 2; i++) {
		            		    Asteroid smallAsteroid = new Asteroid(asteroid.getCharacter().getTranslateX(), asteroid.getCharacter().getTranslateY(), "Small");
	                    		smallAsteroids.add(smallAsteroid);
	                    		root.getChildren().add(smallAsteroid.getCharacter());
		            		} 
		                }
		            });
		            
		            
		            // Collision with small asteroids
		            smallAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                    bullet.setAlive(false);
		                    asteroid.setAlive(false);
		                }
		            });
		        });

		        // Removes bullets that are not alive from the bullets list
		        bullets.stream()
		            .filter(bullet -> !bullet.isAlive())
		            .forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
		        bullets.removeAll(bullets.stream()
		                                .filter(bullet -> !bullet.isAlive())
		                                .collect(Collectors.toList()));
		        
		        
		        // Removes bullets that have travelled too far
		        bullets.stream()
	            .filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
	            .forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
		        bullets.removeAll(bullets.stream()
	                                .filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
	                                .collect(Collectors.toList()));

		        
		        // Removes asteroids that are not alive from the largeAsteroids list
		        largeAsteroids.stream()
		                .filter(asteroid -> !asteroid.isAlive())
		                .forEach(asteroid -> root.getChildren().remove(asteroid.getCharacter()));
		        largeAsteroids.removeAll(largeAsteroids.stream()
		                                    .filter(asteroid -> !asteroid.isAlive())
		                                    .collect(Collectors.toList()));
		        
		        // Removes asteroids that are not alive from the medAsteroids list
		        medAsteroids.stream()
		                .filter(asteroid -> !asteroid.isAlive())
		                .forEach(asteroid -> root.getChildren().remove(asteroid.getCharacter()));
		        medAsteroids.removeAll(medAsteroids.stream()
		                                    .filter(asteroid -> !asteroid.isAlive())
		                                    .collect(Collectors.toList()));
		        
		        // Removes asteroids that are not alive from the smallAsteroids list
		        smallAsteroids.stream()
		                .filter(asteroid -> !asteroid.isAlive())
		                .forEach(asteroid -> root.getChildren().remove(asteroid.getCharacter()));
		        smallAsteroids.removeAll(smallAsteroids.stream()
		                                    .filter(asteroid -> !asteroid.isAlive())
		                                    .collect(Collectors.toList()));
		        
		        // Checks if the game is over
		        if (smallAsteroids.size() == 0 && medAsteroids.size() == 0 && largeAsteroids.size() == 0) {
//		        	System.out.println("Next level");
		        	try {
						switchToLevelScreen(stage);
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	stop();
		        }

		        // Handles collision between the ship and the largeAsteroids
//		        largeAsteroids.forEach(asteroid -> {
//		            if (playerShip.collide(asteroid)) {	
//		            	stop();
//		            }
//		        });
			}
			
		}.start();
		
		stage.show();
		
		// Request focus on the player ship, so that it will react to key events
		playerShip.getCharacter().requestFocus();
	}
	
	
	public void switchToGameOverScreen(Stage stage) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
		// Add game over text
		Text gameOver = new Text("Game Over");
		gameOver.setTranslateX(100);
		gameOver.setTranslateY(300);
		gameOver.setFont(Font.font("Verdana", 100));
		gameOver.setFill(Color.WHITE);
		root.getChildren().add(gameOver);
		
		// Add press enter to continue text
		Text continueText = new Text("Press Enter to continue");
		continueText.setTranslateX(275);
		continueText.setTranslateY(400);
		continueText.setFont(Font.font("Verdana", 20));
		continueText.setFill(Color.WHITE);
		root.getChildren().add(continueText);
		
		// Add on click event so that the scene changes to the enter score scene when the user presses enter
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.ENTER) {
				try {
					switchToEnterNameScreen(stage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void switchToEnterNameScreen(Stage stage) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
		// Add text prompting the user to enter a username
		Text enterNameText = new Text("Enter Username Below");
		enterNameText.setTranslateX(100);
		enterNameText.setTranslateY(200);
		enterNameText.setFont(Font.font("Verdana", 50));
		enterNameText.setFill(Color.WHITE);
		root.getChildren().add(enterNameText);
		
		// Add textfield where the user can type in their username
		TextField userInput = new TextField();
		userInput.setTranslateX(100);
		userInput.setTranslateY(300);
		root.getChildren().add(userInput);
		
		// Extract the user's input and use it to create a score object
//		String username = userInput.getText();
		
		// Add a key event which switches to the view high scores screen when the user presses enter
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.ENTER) {
				try {
					switchToViewHighScoreScreen(stage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void switchToViewHighScoreScreen(Stage stage) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
		// Add styling to the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add high score title
		Text highScoresText = new Text("High Scores");
		highScoresText.setTranslateX(150);
		highScoresText.setTranslateY(150);
		highScoresText.setFont(Font.font("Verdana", 80));
		highScoresText.setFill(Color.WHITE);
		root.getChildren().add(highScoresText);
		
		
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
			newHighScoreLabel.setLayoutX(280);
			newHighScoreLabel.setLayoutY(250 + i * 40);
			newHighScoreLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 32));
			root.getChildren().add(newHighScoreLabel);
		}
		
		
		// Add press enter to continue text
		Text continueText = new Text("Press Enter to continue");
		continueText.setTranslateX(260);
		continueText.setTranslateY(550);
		continueText.setFont(Font.font("Verdana", 20));
		continueText.setFill(Color.WHITE);
		root.getChildren().add(continueText);
		
		
		// Add a key event which switches back to the welcome screen
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.ENTER) {
				try {
					switchToWelcomeScreen(stage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	// Method for switching to the level screen which shows the user which level they are currently on and prompts them to hit enter to begin the next level
	public void switchToLevelScreen(Stage stage) throws IOException {
		// Generate the screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Update level counter
		currentLevel += 1;
		
		// Add text representing the user's current level
		Text levelText = new Text();
		levelText.setText("Level " + currentLevel);
		levelText.setX(200);
		levelText.setY(250);
		levelText.setFont(Font.font("Verdana", 100));
		levelText.setFill(Color.WHITE);
		root.getChildren().add(levelText);
		
		
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
					switchToGameScreen(stage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
