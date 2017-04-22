package triviaGame;

import java.util.Scanner;

import javax.swing.JTextField;
public class Player{

private String playerName; // The player number
private int scores; // Player's points
private int currentAnswer; // Current chosen answer
private String category;


	// Constructor
	public Player(String string, int scores, String category){
		this.playerName = string;
		this.scores = scores;
		this.category = category;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getScores() {
		return scores;
	}
	
	

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return String.format(",Player %s, Score %d, Category %s", getPlayerName(), getScores(), getCategory());
	}

	

	
}
