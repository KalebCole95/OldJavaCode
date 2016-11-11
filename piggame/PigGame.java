// Kaleb Cole
// Program: PigGame
// Last Edited: 10/21/2015
// This program uses a random number generator to simulate the roll of a dice, this roll
// is used to play the ever so popular Pig Dice Game.

package piggame;

import static java.lang.System.in;
import java.util.Random;
import java.util.Scanner;

public class PigGame {

    //Colors for error message and win or loss
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Random randomGenerator = new Random();
        Scanner scan = new Scanner(System.in);
        int roll = 0, computerTotal = 0, humanTotal = 0, turnTotal = 0, input = 0;
        // String instead of a boolean, because then more players can be added to the game
        String nameTurn = "";

        //Determines who starts the game and sets the roll turn
        if (randomGenerator.nextInt(2) == 0) {
            nameTurn = "cpu";
            System.out.println(ANSI_PURPLE + "The computer will go first!" + ANSI_RESET);
        } else {
            nameTurn = "human";
            System.out.println(ANSI_PURPLE + "You will go first!" + ANSI_RESET);
        }

        do {
            // CPU Turn
            if (nameTurn == "cpu") {
                // Gets the "Dice Roll" and determines if the CPU's turn is over
                do {
                    roll = randomGenerator.nextInt(6) + 1;
                    if (roll != 1) {
                        turnTotal = turnTotal + roll;
                    } else {
                        turnTotal = 0;
                    }
                    System.out.println("AI - Roll: " + roll + " , Turn Total: " + turnTotal);
                } while (roll != 1 && turnTotal < 20 && (turnTotal + computerTotal) < 100);
                computerTotal = computerTotal + turnTotal;
            }

            // Human Turn
            if (nameTurn == "human") {
                do {
                    do {
                        // Asks the player whether they want to roll again or not, will repeat until valid answer is given
                        System.out.print("Press 1 for ROLL, or 2 for HOLD: ");
                        input = scan.nextInt();
                        if (input != 1 && input != 2) {
                            System.out.println(ANSI_RED + "Invalid Entry: " + input + ANSI_RESET);
                        }
                    } while (input != 1 && input != 2);
                                            // Gets the "Dice Roll" and determines if the Player's turn is over
                    if (input == 1) {
                        roll = randomGenerator.nextInt(6) + 1;
                        if (roll != 1) {
                            turnTotal = turnTotal + roll;
                        } else {
                            turnTotal = 0;
                        }
                        System.out.println("PLAYER - Roll: " + roll + " , Turn Total: " + turnTotal);
                    }
                } while (input != 2 && roll != 1);
                humanTotal = humanTotal + turnTotal;
            }

            System.out.println(ANSI_BLUE + "== PLAYER: " + humanTotal + " AI: " + computerTotal + " ==" + ANSI_RESET);

            // Switches roll turn
            if (nameTurn == "cpu") {
                nameTurn = "human";
            } else {
                nameTurn = "cpu";
            }

            // Resets turnTotal
            turnTotal = 0;
        } while (computerTotal < 100 && humanTotal < 100);

        // Outputs win or lose message with pretty colors
        if (computerTotal >= 100) {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "[][][][][][][][][][][][][][][][][][][][][][]");
            System.out.println("[]" + ANSI_RED + "  Computer Wins! Better luck next time! " + ANSI_PURPLE + "[]");
            System.out.println("[][][][][][][][][][][][][][][][][][][][][][]" + ANSI_RESET);
        }
        if (humanTotal >= 100) {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "[][][][][][][][][][][][][][][][][]");
            System.out.println("[]" + ANSI_GREEN + "  You Win! Oink like a piggy! " + ANSI_PURPLE + "[]");
            System.out.println("[][][][][][][][][][][][][][][][][]" + ANSI_RESET);
        }
    }
}

