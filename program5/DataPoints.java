//NAME: Kaleb Cole
//PROJECT: Project 5
//DATE: 4/27/2016
package program5;

import java.util.Comparator;

public class DataPoints implements Comparable<DataPoints> {

    public double data;
    public String peak;
    public int xPos, yPos;

    public DataPoints(double data, int xPos, int yPos) {
        this.data = data;
        this.xPos = xPos;
        this.yPos = yPos;
        peak = "  ";
    }

    @Override
    public int compareTo(DataPoints o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Comparators {
        public static final Comparator<DataPoints> DATA = (DataPoints o1, DataPoints o2) -> Double.compare(o1.data, o2.data);
    }
}
