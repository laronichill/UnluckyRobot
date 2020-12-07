package unluckyrobot;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author laronichill
 */

public class UnluckyRobot {
    public static void main(String[] args) {
	Scanner console = new Scanner(System.in);
	int x = 0;
	int y = 0;
	int itrCount = 0;
	int totalScore = 0;
	char direction;
	
	do {
	displayInfo(x, y, itrCount, totalScore);
	direction = inputDirection();
	    if (doesExceed(x, y, direction)) {
		System.out.println("Exceed boundary, -2000 damage applied");
		totalScore += -2000;
	    } else {
		switch (direction){
		    case 'u':
			totalScore += -10;
			y++;
			totalScore += punishOrMercy(direction, reward());
			break;
		    case 'd':
			totalScore += -50;
			y--;
			totalScore += punishOrMercy(direction, reward());
			break;
		    case 'l':
			totalScore += -50;
			x--;
			totalScore += punishOrMercy(direction, reward());
			break;
		    case 'r':
			totalScore += -50;
			x++;
			totalScore += punishOrMercy(direction, reward());
			break;
		}
	    }
	    itrCount++;
	} while (!isGameOver(x, y, totalScore, itrCount));
	evaluation(totalScore);
    }
    public static void displayInfo(int x, int y, int itrCount, int totalScore) {
	System.out.printf("\nFor point (X=%d, Y=%d) at iteration: %d the total"
		+ " score is: %d\n", x, y, itrCount, totalScore);
    }
    public static boolean doesExceed(int x, int y, char direction) {
	switch (direction){
	    case 'u':
		return !(y < 4);
	    case 'd':
		return !(y > 0);
	    case 'l':
		return !(x > 0);
	    case 'r':
		return !(x < 4);
	    default:
		return true;
	}
    }
    public static int reward() {
	Random rand = new Random();
	int dice = rand.nextInt(6) + 1;
	switch (dice){
	    case 1:
		System.out.printf("Dice: 1, reward: -100\n");
		return -100;
	    case 2:
		System.out.printf("Dice: 2, reward: -200\n");
		return -200;
	    case 3:
		System.out.printf("Dice: 3, reward: -300\n");
		return -300;
	    case 4:
		System.out.printf("Dice: 4, reward: 300\n");
		return 300;
	    case 5:
		System.out.printf("Dice: 5, reward: 400\n");
		return 400;
	    default:
		System.out.printf("Dice: 6, reward: 500\n");
		return 600;
	}
    }
    public static int punishOrMercy(char direction, int reward) {
	Random rand = new Random();
	if (reward < 0 && direction == 'u') {
	    int coinFlip = rand.nextInt(2);
	    if (coinFlip == 0) {
		reward = 0;
		System.out.println("Coin: tail "
			+ "| Mercy, the negative was removed");
	    } else {
		System.out.println("Coin: heads "
			+ "| No mercy, the negative was applied");
	    }
	}
	return reward;
    }
    public static String toTitleCase(String str) {
	return Character.toTitleCase(str.charAt(0)) 
		+ str.substring(1,str.indexOf(" ") + 1) + // first word 
		Character.toTitleCase(str.charAt(str.indexOf(" ") + 1)) 
		+ str.substring(str.indexOf(" ") + 2); // second word
    }
    public static void evaluation(int totalScore) {
	Scanner console = new Scanner(System.in);
	System.out.print("\nPlease enter your name (First and Last name): ");
	String name = console.nextLine();
	if (totalScore >= 2000){
	    System.out.printf("Victory! %s, your score is %d\n",
		    toTitleCase(name), totalScore);
	} else {
	    System.out.printf("Mission failed! %s, your score is %d\n",
		    toTitleCase(name), totalScore);
	}
    }
    public static char inputDirection() {
	Scanner console = new Scanner(System.in);
	char direction;
	do {
	    System.out.print("Please input a valid direction: ");
	    direction = console.next().charAt(0);
	} while (!(direction == 'u' || direction == 'd' ||
		direction == 'l' || direction == 'r' ));
	return direction;
    }
    public static boolean isGameOver(int x, int y, int totalScore, int itrCount){
	if (itrCount > 20) {
	    return true;
	} else if (totalScore < -1000 ) {
	    return true;
	} else if (totalScore > 2000 ) {
	    return true;
	} else return ( y == 0 || y == 4) && x == 4;
    }
}
