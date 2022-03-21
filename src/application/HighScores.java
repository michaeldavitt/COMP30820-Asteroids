package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Class representing an array of high scores
public class HighScores {

	private Score[] highScoresList;
	private int numScoresToDisplay;
	
	public HighScores() {
		try {
			// Open text file and get the number of lines in the file
			File myFile = openTextFile("src/application/scores.txt");
			Scanner reader = new Scanner(myFile);
			int counter = getNumberOfLines(reader);
			
			// Get number of scores to be displayed (max = 5)
			this.numScoresToDisplay = (counter > 4) ? 5: counter;
			System.out.println(numScoresToDisplay);
			
			// Close and reopen file
			reader.close();
			myFile = openTextFile("src/application/scores.txt");
			reader = new Scanner(myFile);
			
			// Assign high scores to highScoreLists array
			highScoresList = new Score[numScoresToDisplay];
			
			// Put high scores into highScoresList array
			for (int i = 0; i < highScoresList.length; i++) {
				String data = reader.nextLine();
				String name = data.split(",")[0];
				int score = Integer.parseInt(data.split(",")[1]);
				Score scoreAndName = new Score(name, score);
				this.highScoresList[i] = scoreAndName;
			}
			
			// Sort the high scores list
			Arrays.sort(highScoresList, new Comparator<Score>() {
				public int compare(Score s1, Score s2) {
					return Integer.compare(s2.getScore(), s1.getScore());
				}
			});
			
			// Close file reader
			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred");
			e.printStackTrace();
		}
	}
	
	// Method to open a text file
	private static File openTextFile(String fileName) {
		return new File(fileName);
	}
	
	// Method to get the number of lines in a text file
	private static int getNumberOfLines(Scanner reader) {
		int counter = 0;
		while (reader.hasNextLine()) {
			reader.nextLine();
			counter++;
		}
		return counter;
	}
	
	// Method for writing a new high score to the text file containing all scores
	public void updateHighScore(Score newScore)
		throws IOException {
		    FileWriter fw = new FileWriter("src/application/scores.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.newLine();
		    bw.write(newScore.toString());
		    bw.close();
		}
	
	// Method to get high scores list 
	public Score[] getHighScores() {
		return this.highScoresList;
	}
	
	// Main method
	public static void main(String[] args) {
		// Create high score object
		HighScores highScores = new HighScores();
		Score newScore = new Score("HEM", 444);
		
		// Update high scores with new score
		try {
			highScores.updateHighScore(newScore);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get high scores
		for (int i = 0; i < highScores.getHighScores().length; i++) {
			System.out.println(highScores.getHighScores()[i].getName() + " - " + highScores.getHighScores()[i].getScore());
		}
	}
}
