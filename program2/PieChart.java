//NAME: Kaleb Cole
//PROJECT: Project 2
//DATE: 3/14/2016
package program2;

import static java.lang.Math.PI;

public class PieChart extends Chart {

    public static final String BLACK = "\u001B[41m\u001B[37mX\u001B[0m";
    public static final String OUT_1 = "\u001B[41m \u001B[0m";
    public static final String OUT_2 = "\u001B[42m \u001B[0m";
    public static final String OUT_3 = "\u001B[44m \u001B[0m";
    public static final String OUT_4 = "\u001B[45m \u001B[0m";
    public static final String OUT_5 = "\u001B[46m \u001B[0m";
    public static final String OUT_6 = "\u001B[41m\u001B[37mX\u001B[0m";
    public static final String OUT_7 = "\u001B[42m\u001B[37mX\u001B[0m";
    public static final String OUT_8 = "\u001B[44m\u001B[37mX\u001B[0m";
    public static final String OUT_9 = "\u001B[45m\u001B[37mX\u001B[0m";
    public static final String OUT_10 = "\u001B[46m\u001B[37mX\u001B[0m";
    public static final String[] OUTPUT_LIST = {OUT_1, OUT_2, OUT_3, OUT_4, OUT_5, OUT_6, OUT_7, OUT_8, OUT_9, OUT_10};
    public static final int MAX_RADIUS = 8;

    public PieChart() {
        super();
    }

    @Override
    public void displayChart() {
        double[] percent, angle;
        int total = 0;
        percent = new double[numberOfCategories];
        angle = new double[numberOfCategories];
        double angleSoFar = 0;
        for (int i = 0; i < numberOfCategories; i++) {
            total += getCategoryAt(i).getValue();
        }
        for (int i = 0; i < numberOfCategories; i++) {
            percent[i] = getCategoryAt(i).getValue() / total;
            angle[i] = angleSoFar + 2 * Math.PI * percent[i];
            angleSoFar = angle[i];
        }
        int x, y;
        double distanceFromCenter, currentAngle;
        for (y = MAX_RADIUS; y >= -MAX_RADIUS; y--) {
            System.out.print("\t");
            for (x = (int) (-2.0 * MAX_RADIUS); x <= (int) (2.0 * MAX_RADIUS); x++) {
                distanceFromCenter = Math.sqrt((2.0 * x / 4.0) * (2.0 * x / 4.0) + y * y);
                if (distanceFromCenter > MAX_RADIUS) {
                    System.out.print(" ");
                } else {
                    currentAngle = Math.atan2(2.0 * x / 3.0, y); // atan2 returns arctan of 2x/3y
                    if (currentAngle < 0) { // (adjusting if y=0); 2*PI may be                    
                        currentAngle += 2 * PI;          // added to yield angles in [0,2*PI].
                    }
                    if (currentAngle <= angle[0]) {
                        System.out.print(OUT_1);
                    } else if (currentAngle <= angle[1]) {
                        System.out.print(OUT_2);
                    } else if (currentAngle <= angle[2]) {
                        System.out.print(OUT_3);
                    } else if (currentAngle <= angle[3]) {
                        System.out.print(OUT_4);
                    } else if (currentAngle <= angle[4]) {
                        System.out.print(OUT_5);
                    } else if (currentAngle <= angle[5]) {
                        System.out.print(OUT_6);
                    } else if (currentAngle <= angle[6]) {
                        System.out.print(OUT_7);
                    } else if (currentAngle <= angle[7]) {
                        System.out.print(OUT_8);
                    } else if (currentAngle <= angle[8]) {
                        System.out.print(OUT_9);
                    } else if (currentAngle <= angle[9]) {
                        System.out.print(OUT_10);
                    } else {
                        System.out.print(BLACK);
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("----------- LEGEND ------------");
        for (int i = 0; i < numberOfCategories; i++) {
            System.out.printf("%15s: %1s (%-2.2f%1s)", getCategoryAt(i).getTitle(), OUTPUT_LIST[i], (100 * percent[i]), "%");
            System.out.println("");
        }
    }
}
