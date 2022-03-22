package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Polygon {
	
	public double xLocation = 200.0;
	public double yLocation = 200.0;

	public Ship() {
		drawShip();
		this.setFill(Color.WHITE);
	}
	
	public void drawShip() {
		this.getPoints().setAll(
				this.xLocation - 20, this.yLocation,
				this.xLocation + 10, this.yLocation + 10,
				this.xLocation + 10, this.yLocation - 10
				);
	}
}
