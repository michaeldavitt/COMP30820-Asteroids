package application;

// Imports required packages
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


// Class representing the application in general
// In this class, we control the content that is displayed on the screen
public class SceneController {
	// Initialise scene controller variables
	// These variables all relate to the game screen (the window itself, the content in the window, size of the window etc.)
	// Current level is also initialised to be 0, and this will be reset in each iteration of the game
	private Stage stage;
	private Scene scene;
	private Pane root;
	private String css;
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;
	private int currentLevel = 0;
	private AtomicInteger score;
	AudioClip explosionSoundEffect;
	
	
	// Constructor for the scene controller
	// Here, we assign the game window to the stage variable and define the default css styles
	// We also call the switch to welcome screen method, which generates the welcome screen for the user
	public SceneController(Stage stage) {
		this.stage = stage;
		this.css = this.getClass().getResource("application.css").toExternalForm();
		
		try {
			switchToWelcomeScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Method that displays the welcome screen to the user
	// This is the first screen that the user will see when they start the game
	// When the user dies and wants to play again, they will return to this screen before starting
	public void switchToWelcomeScreen() throws IOException {
		// Resets current level to 0 before the user starts playing
		currentLevel = 0;
		
		// Resets score to 0
		score = new AtomicInteger();
		
		// Create the welcome screen
		root = new Pane(); // Root node onto which we will add objects
		scene = new Scene(root); // A drawing surface - will be different for each screen type (welcome screen, game screen etc.)
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT); // Set preferred screen size
		
		// Style the screen using CSS
		scene.getStylesheets().add(css);
		
		// Add title to welcome users to the game
		Text welcomeText = new Text("Welcome To Asteroids!");
		welcomeText.setTranslateX(170);
		welcomeText.setTranslateY(200);
		welcomeText.setFill(Color.WHITE);
		welcomeText.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(welcomeText);
		
		// Add button to start the game
		// Clicking this button will go to the level screen, which will display "Level 1" before starting the game
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToLevelScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		launchGameButton.setTranslateX(250);
		launchGameButton.setTranslateY(300);
		launchGameButton.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(launchGameButton);
		
		
		// Add button to view game controls
		// Clicking this button will redirect the user to a page with the game controls described
		Button viewControlsButton = new Button("Controls");
		viewControlsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToControlsScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		viewControlsButton.setTranslateX(275);
		viewControlsButton.setTranslateY(400);
		viewControlsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(viewControlsButton);
		
		
		// Show the welcome screen to the user
		stage.setScene(scene);
		stage.show();
	}
	
	
	// Method that displays the controls screen to the user
	// Users can view the buttons they need to press to interact with the game on this screen
	public void switchToControlsScreen() throws IOException {
		// Generates the controls screen
		root = new Pane();
		scene = new Scene(root);
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		
		// Adds styling to the controls screen
		scene.getStylesheets().add(css);
		
		// Adds a title to the game controls screen
		Text controlsText = new Text("Game Controls");
		controlsText.setTranslateX(250);
		controlsText.setTranslateY(100);
		controlsText.setFill(Color.WHITE);
		controlsText.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
		root.getChildren().add(controlsText);
		
		// Adds controls
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
		
		
		// Adds button to launch the game
		// Clicking this button will launch the level screen with "Level 1" before the game starts
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToLevelScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		launchGameButton.setTranslateX(300);
		launchGameButton.setTranslateY(450);
		launchGameButton.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		root.getChildren().add(launchGameButton);
		
		
		// Adds button to return to the main menu screen
		// Clicking this button will return the user to the welcome screen that was displayed to the user when they first launched the application
		Button returnToWelcomeButton = new Button("Return to Main Menu");
		returnToWelcomeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					switchToWelcomeScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		returnToWelcomeButton.setTranslateX(230);
		returnToWelcomeButton.setTranslateY(500);
		returnToWelcomeButton.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		root.getChildren().add(returnToWelcomeButton);
		
		
		// Add the controls screen to the window and show the window
		stage.setScene(scene);
	}
	
	
	// Method to display the main game screen, where the user will play the game
	public void switchToGameScreen() throws IOException {
		// Generate the game screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Adds styling to the game screen
		scene.getStylesheets().add(css);
		
		// Adds the current level to the screen
		Text currentLevelText = new Text(10, 20, "Level: " + currentLevel);
		currentLevelText.setFill(Color.WHITE);
		currentLevelText.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
	    root.getChildren().add(currentLevelText);
	    
		// Adds the player's score to the screen
		Text pointsTally = new Text(10, 40, "Points: " + score);
		pointsTally.setFill(Color.WHITE);
		pointsTally.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
	    root.getChildren().add(pointsTally);
	    
		
		// Spawns the enemy ship at either end of the screen
		// The spawn location of the ship is random (either left or right)
		int a2 = 850;
        int b2 = -50;
		int randInt3 = new Random().nextBoolean() ? a2 : b2;
		
		Random r = new Random();
		int randInt = r.nextInt(SCREENHEIGHT-1) + 1;
		
		// Spawns the enemy ship and adds it to the screen
		List<EnemyShip> enemyShips = new ArrayList<>();
		EnemyShip enemyShip = new EnemyShip(randInt3, randInt);
		
		enemyShips.add(enemyShip);
		enemyShips.forEach(enemy -> {
			root.getChildren().add(enemy.getCharacter());
			enemyShip.accelerateSlow();
		});
		
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
		List<EnemyBullet> enemyBullets = new ArrayList<>();
		
		// Creates an array to store all of the asteroids
    	List<Character> enemyCharacters = new ArrayList<>();	
		enemyCharacters.addAll(largeAsteroids);
		enemyCharacters.addAll(medAsteroids);
		enemyCharacters.addAll(smallAsteroids);
		enemyCharacters.addAll(enemyShips);
		
		// Spawn the playerShip
		Ship playerShip = new Ship(SCREENWIDTH / 2, SCREENHEIGHT / 2);
		spawnPlayerShip(playerShip, enemyCharacters);
		root.getChildren().add(playerShip.getCharacter());
		
		// Adds the player's health to the screen
	    Text playerHealthTally = new Text(10, 60, "Lives Remaining: " + playerShip.getLives());
	    playerHealthTally.setFill(Color.WHITE);
	    playerHealthTally.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
	    root.getChildren().add(playerHealthTally);
		
		// Adds the game screen to the window and show the window
		stage.setScene(scene);
		
		
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
			
			private long lastBulletUpdate = 0;
			private long lastHyperSpaceJump = 0;
			private long lastEnemyShot = 0;
			
			@Override
			public void handle(long now) {
				if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
					playerShip.rotateLeft(delta);
				}
				
				if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
					playerShip.rotateRight(delta);
				}
				
				// Accelerate when the user presses the up arrow key
				if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
		            playerShip.accelerate();
		        };

		        
		        // Respawn the player in a random location when the user presses S
			    if (pressedKeys.getOrDefault(KeyCode.S, false) && now - lastHyperSpaceJump >= 280_000_000){
			    	
			    	// Create a list containing all asteroids
			    	List<Character> enemyCharacters = new ArrayList<>();	
					enemyCharacters.addAll(largeAsteroids);
					enemyCharacters.addAll(medAsteroids);
					enemyCharacters.addAll(smallAsteroids);	
					enemyCharacters.addAll(enemyShips);
					
					// Spawn the player ship and ensure that the player does not collide with asteroids upon spawning
					spawnPlayerShip(playerShip, enemyCharacters);
			    	
			    	// Resets last hyper space jump variable
			    	lastHyperSpaceJump = now;
			    }
			    
				
				// Fire a bullet when the user presses SPACE
				if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && now - lastBulletUpdate >= 280_000_000) {
				    // we shoot
				    Bullet bullet = new Bullet((int) playerShip.getCharacter().getTranslateX(), (int) playerShip.getCharacter().getTranslateY());
				    bullet.getCharacter().setRotate(playerShip.getCharacter().getRotate());
				    bullets.add(bullet);

				    bullet.accelerate();
				    bullet.setMovement(bullet.getMovement().normalize().multiply(4).add(playerShip.getMovement()));

				    root.getChildren().add(bullet.getCharacter());
				    
				    AudioClip shootingSoundEffect = new AudioClip(getClass().getResource("shooting.mp3").toExternalForm());
				    shootingSoundEffect.play();
				    
				    lastBulletUpdate = now;

				}
				
				// Get the enemy ship to fire a bullet
				enemyShips.forEach(enemy -> {
					if (now - lastEnemyShot >= 1080_000_000 && enemy.isAlive()) {
						// Get the change in x divided by the change in y = slope formula
						double deltaX = (playerShip.getCharacter().getTranslateX() - enemy.getCharacter().getTranslateX());
						double deltaY = (enemy.getCharacter().getTranslateY() - playerShip.getCharacter().getTranslateY());
						double shootingDirection = Math.toDegrees(Math.atan2(deltaY, deltaX));
						
						EnemyBullet bullet = new EnemyBullet((int) enemy.getCharacter().getTranslateX(), (int) enemy.getCharacter().getTranslateY());
						bullet.getCharacter().setRotate(shootingDirection);
						enemyBullets.add(bullet);
						
						bullet.accelerate();
					    bullet.setMovement(bullet.getMovement().normalize().multiply(5));

					    root.getChildren().add(bullet.getCharacter());
					    
					    AudioClip shootingSoundEffect = new AudioClip(getClass().getResource("shooting.mp3").toExternalForm());
					    shootingSoundEffect.play();
					    
					    lastEnemyShot = now;
					}
				});
					
				// Enables game characters to move
				playerShip.move();
				enemyShips.forEach(enemy -> enemy.move());
		        largeAsteroids.forEach(asteroid -> asteroid.move());
		        medAsteroids.forEach(asteroid -> asteroid.move());
		        smallAsteroids.forEach(asteroid -> asteroid.move());
		        bullets.forEach(bullet -> bullet.move());
		        enemyBullets.forEach(bullet -> bullet.move());
		        
		        // Changes the is alive status of the asteroid and the bullet when the bullet hits the asteroid
		        bullets.forEach(bullet -> {
		        	
		        	enemyShips.forEach(enemy -> {
		        		if(bullet.collide(enemy)) {
				        	bullet.setAlive(false);
				        	enemy.setAlive(false);
				        	pointsTally.setText("Points: " + score.addAndGet(500));
				        	
				        	explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
		                    explosionSoundEffect.play();
				        }
		        	});
			        
			        	
		        	// Collision with large asteroids
		            largeAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                	
		                	// Change alive status of bullet and hit asteroid
		                    bullet.setAlive(false);
		                    pointsTally.setText("Points: " + score.addAndGet(100));
		                    asteroid.setAlive(false);
		                    
		                    // Spawn two new medium asteroids
		                    spawnAsteroids(asteroid, "Medium", medAsteroids);
		                    
		                    explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
		                    explosionSoundEffect.play();
		                }
		            });
			            
			            
		            // Collision with medium asteroids
		            medAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                	
		                	// Change alive status of bullet and asteroid
		                    bullet.setAlive(false);
		                    pointsTally.setText("Points: " + score.addAndGet(200));
		                    asteroid.setAlive(false);
		                    
		                    // Spawn new small asteroids
		                    spawnAsteroids(asteroid, "Small", smallAsteroids);
		                    
		                    explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
		                    explosionSoundEffect.play();
		                }
		            });
			            
			            
		            // Collision with small asteroids
		            smallAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                    bullet.setAlive(false);
		                    pointsTally.setText("Points: " + score.addAndGet(300));
		                    asteroid.setAlive(false);
		                    
		                    explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
		                    explosionSoundEffect.play();
		                }
		            });
		            
		        });
		        
		        // Player gets shot
		        enemyBullets.forEach(bullet -> {
		        	if (playerShip.collide(bullet)) {
		        		playerShip.decrementLives();
		        		bullet.setAlive(false);
		        		
		        		// Decrease player's health
		        		playerHealthTally.setText("Lives Remaining: " + playerShip.getLives());
		        		
		        		// Spawn player ship in a safe location
		        		List<Character> enemyCharacters = new ArrayList<>();	
						enemyCharacters.addAll(largeAsteroids);
						enemyCharacters.addAll(medAsteroids);
						enemyCharacters.addAll(smallAsteroids);	
						enemyCharacters.addAll(enemyShips);
		        		spawnPlayerShip(playerShip, enemyCharacters);
		        		
		        		explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
	                    explosionSoundEffect.play();
		        	}
		        });
		        
		        // Collision between player ship and large asteroid
		        largeAsteroids.forEach(asteroid -> {
		        	if (playerShip.collide(asteroid)) {
		        		playerShip.decrementLives();
		        		asteroid.setAlive(false);
		        		
		        		// Spawn new medium asteroids
		        		spawnAsteroids(asteroid, "Medium", medAsteroids);
		        		
		        		// Decrease player health
		        		playerHealthTally.setText("Lives Remaining: " + playerShip.getLives());
		        		
		        		// Spawn player ship in a safe location
		        		List<Character> enemyCharacters = new ArrayList<>();	
						enemyCharacters.addAll(largeAsteroids);
						enemyCharacters.addAll(medAsteroids);
						enemyCharacters.addAll(smallAsteroids);	
						enemyCharacters.addAll(enemyShips);
		        		spawnPlayerShip(playerShip, enemyCharacters);
		        		
		        		explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
	                    explosionSoundEffect.play();
		        	}
		        });
		        
		        // Collision between player ship and medium asteroid
		        medAsteroids.forEach(asteroid -> {
		        	if (playerShip.collide(asteroid)) {
		        		playerShip.decrementLives();
		        		asteroid.setAlive(false);
		        		
		        		// Spawn new small asteroids
		        		spawnAsteroids(asteroid, "Small", smallAsteroids);
		        		
		        		// Decrease player health
		        		playerHealthTally.setText("Lives Remaining: " + playerShip.getLives());
		        		
		        		// Spawn player ship in a safe location
		        		List<Character> enemyCharacters = new ArrayList<>();	
						enemyCharacters.addAll(largeAsteroids);
						enemyCharacters.addAll(medAsteroids);
						enemyCharacters.addAll(smallAsteroids);	
						enemyCharacters.addAll(enemyShips);
		        		spawnPlayerShip(playerShip, enemyCharacters);
		        		
		        		explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
	                    explosionSoundEffect.play();
		        	}
		        });
		        
		        // Collision between player ship and small asteroid
		        smallAsteroids.forEach(asteroid -> {
		        	if (playerShip.collide(asteroid)) {
		        		playerShip.decrementLives();
		        		asteroid.setAlive(false);
		        		
		        		// Decrease player health
		        		playerHealthTally.setText("Lives Remaining: " + playerShip.getLives());
		        		
		        		// Spawn player ship in a safe location
		        		List<Character> enemyCharacters = new ArrayList<>();	
						enemyCharacters.addAll(largeAsteroids);
						enemyCharacters.addAll(medAsteroids);
						enemyCharacters.addAll(smallAsteroids);	
						enemyCharacters.addAll(enemyShips);
		        		spawnPlayerShip(playerShip, enemyCharacters);
		        		
		        		explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
	                    explosionSoundEffect.play();
		        	}
		        });
		        
		        // Collision between player ship and enemy ship
		        enemyShips.forEach(enemy -> {
		        	if (playerShip.collide(enemy)) {
			        	playerShip.decrementLives();
			        	enemy.setAlive(false);
			        	
			        	// Decrease player health
		        		playerHealthTally.setText("Lives Remaining: " + playerShip.getLives());
			        	
			        	// Spawn player ship in a safe location
		        		List<Character> enemyCharacters = new ArrayList<>();	
						enemyCharacters.addAll(largeAsteroids);
						enemyCharacters.addAll(medAsteroids);
						enemyCharacters.addAll(smallAsteroids);	
						enemyCharacters.addAll(enemyShips);
		        		spawnPlayerShip(playerShip, enemyCharacters);
		        		
		        		explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
	                    explosionSoundEffect.play();
			        }
		        });
		        

		        // Removes dead items from the screen
		        removeDeadBullets(bullets);
		        removeDeadEnemyBullets(enemyBullets);
		        removeDeadAsteroids(largeAsteroids);
		        removeDeadAsteroids(medAsteroids);
		        removeDeadAsteroids(smallAsteroids);
		        removeDeadEnemies(enemyShips);
		        
		        // Checks if all asteroids have been destroyed
		        // If this is the case, the player will move onto the next level
		        if (smallAsteroids.size() == 0 && medAsteroids.size() == 0 && largeAsteroids.size() == 0 && enemyShips.size() == 0) {
		        	try {
						switchToLevelScreen();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	
		        	// Stops the animation timer
		        	stop();
		        }
		        
		        // Checks if the player ship has been destroyed
		        if (playerShip.getLives() <= 0) {
		        	try {
		        		switchToGameOverScreen();
		        	} catch (IOException e) {
		        		e.printStackTrace();
		        	}
		        	
		        	stop();
		        }
			}
			
		}.start();
		
		// Displays the game screen
		stage.show();
		
		// Request focus on the player ship, so that it will react to key events
		playerShip.getCharacter().requestFocus();
	}
	
	
	// Method to spawn the player ship in a safe location
	public void spawnPlayerShip(Ship playerShip, List<Character> enemyCharacters) {
		double safeSpaceX;
		double safeSpaceY;
		
		// Spawn the player ship and ensure that the player does not collide with asteroids upon spawning
		do {
			
			// Try spawn the player in a random location on the screen and set safe spawn = true
			safeSpaceX = Math.random() * (SceneController.SCREENWIDTH - 400.0) + 200.0;
			safeSpaceY = Math.random() * (SceneController.SCREENHEIGHT - 200.00) + 100.0;
			playerShip.hyperspace(safeSpaceX, safeSpaceY);
			playerShip.setSafelySpawned(true);
			
			// Loop through all asteroids, and if there is a collision, set safe spawn = false
			enemyCharacters.forEach(enemy -> {
				if (playerShip.collide(enemy)) {
					playerShip.setSafelySpawned(false);
				}
				
			});
		}
		while(!playerShip.isSafelySpawned());
		
	}
	
	// Method for spawning two new asteroids when an asteroid has been hit
	public void spawnAsteroids(Asteroid asteroid, String size, List<Asteroid> asteroidArray) {
		// Spawn two new asteroids
    	for (int i = 0; i < 2; i++) {
		    Asteroid newAsteroid = new Asteroid(asteroid.getCharacter().getTranslateX(), asteroid.getCharacter().getTranslateY(), size);
		    asteroidArray.add(newAsteroid);
    		root.getChildren().add(newAsteroid.getCharacter());
		} 
	}
	
	// Method to remove all dead bullets from the screen
	// Dead bullets are ones that have hit an asteroid
	public void removeDeadBullets(List<Bullet> bullets) {
		// Isolates bullets that have hit an asteroid
		bullets.stream()
			.filter(bullet -> !bullet.isAlive())
        	.forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
		
		// Removes bullets that have hit an asteroid
		bullets.removeAll(bullets.stream()
			.filter(bullet -> !bullet.isAlive())
			.collect(Collectors.toList()));
		
		// Removes bullets that have travelled too far
        bullets.stream()
        	.filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
        	.forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
        
        // Removes bullets that have travelled too far
        bullets.removeAll(bullets.stream()
	        .filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
	        .collect(Collectors.toList()));
	}
	
	public void removeDeadEnemyBullets(List<EnemyBullet> bullets) {
		// Isolates bullets that have hit an asteroid
		bullets.stream()
			.filter(bullet -> !bullet.isAlive())
        	.forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
		
		// Removes bullets that have hit an asteroid
		bullets.removeAll(bullets.stream()
			.filter(bullet -> !bullet.isAlive())
			.collect(Collectors.toList()));
		
		// Removes bullets that have travelled too far
        bullets.stream()
        	.filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
        	.forEach(bullet -> root.getChildren().remove(bullet.getCharacter()));
        
        // Removes bullets that have travelled too far
        bullets.removeAll(bullets.stream()
	        .filter(bullet -> bullet.getDistanceTravelled() > bullet.getMaxDistance())
	        .collect(Collectors.toList()));
	}
	
	
	// Method to remove all dead asteroids from the screen
	public void removeDeadAsteroids(List<Asteroid> asteroids) {
		asteroids.stream()
		        .filter(asteroid -> !asteroid.isAlive())
		        .forEach(asteroid -> root.getChildren().remove(asteroid.getCharacter()));
		asteroids.removeAll(asteroids.stream()
                .filter(asteroid -> !asteroid.isAlive())
                .collect(Collectors.toList()));
	}
	
	public void removeDeadEnemies(List<EnemyShip> enemies) {
		enemies.stream()
	        .filter(enemy -> !enemy.isAlive())
	        .forEach(enemy -> root.getChildren().remove(enemy.getCharacter()));
		enemies.removeAll(enemies.stream()
		    .filter(enemy -> !enemy.isAlive())
		    .collect(Collectors.toList()));
	}
	
	public void switchToGameOverScreen() throws IOException {
		// Generates the game over screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Adds styling to the screen
		scene.getStylesheets().add(css);
		
		// Adds the screen to the window and show the window
		stage.setScene(scene);
		
		// Add game over text
		Text gameOver = new Text("Game Over");
		gameOver.setTranslateX(100);
		gameOver.setTranslateY(300);
		gameOver.setFont(Font.font("Verdana", 100));
		gameOver.setFill(Color.WHITE);
		root.getChildren().add(gameOver);
		
		// Adds press enter to continue text
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
					switchToEnterNameScreen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void switchToEnterNameScreen() throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the welcome screen
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
		
		
		// Add a key event which switches to the view high scores screen when the user presses enter
		stage.getScene().setOnKeyPressed(e -> {
			e.consume();
			if (e.getCode() == KeyCode.ENTER) {
				try {
					// Extract the user's input and use it to create a score object
					String username = userInput.getText();
					if (validUserInput(username)) {
						switchToViewHighScoreScreen(username);
					} else {
						switchToEnterNameScreen();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public Boolean validUserInput(String username) {
		Alert invalidInputAlert = new Alert(AlertType.ERROR);
		
		// Check if the user input is empty
		if (username.length() == 0) {
			invalidInputAlert.setContentText("Username cannot be empty");
			invalidInputAlert.show();
			return false;
		}
		
		// Check if the user input is greater than three letters
		else if (username.length() > 3) {
			invalidInputAlert.setContentText("Username must be less than or equal to 3 letters");
			invalidInputAlert.show();
			return false;
		}
		
		// Check that there are no commas in the username
		else {
			for (int i = 0; i < username.length(); i++) {
				if (username.charAt(i) == ',') {
					invalidInputAlert.setContentText("Username cannot contain a comma");
					invalidInputAlert.show();
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void switchToViewHighScoreScreen(String username) throws IOException {
		// Generate the welcome screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		// Add the welcome screen to the window and show the window
		stage.setScene(scene);
		
		// Add styling to the welcome screen
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
		Score newScore = new Score(username, score.intValue());
		
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
					switchToWelcomeScreen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	// Method for switching to the level screen which shows the user which level they are currently on and prompts them to hit enter to begin the next level
	public void switchToLevelScreen() throws IOException {
		// Generate the screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the screen
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
					switchToGameScreen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
