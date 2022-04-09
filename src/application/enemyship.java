package application;

import javafx.scene.shape.Polygon;

public class EnemyShip extends Character {

    public EnemyShip(int x, int y) {
        super(new Polygon(-60, 0, -40, 20, 40, 20, 60, 0, 40, -20,30,-20, 20, -35, -20, -35,-30,-20,-40,-20), x, y);
    }

}
