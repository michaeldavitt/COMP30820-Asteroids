package application;

public class Score {

	private String name = null;
	private int score = 0;
	
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return this.getName() + "," + this.getScore();
	}
}
