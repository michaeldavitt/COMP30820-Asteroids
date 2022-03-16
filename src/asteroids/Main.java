package asteroids;

//Java Program to create a Pane
//and add labels and buttons to the pane
//and relocate them to specific positions
//and add it to the stage
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
//import javafx.scene.web.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


// Reference: https://www.youtube.com/watch?v=9XJicRt_FaI
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
//		Stage stage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root, Color.BLACK);
		
		Text text = new Text();
		text.setText("Welcome to Asteroids");
		text.setX(100);
		text.setY(50);
		text.setFont(Font.font("Verdana", 50));
		text.setFill(Color.WHITE);
		root.getChildren().add(text);
		
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		stage.setTitle("Asteroids");
		
		stage.setFullScreen(true);
		
		stage.setScene(scene);
		stage.show();
		
	}
}