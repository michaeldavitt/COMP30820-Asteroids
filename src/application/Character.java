package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {

	private Polygon character;
	private Point2D movement;
	private Boolean alive;
	private double distanceTravelled;

	// Ship constructor
	public Character(Polygon polygon, double xLocation, double yLocation) {
		this.character = polygon;
		this.setAlive(true);
		this.character.setTranslateX(xLocation);
		this.character.setTranslateY(yLocation);
		this.character.setFill(Color.WHITE);
		
		this.movement = new Point2D(0, 0);
	}
	
	// Get the character (polygon)
	public Polygon getCharacter() {
		return character;
	}
	
	// Get the character's movement
	public Point2D getMovement() {
		return this.movement;
	}
	
	// Sets the character's movement
	public void setMovement(Point2D newMovement) {
		this.movement = newMovement;
	}
	
	// Get distance travelled
	public double getDistanceTravelled() {
		return this.distanceTravelled;
	}
	
	// Set the distance travelled
	private void increaseDistanceTravelled(double distance) {
		this.distanceTravelled += distance;
	}
	
	// Set if the character is alive
	public void setAlive(Boolean status) {
		this.alive = status;
	}
	
	// Checks if the character is alive
	public Boolean isAlive() {
		return this.alive;
	}
	
	// Rotate the character to the right
	public void rotateRight(int delta) {
		this.character.setRotate(this.character.getRotate() + delta);
	}
	
	// Rotate the character to the left
	public void rotateLeft(int delta) {
		this.character.setRotate(this.character.getRotate() - delta);
	}
	
	// Moves the character
	public void move() {
		// Increases the distance travelled
		Point2D currentPosition = new Point2D(this.character.getTranslateX(), this.character.getTranslateY());
		this.increaseDistanceTravelled(currentPosition.distance(this.character.getTranslateX() + this.movement.getX(), this.character.getTranslateY() + this.movement.getY()));
		
		// Changes the new x and y coordinates for the character
	    this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
	    this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

	    // Ensures that the character stays within the screen
	    if (this.character.getTranslateX() < 0) {
	        this.character.setTranslateX(this.character.getTranslateX() + GameController.SCREENWIDTH);
	    }

	    if (this.character.getTranslateX() > GameController.SCREENWIDTH) {
	        this.character.setTranslateX(this.character.getTranslateX() % GameController.SCREENWIDTH);
	    }

	    if (this.character.getTranslateY() < 0) {
	        this.character.setTranslateY(this.character.getTranslateY() + GameController.SCREENHEIGHT);
	    }

	    if (this.character.getTranslateY() > GameController.SCREENHEIGHT) {
	        this.character.setTranslateY(this.character.getTranslateY() % GameController.SCREENHEIGHT);
	    }
	}
	
	// Accelerates the character
	public void accelerate() {
	    double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
	    double changeY = Math.sin(Math.toRadians(this.character.getRotate()));

	    changeX *= -0.05;
	    changeY *= -0.05;

	    this.movement = this.movement.add(changeX, changeY);
	}
	
	// Adds checker for collision between two characters
	public boolean collide(Character other) {
	    Shape collisionArea = Shape.intersect(this.character, other.getCharacter());
	    return collisionArea.getBoundsInLocal().getWidth() != -1;
	}
	
	public void accelerateSlow() {
//		int three = 3;
//        int minusThree = -3;
//		int accSlowChange = new Random().nextBoolean() ? three  : minusThree;

	    this.movement = new Point2D(3,0);
	}

	
	
}
