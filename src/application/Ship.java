package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Polygon {

	public Ship() {
		this.getPoints().setAll(
			200.0, 200.0,
			220.0, 220.0,
			200.0, 220.0
		);
		this.setFill(Color.WHITE);
	}
}
