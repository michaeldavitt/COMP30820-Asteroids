package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {

	private Polygon character;
	private Point2D movement;

	// Ship constructor
	public Character(Polygon polygon, double xLocation, double yLocation) {
		this.character = polygon;
		this.character.setTranslateX(xLocation);
		this.character.setTranslateY(yLocation);
		this.character.setFill(Color.WHITE);
		
		this.movement = new Point2D(0, 0);
	}
	
	// Get the character (polygon)
	public Polygon getCharacter() {
		return character;
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
	    this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
	    this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

	    if (this.character.getTranslateX() < 0) {
	        this.character.setTranslateX(this.character.getTranslateX() + SceneController.SCREENWIDTH);
	    }

	    if (this.character.getTranslateX() > SceneController.SCREENWIDTH) {
	        this.character.setTranslateX(this.character.getTranslateX() % SceneController.SCREENWIDTH);
	    }

	    if (this.character.getTranslateY() < 0) {
	        this.character.setTranslateY(this.character.getTranslateY() + SceneController.SCREENHEIGHT);
	    }

	    if (this.character.getTranslateY() > SceneController.SCREENHEIGHT) {
	        this.character.setTranslateY(this.character.getTranslateY() % SceneController.SCREENHEIGHT);
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
}
