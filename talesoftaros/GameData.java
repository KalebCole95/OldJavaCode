
package talesoftaros;

import java.util.ArrayList;

public class GameData {
    
    public static final int WT_HEIGHT = 9, WT_WIDTH = 16;
    public static final String FOLDER = "Assets/";
    
    public static int pixels, currentArea;
    public static double windowHeight, windowWidth, gameSpeed;
    public static long timeStamp;
    public static String gameVersion;
    public static ArrayList<Area> areaSet;
    
    public GameData(){
        pixels = 64;
        currentArea = 0;
        gameSpeed = 1;
        windowHeight = WT_HEIGHT * pixels;
        windowWidth = WT_WIDTH * pixels;
        areaSet = new ArrayList();
    }
}
