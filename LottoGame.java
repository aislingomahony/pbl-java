
import java.util.Random;

public class LottoGame{

	private int[] lottoNums =new int[6];
	private int[][] playerNums;
	private int[] matchedPerLine;
	private int[] winningsPerLine;

	private int matchedNums;
	private int winnings = 0;

	private final int MATCH_3 = 3;
	private final int MATCH_4 = 4;
	private final int MATCH_5 = 5;
	private final int MATCH_6 = 6;
	private final int WIN_3 = 9;
	private final int WIN_4 = 54;
	private final int WIN_5 = 1000;
	private final int WIN_6 = -1;  //Used to trigger a message in app class

	//New game
	public LottoGame(int[][] values){
		generateNums();
		playerNums = values.clone();
		matchedPerLine = new int[playerNums.length];
		winningsPerLine = new int[playerNums.length];

		for(int r=0; r<playerNums.length; r++){				//loop through each line
			int[] currLine = playerNums[r];
			compare(currLine);								//check for matched nums
			matchedPerLine[r] = matchedNums;
			calcWinnings(currLine);							//calc winnings for line
			winningsPerLine[r] = winnings;
		}
	}

	//generate lotto numbers
    private void generateNums() {
        Random random = new Random();
		int numCounter = 0;

        while(lottoNums[5] == 0){
            int nextNum = random.nextInt(40) + 1;
            if(noRepeat(nextNum)){
                lottoNums[numCounter] = nextNum;
                ++numCounter;
            }
        }
    }
    //used by generateNums() to check for repetition
    private boolean noRepeat(int nextNum) {
        for(int i : lottoNums){
            if(i == nextNum){
                return false;
            }
        }
        return true;
    }

	//compare numbers and store total matched numbers in matchedNums
	private void compare(int[] line){
		matchedNums = 0;

		for(int i=0; i<6; i++){
			int num = line[i];
			for(int j=0; j<6; j++){
				if(num == lottoNums[j]){
					matchedNums = matchedNums+1;
					break;

				}
			}
		}
	}

	//calculate winnings
	private void calcWinnings(int[] line){
		switch(matchedNums){
			case MATCH_3:
				winnings = WIN_3;
				break;
			case MATCH_4:
				winnings = WIN_4;
				break;
			case MATCH_5:
				winnings = WIN_5;
				break;
			case MATCH_6:
				winnings = WIN_6;
				break;
			default:
				winnings = 0;
				break;
		}
	}

	//Getters
	public int[] getMatchedNums(){
		return matchedPerLine;
	}
	public int[] getLottoNums(){
		return lottoNums;
	}
	public int[] getWinnings(){  //returns winnings per line NOT total winnings
		return winningsPerLine;
	}
}