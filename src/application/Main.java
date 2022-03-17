package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Polygon;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;


//Reference: https://www.youtube.com/watch?v=9XJicRt_FaI
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create the welcome screen
		Parent root = FXMLLoader.load(getClass().getResource("Welcome_Screen.fxml"));
		Scene scene = new Scene(root);
		
		// Style the welcome screen
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		// Put welcome screen onto the main screen
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
}
