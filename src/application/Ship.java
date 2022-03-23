package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Polygon {
	
	private double xLocation;
	private double yLocation;
	private int angle;

	// Ship constructor
	public Ship(double xLocation, double yLocation) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.angle = 0;
		drawShip();
		this.setFill(Color.WHITE);
	}
	
	// Draw ship onto the screen
	public void drawShip() {
		this.getPoints().setAll(
				this.xLocation - 20, this.yLocation,
				this.xLocation + 10, this.yLocation + 10,
				this.xLocation + 10, this.yLocation - 10
				);
	}
	
	// Getter for the x coordinate
	public double getXLocation() {
		return this.xLocation;
	}
	
	// Getter for the y coordinate
	public double getYLocation() {
		return this.yLocation;
	}
	
	// Getter for the ship's angle
	public int getAngle() {
		return this.angle;
	}
	
	// Rotate the ship to the right
	public void rotateRight() {
		this.setAngle(this.getAngle() + 5);
		this.setRotate(this.getAngle());
	}
	
	// Rotate the ship to the left
	public void rotateLeft() {
		this.setAngle(this.getAngle() - 5);
		this.setRotate(this.getAngle());
	}
	
	// Setter for the ships current rotation angle
	private void setAngle(int newAngle) {
		this.angle = newAngle;
	}
}
