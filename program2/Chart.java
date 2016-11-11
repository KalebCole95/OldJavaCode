//NAME: Kaleb Cole
//PROJECT: Project 2
//DATE: 3/14/2016
package program2;

import java.util.Scanner;

public class Chart {

    protected Category[] list = new Category[10];
    protected int numberOfCategories;

    public Chart() {
        for (int i = 0; i < 10; i++) {
            list[i] = new Category();
        }
        numberOfCategories = 0;
    }

    public boolean loadChart(Scanner inputScanner) {
        numberOfCategories = inputScanner.nextInt();
        int i = 0;
        String temp = "";
        boolean isDone = false;
        while (!isDone && inputScanner.hasNextLine()) {
            inputScanner.nextLine();
            temp = inputScanner.nextLine().trim();
            if (temp.isEmpty()) {
                isDone = true;
            } else {
                getCategoryAt(i).setTitle(temp);
                getCategoryAt(i).setValue(inputScanner.nextDouble());
                i++;
            }
        }
        if (inputScanner.hasNextLine()) {
            return false;
        }
        return true;
    }

    public Category getCategoryAt(int position) {
        if (position >= 0 && position < numberOfCategories) {
            return list[position];
        }
        return null;
    }

    public int getNumberOfCategories() {
        return numberOfCategories;
    }

    public void displayChart() {
        for (int i = 0; i < numberOfCategories; i++) {
            System.out.printf("%15s: %-5.2f", getCategoryAt(i).getTitle(), getCategoryAt(i).getValue());
            System.out.println("");
        }
    }
}
