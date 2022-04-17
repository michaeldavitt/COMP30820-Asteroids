package application;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class EnemyShip extends Ship {

    public EnemyShip(int x, int y) {
        super(new Polygon(-60, 0, -40, 20, 40, 20, 60, 0, 40, -20, 30, -20, 20, -35, -20, -35, -30, -20, -40, -20), x, y);
    }
    
    
    @Override
	public void accelerate() {
		Random randomY = new Random();
		int enemyY = randomY.nextInt(3+3) - 3;

		int three = 3;
        int minusThree = -3;
		int enemyX = new Random().nextBoolean() ? three  : minusThree;
		
	    this.movement = new Point2D(enemyX,enemyY);
	}

}
