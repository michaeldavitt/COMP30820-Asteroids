package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.shape.Polygon;

public class PlayerShip extends Ship {

	private int lives;
	private AtomicInteger score;
	private Boolean safelySpawned;
	
    public PlayerShip(int x, int y) {
        super(new Polygon(-20, 0, 10, 10, 10, -10), x, y);
        this.setLives(3);
        this.setSafelySpawned(true);
        this.score = new AtomicInteger();
    }
    
    // Method to get the number of lives that the ship has left
    public int getLives() {
    	return this.lives;
    }
    
    // Method to set the number of lives that the ship starts with
    // Private so that this can only be done within the ship class
    private final void setLives(int lives) {
    	this.lives = lives;
    }
    
    // Method to decrease the number of lives the ship has
    public void decrementLives() {
    	this.lives -= 1;
    }
    
    // Method to get the player's current score
    public AtomicInteger getScore() {
    	return this.score;
    }
    
    // Method to increase the player's score
    public void increaseScore(int scoreIncrease) {
    	this.score.addAndGet(scoreIncrease);
    }
    
    // Method to set if the player has safely spawned or not
    public Boolean isSafelySpawned() {
    	return this.safelySpawned;
    }
    
    // Method to set the safely spawned status
    public final void setSafelySpawned(Boolean safelySpawned) {
    	this.safelySpawned = safelySpawned;
    }
    
    // Method for performing a hyperspace jump
	public void hyperspace(double hyperSpaceX, double hyperSpaceY) {
		this.getCharacter().setTranslateX(hyperSpaceX);
		this.getCharacter().setTranslateY(hyperSpaceY);
	}
}
