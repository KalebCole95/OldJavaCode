package notfour;

import java.util.Random;
import java.util.Scanner;

public class NotFour {

    final static String C_RESET = "\u001B[0m";
    final static String C_BLACK = "\u001B[30m";
    final static String C_RED = "\u001B[31m";
    final static String C_GREEN = "\u001B[32m";
    final static String C_YELLOW = "\u001B[33m";
    final static String C_BLUE = "\u001B[34m";
    final static String C_PURPLE = "\u001B[35m";
    final static String C_CYAN = "\u001B[36m";
    final static String C_WHITE = "\u001B[37m";

    final static String PLAYER_ONE = C_RED + "\u2022" + C_RESET;
    final static String PLAYER_TWO = C_BLUE + "\u2022" + C_RESET;
    final static String UNUSED_SPACE = C_WHITE + " " + C_RESET;

    final static int HEIGHT = 6;
    final static int WIDTH = 6;

    static int placement = -1, turn = 0;
    static String[][] board = new String[HEIGHT + 2][WIDTH];
    static Scanner input = new Scanner(System.in);
    static Random randomGenerator = new Random();
    static boolean gameFinished = false, isHumanTurn = randomGenerator.nextBoolean(), isValidSpot = false, isFinishedChecking = false;
    static String playerWin = "None";

    public static void main(String[] args) {
        initializeGameSpace();
        displayGameSpace();

        while (!gameFinished) { //runs until winner or tie has been detected
            turn();
            playerWin = winDetection();
            displayGameSpace();
            if (turn >= WIDTH * HEIGHT || playerWin == "Player" || playerWin == "Computer") {
                if(turn >= WIDTH * HEIGHT){
                    playerWin = "Draw"; // detects a tie
                }
                gameFinished = true;
            }
        }
        //win or lose or tie output for game
        System.out.println("Game Over!");
        if(playerWin == "Player"){
            System.out.println("You Lose!");
        }
        if(playerWin == "Computer"){
            System.out.println("You Win!");
        }
        if(playerWin == "Draw"){
            System.out.println("It's A Draw!");
        }
    }

    static void initializeGameSpace() {             //Sets the initial gamespace to the board array
        for (int h = 0; h < HEIGHT + 2; h++) {
            for (int w = 0; w < WIDTH; w++) {
                if (h == 0 || h == HEIGHT + 1) {
                    board[h][w] = (w) + "";
                } else {
                    board[h][w] = UNUSED_SPACE;
                }
            }
        }
    }

    static void displayGameSpace() {                //Displays the gamespace and collumn numbers
        for (int h = HEIGHT + 1; h >= 0; h--) {
            for (int w = 0; w < WIDTH; w++) {
                if (h != 0 && h != (HEIGHT + 1)) {
                    System.out.print("|");
                } else {
                    if (w == 0) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print(board[h][w]);
                if ((h == 0 || h == (HEIGHT + 1)) && w == WIDTH - 1) {
                    System.out.print("|");
                }
            }
            if (h != 0 && h != (HEIGHT + 1)) {
                System.out.print("|");
            }
            System.out.println();
        }
    }

    static void turn() {            //Determines whose turn it is and calls placement
        isValidSpot = false;
        if (isHumanTurn) {
            humanPlacement();
            isHumanTurn = false;
        } else {
            computerPlacement();
            isHumanTurn = true;
        }
        turn++;
    }

    static void humanPlacement() {      //Does the process for taking a human turn
        System.out.print(C_PURPLE + "Player Choice: ");
        placement = (input.nextInt());
        System.out.print(C_RESET);
        int h = 1;
        do {
            if (placement < 0 || placement > (WIDTH - 1)) {
                System.out.print(C_PURPLE + "It Must be a labeled column name! :" + C_RESET);
                placement = input.nextInt();
                h = 1;
            } else {
                if (board[h][placement] == UNUSED_SPACE) {
                    board[h][placement] = PLAYER_ONE;
                    isValidSpot = true;
                } else {
                    h++;
                    if (h > HEIGHT) {
                        System.out.print(C_PURPLE + "column " + placement + " is full choose a new column! :" + C_RESET);
                        placement = input.nextInt();
                        h = 1;
                    }
                }
            }
        } while (!isValidSpot);
    }

    static void computerPlacement() {           //Does the process for taking a computer turn
        placement = randomGenerator.nextInt(WIDTH);
        int h = 1;
        do {
            if (board[h][placement] == UNUSED_SPACE) {
                board[h][placement] = PLAYER_TWO;
                System.out.println(C_PURPLE + "Computer Choice: " + C_RESET + (placement));
                isValidSpot = true;
            } else {
                h++;
                if (h > HEIGHT) {
                    placement = randomGenerator.nextInt(WIDTH);
                    h = 1;
                }
            }
        } while (!isValidSpot);
    }

    static String winDetection() {          //checks and calls win for player and computer
        for (int h = 1; h < HEIGHT + 1; h++) {
            for (int w = 0; w < WIDTH; w++) {
                if (board[h][w] == PLAYER_ONE) {
                    if (winChecker(h, w)) {
                        return "Player";
                    }
                }
                if (board[h][w] == PLAYER_TWO) {
                    if (winChecker(h, w)) {
                        return "Computer";
                    }
                }
            }
        }
        return "None";
    }

    static Boolean winChecker(int h, int w) {
        //Checks all horizontal wins
        if (w < WIDTH - 3) {
            if (board[h][w] == board[h][w + 1] && board[h][w + 1] == board[h][w + 2] && board[h][w + 2] == board[h][w + 3]) {
                for (int c = 0; c < 4; c++) {
                    board[h][w + c] = "\u001B[43m" + board[h][w + c];
                }
                return true;
            }
        }
        //Checks all vertical wins
        if (h > 3) {
            if (board[h][w] == board[h - 1][w] && board[h - 1][w] == board[h - 2][w] && board[h - 2][w] == board[h - 3][w]) {
                for (int c = 0; c < 4; c++) {
                    board[h - c][w] = "\u001B[43m" + board[h - c][w];
                }
                return true;
            }
        }
        //Checks all diagonal down to the right
        if (h > 3 && w < WIDTH - 3) {
            if (board[h][w] == board[h - 1][w + 1] && board[h - 1][w + 1] == board[h - 2][w + 2] && board[h - 2][w + 2] == board[h - 3][w + 3]) {
                for (int c = 0; c < 4; c++) {
                    board[h - c][w + c] = "\u001B[43m" + board[h - c][w + c];
                }
                return true;
            }
        }
        //Checks all diagonal up to the right
        if (h < HEIGHT - 3 && w < WIDTH - 3) {
            if (board[h][w] == board[h + 1][w + 1] && board[h + 1][w + 1] == board[h + 2][w + 2] && board[h + 2][w + 2] == board[h + 3][w + 3]) {
                for (int c = 0; c < 4; c++) {
                    board[h + c][w + c] = "\u001B[43m" + board[h + c][w + c];
                }
                return true;
            }
        }
        return false;
    }
}