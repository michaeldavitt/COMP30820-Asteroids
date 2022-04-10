package application;

import javafx.scene.shape.Polygon;

public class Bullet extends Character {
	
	private static final int MAXDISTANCE = 400;

	public Bullet(int x, int y) {
		super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
	}
	
	public int getMaxDistance() {
    	return Bullet.MAXDISTANCE;
    }
}
