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
public class ScoresList {

	private Score[] scoresList;
	private int numScores;
	
	public ScoresList() {
		try {
			// Open text file containing all scores and get the number of lines in the file
			File myFile = openTextFile("src/application/scores.txt");
			Scanner reader = new Scanner(myFile);
			numScores = getNumberOfLines(reader);		
			
			// Close and reopen the file
			reader.close();
			myFile = openTextFile("src/application/scores.txt");
			reader = new Scanner(myFile);
			
			// Define a scoresList array to hold the scores
			// Set the size of the scores array = number of scores + 1 to allow for the new user's score
			scoresList = new Score[numScores + 1];
			
			// Put scores into an array
			for (int i = 0; i < numScores; i++) {
				String data = reader.nextLine();
				String name = data.split(",")[0];
				int score = Integer.parseInt(data.split(",")[1]);
				Score scoreAndName = new Score(name, score);
				this.scoresList[i] = scoreAndName;
			}
			
			// Close file reader
			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred");
			e.printStackTrace();
		}
	}
	
	// Method to open a text file
	private File openTextFile(String fileName) {
		return new File(fileName);
	}
	
	// Method to get the number of lines in a text file
	private int getNumberOfLines(Scanner reader) {
		int counter = 0;
		while (reader.hasNextLine()) {
			reader.nextLine();
			counter++;
		}
		return counter;
	}
	
	public void updateHighScore(Score newScore) {
		// Add new score to the high scores list
		this.scoresList[this.numScores] = newScore;
		
		// Sort the scores list
		Arrays.sort(scoresList, new Comparator<Score>() {
			public int compare(Score s1, Score s2) {
				return Integer.compare(s2.getScore(), s1.getScore());
			}
		});
		
		// Add the new score to a text file
		try {
			this.writeScoreToTextFile(newScore);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Method for writing a new high score to the text file containing all scores
	private void writeScoreToTextFile(Score newScore)
		throws IOException {
		    FileWriter fw = new FileWriter("src/application/scores.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.newLine();
		    bw.write(newScore.toString());
		    bw.close();
		}
	
	// Method to get high scores list 
	public Score[] getHighScores() {
		int numScoresToDisplay = (this.scoresList.length > 5) ? 5: this.scoresList.length;
		return Arrays.copyOfRange(this.scoresList, 0, numScoresToDisplay);
	}
}
