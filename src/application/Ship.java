package application;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(-20, 0, 10, 10, 10, -10), x, y);
    }
}
