package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


//Reference: https://www.youtube.com/watch?v=9XJicRt_FaI
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
//		Stage stage = new Stage();
		Pane root = new Pane();
		Scene scene = new Scene(root, Color.BLACK);
		
		Text text = new Text();
		text.setText("Welcome to Asteroids");
		text.relocate(700, 500);
		text.setFont(Font.font("Verdana", 50));
		text.setFill(Color.WHITE);
		
		
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		stage.setTitle("Asteroids");
		
		Polygon ship = new Polygon();
		ship.getPoints().setAll(200.0, 200.0, 300.0, 300.0, 200.0, 300.0);
		ship.setFill(Color.WHITE);
		stage.setFullScreen(true);
		
		root.getChildren().add(text);
		root.getChildren().add(ship);
		stage.setScene(scene);
		stage.show();
		
	}
}
