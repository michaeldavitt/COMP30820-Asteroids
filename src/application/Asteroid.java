package application;

import java.util.Random;

public class Asteroid extends Character {

    private double rotationalMovement;
    private String size;
    private double scaler;
    private double accelerationAmount;

    public Asteroid(double x, double y, String size) {
        super(new PolygonGenerator().createPolygon(), x, y);
        
        this.size = size;
        
        // Sets the size of the asteroid
        setSize();
        
        // Generates a random number for the direction and rotational movement
        Random rnd = new Random();
        
        // Sets a random direction of the asteroid
        setDirection(rnd);
        
        // Sets the speed of the asteroid
        setSpeed(rnd);
        
        // Sets the rotational movement of the asteroid
        setRotationalMovement(rnd);
    }
    
    public String getSize() {
    	return this.size;
    }
    
    private void setSize() {
    	
    	// Checks the size of the asteroid to determine the scaling factor that should be applied to the size
    	if (this.size.equals("Large")) {
    		this.scaler = 1.0;
    	} else if (this.size.equals("Medium")) {
    		this.scaler = 0.5;
    	} else {
    		this.scaler = 0.2;
    	}
    	
    	// Loops through each point in the asteroid and adjusts it according to the scaling factor
    	for (int i = 0; i < this.getCharacter().getPoints().size(); i++) {
            this.getCharacter().getPoints().set(i, this.getCharacter().getPoints().get(i) * scaler);
        }
    }
    
    private void setSpeed(Random rnd) {
    	// Change the speed of the asteroid depending on its size
    	if (this.size.equals("Large")) {
    		this.accelerationAmount = 20.0 + rnd.nextDouble() * 10.0;
    	} else if (this.size.equals("Medium")) {
    		this.accelerationAmount = 45.0 + rnd.nextDouble() * 10.0;
    	} else {
    		this.accelerationAmount = 60.0 + rnd.nextDouble() * 5.0;
    	}
        
        for (double i = 0; i < this.accelerationAmount; i++) {
            accelerate();
        }
    }
    
    private void setDirection(Random rnd) {
    	// Sets a random rotation speed and direction for the asteroid
        super.getCharacter().setRotate(rnd.nextInt(360));
    }
    
    private void setRotationalMovement(Random rnd) {
    	this.rotationalMovement = 1.0 - rnd.nextDouble();
    }

    
    @Override
    public void move() {
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotationalMovement);
    }
}
