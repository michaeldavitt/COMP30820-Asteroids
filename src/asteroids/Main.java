package asteroids;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {
    public static void main(String[] args) {
        // First step, display a welcome screen to the user
        Screen screen = new Screen(); // Creates generic screen to hold our game

        // Creates specific screen where we will display a welcome message to the user
        ContentScreen welcomeScreen = new ContentScreen();

        JLabel gameTitle = new JLabel("Welcome to Asteroids", SwingConstants.CENTER); // Creates title label
        JLabel pressEnter = new JLabel("Press Enter to Start", SwingConstants.CENTER); // Creates instruction
        gameTitle.setForeground(Color.WHITE);
        pressEnter.setForeground(Color.WHITE);

        welcomeScreen.add(gameTitle, BorderLayout.NORTH); // Adds the title to the screen
        welcomeScreen.add(pressEnter, BorderLayout.CENTER); // Adds press enter instruction to screen
        screen.add(welcomeScreen, BorderLayout.CENTER); // Adds the game screen to the basic screen

        screen.setVisible(true); // Makes the screen visible
    }
}
