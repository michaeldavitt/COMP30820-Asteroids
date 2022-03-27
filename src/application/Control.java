package application;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Control extends Label {

	public Control(String controlDescription) {
		super(controlDescription);
		this.setMaxWidth(700);
		this.setWrapText(true);
		this.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
	}
}
