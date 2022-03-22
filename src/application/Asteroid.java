package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroid extends Polygon {

	public double xLocation = 100.0;
	public double yLocation = 100.0;
	
	public Asteroid() {
		drawAsteroid();
		this.setFill(Color.WHITE);
	}
	
	public void drawAsteroid() {
		this.getPoints().setAll(
				this.xLocation - 50, this.yLocation,
				this.xLocation - 40, this.yLocation - 30,
				this.xLocation - 30, this.yLocation - 40,
				this.xLocation - 10, this.yLocation - 20,
				this.xLocation, this.yLocation - 50,
				this.xLocation + 30, this.yLocation - 40,
				this.xLocation + 40, this.yLocation - 30,
				this.xLocation + 50, this.yLocation,
				this.xLocation + 20, this.yLocation + 10,
				this.xLocation + 40, this.yLocation + 30,
				this.xLocation + 30, this.yLocation + 40,
				this.xLocation, this.yLocation + 50,
				this.xLocation - 30, this.yLocation + 40,
				this.xLocation - 40, this.yLocation + 30,
				this.xLocation - 25, this.yLocation + 25
				);
		
	}
}
