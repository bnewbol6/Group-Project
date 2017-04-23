package triviaGame;

public class Player implements Comparable<Player>{

private String playerName; // The player number
public int scores; // Player's points
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
		return String.format("%s,%d,%s", getPlayerName(), getScores(), getCategory());
	}

	@Override
	public int compareTo(Player player) {          
	    return (this.getScores() < player.getScores() ? 1 : 
	            (this.getScores() == player.getScores() ? 0 : -1));     
	  }  

	
}
