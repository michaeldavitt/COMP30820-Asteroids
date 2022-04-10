package application;

import java.util.Random;

public class Asteroid extends Character {

    private double rotationalMovement;
    private Size size;
    private double scaler;
    private double accelerationAmount;

    public Asteroid(double x, double y, Size size) {
        super(new PolygonGenerator().createPolygon(), x, y);
        
        this.size = size;
        
        // Sets the size of the asteroid
        setSize();
        
        // Generates a random number for the direction, speed, and rotational movement
        Random rnd = new Random();
        
        // Sets a random direction of the asteroid
        setDirection(rnd);
        
        // Sets the speed of the asteroid
        setSpeed(rnd);
        
        // Sets the rotational movement of the asteroid
        setRotationalMovement(rnd);
    }
    
    public Size getSize() {
    	return this.size;
    }
    
    private final void setSize() {
    	
    	// Checks the size of the asteroid to determine the scaling factor that should be applied to the size
    	if (this.size == Size.LARGE) {
    		this.scaler = 1.0;
    	} else if (this.size == Size.MEDIUM) {
    		this.scaler = 0.5;
    	} else if (this.size == Size.SMALL) {
    		this.scaler = 0.2;
    	} else {
    		this.scaler = 0;
    	}
    	
    	// Loops through each point in the asteroid and adjusts it according to the scaling factor
    	for (int i = 0; i < this.getCharacter().getPoints().size(); i++) {
            this.getCharacter().getPoints().set(i, this.getCharacter().getPoints().get(i) * scaler);
        }
    }
    
    private void setSpeed(Random rnd) {
    	// Change the speed of the asteroid depending on its size
    	if (this.size == Size.LARGE) {
    		this.accelerationAmount = 20.0 + rnd.nextDouble() * 10.0;
    	} else if (this.size == Size.MEDIUM) {
    		this.accelerationAmount = 45.0 + rnd.nextDouble() * 10.0;
    	} else if (this.size == Size.SMALL) {
    		this.accelerationAmount = 60.0 + rnd.nextDouble() * 5.0;
    	} else {
    		this.accelerationAmount = 0.0;
    	}
        
        for (double i = 0; i < this.accelerationAmount; i++) {
            accelerate();
        }
    }
    
    private final void setDirection(Random rnd) {
    	// Sets a random rotation speed and direction for the asteroid
        super.getCharacter().setRotate(rnd.nextInt(360));
    }
    
    private final void setRotationalMovement(Random rnd) {
    	this.rotationalMovement = 1.0 - rnd.nextDouble();
    }

    
    @Override
    public void move() {
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotationalMovement);
    }
}
