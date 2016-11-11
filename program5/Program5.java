//NAME: Kaleb Cole
//PROJECT: Project 5
//DATE: 4/27/2016
package program5;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import static program5.DataPoints.Comparators.DATA;

public class Program5 {

    public static DataPoints[][] dataArray = new DataPoints[250][250];
    public static ArrayList<DataPoints> peakList = new ArrayList();

    public static void main(String[] args) {
        readInFile();
        findPeaks();
        findTallestPeaks();
        writeToFile();
    }

    public static void readInFile() {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream("datapoints.dat"));
            for (int i = 0; i < 250; i++) {
                for (int j = 0; j < 250; j++) {
                    dataArray[i][j] = new DataPoints(((double) Math.round(input.readDouble() * 1000) / 1000), j, i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void findPeaks() {
        for (int i = 0; i < 250; i++) {
            for (int j = 0; j < 250; j++) {
                if (i != 0 && i != 249 && j != 0 && j != 249) {
                    if (dataArray[i][j].data >= dataArray[i][j - 1].data && dataArray[i][j].data >= dataArray[i][j + 1].data && dataArray[i][j].data >= dataArray[i - 1][j - 1].data && dataArray[i][j].data >= dataArray[i - 1][j].data && dataArray[i][j].data >= dataArray[i - 1][j + 1].data && dataArray[i][j].data >= dataArray[i + 1][j - 1].data && dataArray[i][j].data >= dataArray[i + 1][j].data && dataArray[i][j].data >= dataArray[i + 1][j + 1].data) {
                        dataArray[i][j].peak = "* ";
                        peakList.add(dataArray[i][j]);
                    } else {
                        dataArray[i][j].peak = "  ";
                    }
                } else {
                    dataArray[i][j].peak = "  ";
                }
            }
        }
    }

    public static void findTallestPeaks() {
        peakList.sort(DATA);
        Collections.reverse(peakList);
        for (int i = 0; i < 100; i++) {
            peakList.get(i).peak = "**";
        }
    }

    public static void writeToFile() {
        try {
            PrintWriter writer = new PrintWriter("PeaksOutput.txt", "UTF-8");
            writer.println("There are " + peakList.size() + " peaks!");
            for (int i = 0; i < 250; i++) {
                for (int j = 0; j < 250; j++) {
                    writer.printf("%8.3f%2s", dataArray[i][j].data, dataArray[i][j].peak);
                }
                writer.println("");
            }
            writer.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
