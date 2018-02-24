/*
@author: Aisling O'Mahony x17122368

About: The LottoApp Class facilitates the running of the Lottory Game
by accepting player input and bringing together the required instances of
the LottoPlayer and LottoGame classes.
*/

import java.util.Arrays;
import java.util.Scanner;

public class LottoApp{

	public static void main(String args[]){

		//declare variables & objects
		int max = 3;
		int numLines;
		//declare instances of Scanner and LottoPlayer
		Scanner in = new Scanner(System.in);
		LottoPlayer player = new LottoPlayer();
		//variables assigned to store response from user when prompted to play again
		String yes = "y";
		boolean playAgain = true;

		//this section of code will run while a game is in progress
		while(playAgain) {
			//Ask player for number of lines
			System.out.println("How many lines would you like to play? (max. lines = 3)");
			numLines = in.nextInt();
				/*check for valid no. of lines (between 1-3).
				If not valid, player is prompted to re-enter.*/
			for (int i = 0; i != 1; ) {
				if (numLines > max || numLines <= 0) {
					System.out.println("Invalid entry, please re-enter: (max. lines = 3)");
					numLines = in.nextInt();
				}
				//exits the for loop
				else {
					break;
				}
			}

			//create array to pass player's input to the LottoGame class
			player.setPlayerNums(new int[numLines][6]);
			int[][] playerNums = player.getPlayerNums();
			for (int i = 0; i < numLines; i++) {
				for (int j = 0; j < playerNums[i].length; j++) {
					boolean isntValid = true;
					while (isntValid) {
						//Prompt user for number entry
						System.out.println("Enter value: line " + (i + 1) + " number " + (j + 1));

						//check that numbers are valid - (between 1-40) and no duplicate entries in same line
						int valid = in.nextInt();
						boolean check = noRepeat(valid, playerNums[i]);
						if (valid <= 40 && valid > 0 && check) {
							isntValid = false;
							playerNums[i][j] = valid;
						} else
							System.out.println("Invalid entry, please try again (number must be 1-40): ");
					}
				}
			}
			LottoGame game = new LottoGame(playerNums);

			//get results from LottoGame class
			int matched[] = game.getMatchedNums();
			int winnings[] = game.getWinnings();
			int totalWinnings = 0;
			int numLinesMatched = 0;
			//display results of numbers matched in each line and the corresponding winnings
			for (int i = 0; i < playerNums.length; i++) {
				System.out.println("Line " + (i + 1) + ": " + matched[i] + " matched. Winnings = " + winnings[i]);
				totalWinnings += winnings[i];
				if (matched[i] > 2) {
					numLinesMatched++;
				}
					/*if the player's 6 numbers are matched to
					the random lottory numbers display congrats message*/

				if (winnings[i] == -1) {
					System.out.println("Congratulations! You have won the lottery!");
				}
			}
			//display the randomly generated lotto numbers to the player
			int lotto[] = game.getLottoNums();
			System.out.println("Lotto numbers were" + Arrays.toString(lotto));

			//ask if player wants to play again & use input to decide whether new game begins
			System.out.println("Would you like to play again? y / n...");
			String play = new Scanner(System.in).next();

			if (!play.contains(yes)) {
				playAgain = false;
			}

			//send player input to LottoPlayer class
			player.addGameToHistory(numLines, numLinesMatched, totalWinnings);
		}
		//print game history
		player.getHistory();

	}

	//checks for repetition in player's entered numbers
	private static boolean noRepeat(int nextNum, int[] lottoNums){
	        for(int i : lottoNums){
	            if(i == nextNum){
	                return false;
	            }
	        }
	        return true;
    }

}