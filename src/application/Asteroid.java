package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroid extends Polygon {

	public Asteroid() {
		this.getPoints().setAll(
			100.0, 100.0,
			120.0, 100.0,
			140.0, 120.0,
			140.0, 140.0,
			120.0, 160.0,
			100.0, 160.0,
			80.0, 140.0,
			80.0, 120.0
		);
		this.setFill(Color.WHITE);
	}
}
