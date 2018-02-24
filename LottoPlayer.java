
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LottoPlayer {
    private int[][] playerNums;
	private List<int[]> gameHistory;

	public LottoPlayer(){
		gameHistory = new ArrayList();
    }

	public void addGameToHistory(int linesPlayed, int linesWon, int winnings) {
		gameHistory.add(new int[]{linesPlayed, linesWon, winnings});
	}

	public void getHistory() {
		for(int i = 0; i < gameHistory.size(); ++i) {
			int winnings = gameHistory.get(i)[2];
			System.out.println("\n- - - - - - - - - - - - - - - - - -\nFor game " + (i + 1) + " your history is...." +
					"\nLines played:\t" + gameHistory.get(i)[0] +
					"\nLines won:\t\t" + gameHistory.get(i)[1] +
					"\nWinnings:\t\t" + checkForJackpot(winnings));
		}
	}

	private String checkForJackpot(int winnings) {
		if(winnings == -1) return "Jackpot!";
		return "" + winnings;
	}

	public void setPlayerNums(int[][] playerNums){
		this.playerNums = playerNums;
	}

	public int[][] getPlayerNums() {
		return playerNums;
	}
}
