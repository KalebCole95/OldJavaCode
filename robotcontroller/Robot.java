package robotcontroller;

import static java.lang.Math.sqrt;

public class Robot {

    final static String C_BLUE = "\u001B[34m";
    final static String C_RESET = "\u001B[0m";
    
    private String name;
    private int xPosition, yPosition;
    private String direction;

    public Robot() {
        this.name = "Robot";
        this.xPosition = 0;
        this.yPosition = 0;
        this.direction = "North";
        System.out.println(C_BLUE + name + " Created!" + C_RESET);
    }

    public Robot(int x, int y) {
        this.name = "Robot";
        this.xPosition = x;
        this.yPosition = y;
        this.direction = "North";
        System.out.println(C_BLUE + name + " Created!" + C_RESET);
    }

    public Robot(String name) {
        this.name = name;
        this.xPosition = 0;
        this.yPosition = 0;
        this.direction = "North";
        System.out.println(C_BLUE + name + " Created!" + C_RESET);
    }

    public String getName() {
        return name;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public String getDirection() {
        return direction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double distanceTo(Robot r) {
        return sqrt((xPosition ^ 2) + (yPosition ^ 2));
    }

    public void turnRight() {
        switch (direction) {
            case "North":
                direction = "East";
                break;
            case "East":
                direction = "South";
                break;
            case "South":
                direction = "West";
                break;
            case "West":
                direction = "North";
                break;
            default:
                System.out.println("Current Direction Not Found! Reset to North!");
                direction = "North";
                break;
        }
    }

    @Override
    public String toString() {
        return name + " is at (" + xPosition + ", " + yPosition + ") facing" + direction;
    }

    public void forward(int move) {
        switch (direction) {
            case "North":
                yPosition = yPosition + move;
                break;
            case "East":
                xPosition = xPosition + move;
                break;
            case "South":
                yPosition = yPosition - move;
                break;
            case "West":
                xPosition = xPosition - move;
                break;
            default:
                System.out.println("Current Direction Not Found! Reset to North! Went " + move + " spaces!");
                direction = "North";
                yPosition = yPosition + move;
                break;
        }
    }
}
