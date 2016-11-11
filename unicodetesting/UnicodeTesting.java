package unicodetesting;

import java.util.Scanner;

public class UnicodeTesting {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        System.out.println(RED + "XXX" + GREEN + "XXX" + YELLOW + "XXX" + BLUE + "XXX" + PURPLE +"XXX" + CYAN+"XXX");
    }
}
