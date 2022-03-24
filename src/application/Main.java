package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Polygon;
//import javafx.scene.image.Image;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Polygon;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
import javafx.scene.layout.Pane;


//Reference: https://www.youtube.com/watch?v=9XJicRt_FaI
public class Main extends Application {
	private final int screenX = 800;
	private final int screenY = 600;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create the welcome screen
		Pane root = new Pane();
		Scene scene = new Scene(root);
		root.setPrefSize(screenX, screenY);
		
		// Style the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Add image to screen
		Image icon = new Image("asteroid.jpg");
		stage.getIcons().add(icon);
		
		// Adds buttons to the screen to switch scenes
		SceneController sceneController = new SceneController();
		Button launchGameButton = new Button("Begin Game");
		launchGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					sceneController.switchToLevelScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		launchGameButton.setTranslateX(screenX / 2);
		launchGameButton.setTranslateY(screenY / 2);
		root.getChildren().add(launchGameButton);
		
		Button viewControlsButton = new Button("Controls");
		viewControlsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					sceneController.switchToControlsScreen(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		viewControlsButton.setTranslateX(screenX / 2);
		viewControlsButton.setTranslateY(screenY / 4);
		root.getChildren().add(viewControlsButton);
		
		// Put welcome screen onto the main screen
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		stage.show();
		
	}
}
