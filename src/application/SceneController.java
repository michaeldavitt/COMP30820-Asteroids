package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToGameScreen(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Game_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToWelcomeScreen(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Welcome_Screen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		stage.setScene(scene);
		stage.show();
	}
}
