package robotcontroller;

import java.util.Scanner;

public class RobotController {

    final static String C_RESET = "\u001B[0m";
    final static String C_RED = "\u001B[31m";
    final static String C_GREEN = "\u001B[32m";
    final static String C_BLUE = "\u001B[34m";
    final static String C_PURPLE = "\u001B[35m";
    final static String C_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        String command = "";
        boolean isFinished = false;
        Scanner keyboard = new Scanner(System.in);
        Robot robo1 = new Robot();

        do {
            System.out.print(C_PURPLE + "Next Command: " + C_RESET);
            command = keyboard.next();
            switch (command) {
                case "f":
                    System.out.print(C_PURPLE + "Distance: " + C_RESET);
                    int move = keyboard.nextInt();
                    robo1.forward(move);
                    System.out.println(C_GREEN + robo1.getName() + " moved forward " + move + C_RESET);
                    break;
                case "t":
                    robo1.turnRight();
                    System.out.println(C_GREEN + robo1.getName() + " is now facing " + robo1.getDirection() + C_RESET);
                    break;
                case "n":
                    System.out.print(C_PURPLE + "Name: " + C_RESET);
                    String name = keyboard.next();
                    System.out.println(C_GREEN + robo1.getName() + " is now " + name + C_RESET);
                    robo1.setName(name);
                    break;
                case "r":
                    System.out.println(C_GREEN + robo1.toString() + C_RESET);
                    break;
                case "e":
                    System.out.println(C_BLUE + "Program Finished!" + C_RESET);
                    isFinished = true;
                    break;
                default:
                    System.out.println(C_RED + "Incorrect Input! " + command + " unrecognized! Try Again!" + C_RESET);
                    break;
            }
        } while (!isFinished);
    }
}
