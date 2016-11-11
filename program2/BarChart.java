//NAME: Kaleb Cole
//PROJECT: Project 2
//DATE: 3/14/2016
package program2;

public class BarChart extends Chart {

    public static final int BAR_CHART_HEIGHT = 20;
    public static final String OUT_1 = " \u001B[41m \u001B[0m ";
    public static final String OUT_2 = " \u001B[42m \u001B[0m ";
    public static final String OUT_3 = " \u001B[44m \u001B[0m ";
    public static final String OUT_4 = " \u001B[45m \u001B[0m ";
    public static final String OUT_5 = " \u001B[46m \u001B[0m ";
    public static final String OUT_6 = " \u001B[41m\u001B[37mX\u001B[0m ";
    public static final String OUT_7 = " \u001B[42m\u001B[37mX\u001B[0m ";
    public static final String OUT_8 = " \u001B[44m\u001B[37mX\u001B[0m ";
    public static final String OUT_9 = " \u001B[45m\u001B[37mX\u001B[0m ";
    public static final String OUT_10 = " \u001B[46m\u001B[37mX\u001B[0m ";
    public static final String[] OUTPUT_LIST = {OUT_1, OUT_2, OUT_3, OUT_4, OUT_5, OUT_6, OUT_7, OUT_8, OUT_9, OUT_10};

    public BarChart() {
        super();
    }

    @Override
    public void displayChart() {
        double largestNum = 0, numPerSquare;
        int[] numSquares;
        numSquares = new int[numberOfCategories];
        for (int i = 0; i < numberOfCategories; i++) {
            if (largestNum < getCategoryAt(i).getValue()) {
                largestNum = getCategoryAt(i).getValue();
            }
        }
        numPerSquare = largestNum / BAR_CHART_HEIGHT;
        for (int i = 0; i < numberOfCategories; i++) {
            numSquares[i] = (int) Math.round(getCategoryAt(i).getValue() / numPerSquare);
        }
        for (int i = BAR_CHART_HEIGHT; i > 0; i--) {
            System.out.printf("%5.0f |", (i * numPerSquare));
            for (int j = 0; j < numberOfCategories; j++) {
                if (i <= numSquares[j]) {
                    System.out.print(OUTPUT_LIST[j]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
        System.out.println("----------- LEGEND ------------");
        for (int i = 0; i < numberOfCategories; i++) {
            System.out.printf("%15s:%3s(%-5.2f)", getCategoryAt(i).getTitle(), OUTPUT_LIST[i], getCategoryAt(i).getValue());
            System.out.println("");
        }
    }
}
