//BY: Kaleb Cole
//DATE: 2/3/2016
//Project 1
//Klein - CS 150
package program1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program1 {

    public static final String RACERS = "racers.txt";
    public static final String RACE_1 = "race1.txt";
    public static final String RACE_2 = "race2.txt";
    public static final String RACE_3 = "race3.txt";
    public static final String RACE_4 = "race4.txt";
    public static final String RACE_5 = "race5.txt";

    public static final String R = "\u001B[31m";
    public static final String G = "\u001B[32m";
    public static final String RE = "\u001B[0m";

    public static int numItems = 0;

    public static void main(String[] args) {
        RaceCar[] raceData = new RaceCar[100];

        for (int i = 0; i < 100; i++) {
            raceData[i] = new RaceCar();
            raceData[i].setLastPosition(i + 1);
        }
        //sortDrivers calls printTotals
        openFile(RACERS, raceData);
        sortDrivers(raceData);
        openFile(RACE_1, raceData);
        sortDrivers(raceData);
        openFile(RACE_2, raceData);
        sortDrivers(raceData);
        openFile(RACE_3, raceData);
        sortDrivers(raceData);
        openFile(RACE_4, raceData);
        sortDrivers(raceData);
        openFile(RACE_5, raceData);
        sortDrivers(raceData);
    }

    public static void openFile(String fileName, RaceCar[] raceData) {
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            readFile(input, fileName, raceData);
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }

    public static void readFile(Scanner input, String fileName, RaceCar[] raceData) {
        if (fileName.equals(RACERS)) {
            int i = 0;
            while (input.hasNext()) {
                if (!raceData[i].setCarNumber(input.next())) {

                }
                while (!input.hasNextInt() && input.hasNext()) {
                    raceData[i].setDriver(input.next());
                }
                i++;
                numItems++;
            }
        } else {
            int i, position, points, bonusPoints;
            String carNumber, driver = "", raceName = input.nextLine();
            System.out.println("         " + raceName);
            System.out.println("");
            while (input.hasNext()) {
                i = 0;
                driver = "";
                //inputs for the race data
                position = input.nextInt();
                carNumber = input.next();
                while (!input.hasNextInt() && input.hasNext()) {
                    driver = driver + " " + input.next();
                }
                points = input.nextInt();
                bonusPoints = input.nextInt();
                while (!(raceData[i].getCarNumber()).equals(carNumber)) {
                    i++;
                }
                //updating racer stats
                raceData[i].addRace();
                if (position == 1) {
                    raceData[i].addWin();
                }
                raceData[i].addPoints(points);
                if (bonusPoints == 5) {
                    raceData[i].addLedLaps();
                }
                if (bonusPoints == 10) {
                    raceData[i].addLedMostLaps();
                }
                System.out.printf("%3d %3s %-20s %-3d %2d", position, carNumber, driver, points, bonusPoints);
                System.out.println("");
            }
            System.out.println("");
        }
    }

    public static void printTotals(RaceCar[] raceData) {
        int i = 0;
        System.out.println("-------------------------------Standings-------------------------------");
        System.out.printf("| %3s | %3s | %-20s | %4s | %3s | %3s | %4s | %4s |", "Pos", "Car", "Driver", "Race", "PTS", "Led", "Most", "Wins");
        System.out.println(RE);
        while (!(raceData[i].getDriver()).equals("")) {
            raceData[i].setMovement(i + 1);
            if ((raceData[i].getMovement()).equals("\u25BC")) {
                System.out.printf("| " + R + "%3d" + RE + " | " + R + "%3s" + RE + " | " + R + "%-20s" + RE + " | " + R + "%-4d" + RE + " | " + R + "%3d" + RE + " | " + R + "%-3d" + RE + " | " + R + "%-4d" + RE + " | " + R + "%-4s" + RE + " | " + R + "%1s", (i + 1), raceData[i].getCarNumber(), raceData[i].getDriver(), raceData[i].getRaces(), raceData[i].getPoints(), raceData[i].getLedLaps(), raceData[i].getLedMostLaps(), raceData[i].getWins(), raceData[i].getMovement());
            } else if ((raceData[i].getMovement()).equals("\u25B2")) {
                System.out.printf("| " + G + "%3d" + RE + " | " + G + "%3s" + RE + " | " + G + "%-20s" + RE + " | " + G + "%-4d" + RE + " | " + G + "%3d" + RE + " | " + G + "%-3d" + RE + " | " + G + "%-4d" + RE + " | " + G + "%-4s" + RE + " | " + G + "%1s", (i + 1), raceData[i].getCarNumber(), raceData[i].getDriver(), raceData[i].getRaces(), raceData[i].getPoints(), raceData[i].getLedLaps(), raceData[i].getLedMostLaps(), raceData[i].getWins(), raceData[i].getMovement());
            } else {
                System.out.printf("| %3d | %3s | %-20s | %-4d | %3d | %-3d | %-4d | %-4s | %1s", (i + 1), raceData[i].getCarNumber(), raceData[i].getDriver(), raceData[i].getRaces(), raceData[i].getPoints(), raceData[i].getLedLaps(), raceData[i].getLedMostLaps(), raceData[i].getWins(), raceData[i].getMovement());
            }
            System.out.println(RE);
            raceData[i].setLastPosition(i + 1);
            i++;
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("");
    }

    public static void sortDrivers(RaceCar[] raceData) {
        int i, j;
        RaceCar temp;
        for (i = 1; i < numItems; i++) {
            temp = raceData[i];
            for (j = i - 1; (j >= 0) && (raceData[j].getPoints() < temp.getPoints()); j--) {
                raceData[j + 1] = raceData[j];
            }
            raceData[j + 1] = temp;
        }
        printTotals(raceData);
    }
}
