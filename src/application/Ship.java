package application;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

	private int lives;
	private Boolean safelySpawned;
	
    public Ship(int x, int y) {
        super(new Polygon(-20, 0, 10, 10, 10, -10), x, y);
        this.lives = 3;
        this.safelySpawned = true;
    }
    
    // Method to get the number of lives that the ship has left
    public int getLives() {
    	return this.lives;
    }
    
    // Method to decrease the number of lives the ship has
    public void decrementLives() {
    	this.lives -= 1;
    }
    
    // Method to set if the player has safely spawned or not
    public Boolean isSafelySpawned() {
    	return this.safelySpawned;
    }
    
    // Method to set the safely spawned status
    public void setSafelySpawned(Boolean safelySpawned) {
    	this.safelySpawned = safelySpawned;
    }
}
