package application;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameButton extends Button {

	public GameButton(String text, int x, int y, int fontSize) {
		super(text);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setFont(Font.font("Courier New", FontWeight.BOLD, fontSize));
	}
}
