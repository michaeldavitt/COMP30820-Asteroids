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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


// Class representing the application in general
// In this class, we control the content that is displayed on the screen
public class GameController {
	// Initialise scene controller variables
	// These variables all relate to the game screen (the window itself, the content in the window, size of the window etc.)
	// Current level is also initialised to be 0, and this will be reset in each iteration of the game
	private final Stage stage;
	private Scene scene;
	private Pane root;
	private final String css;
	public static final int SCREENWIDTH = 800;
	public static final int SCREENHEIGHT = 600;
	private int currentLevel = 0;
	private AudioClip explosionSoundEffect;
	private PlayerShip playerShip;
	
	
	// Constructor for the scene controller
	// Here, we assign the game window to the stage variable and define the default css styles
	// We also call the switch to welcome screen method, which generates the welcome screen for the user
	public GameController(Stage stage) {
		this.stage = stage;
		this.css = this.getClass().getResource("application.css").toExternalForm();
		
		try {
			switchToWelcomeScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Method that displays the welcome screen to the user
	// This is the first screen that the user will see when they start the game
	// When the user dies and wants to play again, they will return to this screen before starting
	public void switchToWelcomeScreen() throws IOException {
		// Resets current level to 0 before the user starts playing
		currentLevel = 0;
		
		// Creates new player ship and resets score to 0
		playerShip = new PlayerShip(SCREENWIDTH / 2, SCREENHEIGHT / 2);
		
		// Clears the screen and generates a new screen
		clearScreen();
		
		// Add title to welcome users to the game
		GameText welcomeText = new GameText("Welcome To Asteroids!", 170, 200, 36);
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
		
		
		stage.show();
	}
	
	
	// Method that displays the controls screen to the user
	// Users can view the buttons they need to press to interact with the game on this screen
	public void switchToControlsScreen() throws IOException {
		
		clearScreen();
		
		// Adds a title to the game controls screen
		GameText controlsText = new GameText("Game Controls", 250, 100, 36);
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

	}
	
	
	// Method to display the main game screen, where the user will play the game
	public void switchToGameScreen() throws IOException {
		clearScreen();
		
		// Adds the current level to the screen
		GameText currentLevelText = new GameText("Level: " + currentLevel, 10, 20, 24);
	    root.getChildren().add(currentLevelText);
	    
		// Adds the player's score to the screen
		GameText pointsTally = new GameText("Points: " + playerShip.getScore(), 10, 40, 24);
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
		List<PlayerBullet> bullets = new ArrayList<>();
		List<EnemyBullet> enemyBullets = new ArrayList<>();
		
		// Creates an array to store all of the enemies
		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
		
		// Spawn the playerShip
		spawnPlayerShip(playerShip, enemyCharacters);
		root.getChildren().add(playerShip.getCharacter());
		
		// Adds the player's health to the screen
	    GameText playerHealthTally = new GameText("Lives Remaining: " + playerShip.getLives(), 10, 60, 24);
	    root.getChildren().add(playerHealthTally);
		
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
			    	List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
					
					// Spawn the player ship and ensure that the player does not collide with asteroids upon spawning
					spawnPlayerShip(playerShip, enemyCharacters);
			    	
			    	// Resets last hyper space jump variable
			    	lastHyperSpaceJump = now;
			    }
			    
				
				// Fire a bullet when the user presses SPACE
				if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && now - lastBulletUpdate >= 280_000_000) {
				    // we shoot
				    PlayerBullet bullet = new PlayerBullet((int) playerShip.getCharacter().getTranslateX(), (int) playerShip.getCharacter().getTranslateY());
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
				        	playerShip.increaseScore(500);
				        	pointsTally.setText("Points: " + playerShip.getScore());
				        	
				        	explosionSoundEffect = new AudioClip(getClass().getResource("explosion.mp3").toExternalForm());
		                    explosionSoundEffect.play();
				        }
		        	});
			        
			        	
		        	// Collision with large asteroids
		            largeAsteroids.forEach(asteroid -> {
		                if(bullet.collide(asteroid)) {
		                	
		                	// Change alive status of bullet and hit asteroid
		                    bullet.setAlive(false);
		                    playerShip.increaseScore(100);
		                    pointsTally.setText("Points: " + playerShip.getScore());
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
		                    playerShip.increaseScore(200);
		                    pointsTally.setText("Points: " + playerShip.getScore());
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
		                    playerShip.increaseScore(300);
		                    pointsTally.setText("Points: " + playerShip.getScore());
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
		        		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
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
		        		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
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
		        		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
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
		        		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
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
		        		List<Character> enemyCharacters =  getEnemyCharacters(largeAsteroids, medAsteroids, smallAsteroids, enemyShips);
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
		
		// Request focus on the player ship, so that it will react to key events
		playerShip.getCharacter().requestFocus();
	}
	
	
	// Method for getting a list of the enemy characters
	public List<Character> getEnemyCharacters(List<Asteroid> largeAsteroids, List<Asteroid> medAsteroids, List<Asteroid> smallAsteroids, List<EnemyShip> enemyShips) {
		List<Character> enemyCharacters = new ArrayList<>();	
		enemyCharacters.addAll(largeAsteroids);
		enemyCharacters.addAll(medAsteroids);
		enemyCharacters.addAll(smallAsteroids);	
		enemyCharacters.addAll(enemyShips);
		
		return enemyCharacters;
	}
	
	
	// Method to spawn the player ship in a safe location
	public void spawnPlayerShip(PlayerShip playerShip, List<Character> enemyCharacters) {
		double safeSpaceX;
		double safeSpaceY;
		
		// Spawn the player ship and ensure that the player does not collide with asteroids upon spawning
		do {
			
			// Try spawn the player in a random location on the screen and set safe spawn = true
			safeSpaceX = Math.random() * (GameController.SCREENWIDTH - 400.0) + 200.0;
			safeSpaceY = Math.random() * (GameController.SCREENHEIGHT - 200.00) + 100.0;
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
	public void removeDeadBullets(List<PlayerBullet> bullets) {
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
		clearScreen();
		
		// Add game over text
		GameText gameOver = new GameText("Game Over", 100, 300, 100);
		root.getChildren().add(gameOver);
		
		// Adds press enter to continue text
		GameText continueText = new GameText("Press Enter to continue", 230, 400, 20);
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
		clearScreen();
		
		// Add text prompting the user to enter a username
		GameText enterNameText = new GameText("Enter Username Below", 100, 200, 50);
		root.getChildren().add(enterNameText);
		
		// Add textfield where the user can type in their username
		TextField userInput = new TextField();
		userInput.setTranslateX(300);
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
						switchToViewHighScoreScreen(username.toUpperCase());
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
		clearScreen();
		
		// Add high score title
		GameText highScoresText = new GameText("High Scores", 150, 150, 80);
		root.getChildren().add(highScoresText);
		
		
		// Add high scores to the screen
		// Create scores object and new user score
		ScoresList scores = new ScoresList();
		Score newScore = new Score(username, playerShip.getScore().intValue());
		
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
		GameText continueText = new GameText("Press Enter to continue", 250, 550, 20);
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
		clearScreen();
		
		// Update level counter
		currentLevel += 1;
		
		// Add text representing the user's current level
		GameText levelText = new GameText("Level " + currentLevel, 200, 250, 80);
		root.getChildren().add(levelText);
		
		
		// Add text prompting the user to hit enter to begin next level
		GameText nextLevelText = new GameText("Press Enter to continue", 200, 400, 24);
		root.getChildren().add(nextLevelText);
		
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
	
	public void clearScreen() {
		// Generate the screen
		root = new Pane();
		root.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
		scene = new Scene(root);
		
		// Add styling to the screen
		scene.getStylesheets().add(css);
		
		// Display screen
		stage.setScene(scene);
	}
}
