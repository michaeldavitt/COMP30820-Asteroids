package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship {

	private Polygon character;
	private Point2D movement;

	// Ship constructor
	public Ship(double xLocation, double yLocation) {
		this.character = new Polygon();
		drawShip();
		this.character.setTranslateX(xLocation);
		this.character.setTranslateY(yLocation);
		this.character.setRotate(0);
		this.character.setFill(Color.WHITE);
		
		this.movement = new Point2D(0, 0);
	}
	
	// Draw ship onto the screen
	public void drawShip() {
		this.character.getPoints().setAll(
			-20.0, 0.0,
			10.0, 10.0,
			10.0, -10.0
		);
	}
	
	// Get the ship's character (polygon)
	public Polygon getCharacter() {
		return character;
	}
	
	// Rotate the ship to the right
	public void rotateRight(int delta) {
		this.character.setRotate(this.character.getRotate() + delta);
	}
	
	// Rotate the ship to the left
	public void rotateLeft(int delta) {
		this.character.setRotate(this.character.getRotate() - delta);
	}
	
	// Moves the ship
	public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
    }
	
	// Accelerate the ship
	public void accelerate() {
	    double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
	    double changeY = Math.sin(Math.toRadians(this.character.getRotate()));

	    changeX *= -0.05;
	    changeY *= -0.05;

	    this.movement = this.movement.add(changeX, changeY);
	}
	
}
