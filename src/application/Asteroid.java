package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Asteroid extends Polygon {
	//	Double[] polygonPoints = {10.0,50.0,25.0,40.0,50.0,50.0,70.0,70.0,50.0,100.0,10.0,100.0,20.0,70.0};
	//	
	//	public Asteroid() {
	//	this.getPoints().setAll(polygonPoints);
	//	this.setFill(Color.WHITE);
	//	
	//}
}

	
class AsteroidMedium extends Asteroid{

	private double xLocation;
	private double yLocation;
	
	public AsteroidMedium (double xLocation, double yLocation) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		asteroidDraw();
		this.setFill(Color.WHITE);
	}
	
	public void asteroidDraw() {
		this.getPoints().setAll(
			this.xLocation +10, this.yLocation,
			this.xLocation + 100, this.yLocation + 10,
			this.xLocation + 100, this.yLocation - 40,
			this.xLocation + 10, this.yLocation - 40
			
		);
	}
	
	public double getXLocation() {
		return this.xLocation;
	}
	
	
	public double getYLocation() {
		return this.yLocation;
	}

}



class AsteroidSmall extends Asteroid{
	
//	public AsteroidSmall(){
//	List<Double> small = new ArrayList<Double>();
//	for(int i = 0; i < polygonPoints.length; i++ ) {
//		small.add(polygonPoints[i]*.5);}
//	
//	this.getPoints().setAll(small);
//	this.setFill(Color.WHITE);
//	}






}
