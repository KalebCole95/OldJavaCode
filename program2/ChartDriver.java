//NAME: Kaleb Cole
//PROJECT: Project 2
//DATE: 3/14/2016
package program2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChartDriver {

    public static final String RED = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    public static Scanner keyboard = new Scanner(System.in);
    public static final int MAX_CHARTS = 10;

    public static void main(String[] args) {
        Chart[] chartList = new Chart[MAX_CHARTS];
        for (int i = 0; i < 10; i++) {
            chartList[i] = new Chart();
        }
        String[] chartType = new String[MAX_CHARTS];
        int numCharts = fillArray(chartList, chartType);
        String choice;
        System.out.println(PURPLE + "Welcome to the charting program!" + RESET);

        do {
            chartList[getIndex(numCharts)].displayChart();
        } while (true);
    }

    public static int getIndex(int size) {
        int index;
        System.out.print(PURPLE + "Which chart would you like to see? (1..." + size + "): " + RESET);
        index = keyboard.nextInt();
        while ((index < 1) || (index > size)) {
            System.out.println(RED + "That is not a valid chart number. Try again." + RESET);
            System.out.print(PURPLE + "Which chart would you like to see? (1..." + size + "): " + RESET);
            index = keyboard.nextInt();
        }
        index--;
        return index;
    }

    public static int fillArray(Chart chartList[], String chartType[]) {
        int i = 0;
        boolean isDone = false;
        try {
            File file = new File("ChartData.txt");
            Scanner inputScanner = new Scanner(file);
            do {
                chartType[i] = inputScanner.nextLine().trim();
                switch (chartType[i]) {
                    case "C":
                        chartList[i] = new Chart();
                        break;
                    case "B":
                        chartList[i] = new BarChart();
                        break;
                    case "P":
                        chartList[i] = new PieChart();
                        break;
                }
                isDone = chartList[i].loadChart(inputScanner);
                i++;
            } while (!isDone);
            inputScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return i;
    }
}
