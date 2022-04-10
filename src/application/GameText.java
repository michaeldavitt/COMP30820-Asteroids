package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameText extends Text {

	public GameText(String text, int x, int y, int fontSize) {
		super(text);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setFill(Color.WHITE);
		this.setFont(Font.font("Courier New", FontWeight.BOLD, fontSize));
	}
}
