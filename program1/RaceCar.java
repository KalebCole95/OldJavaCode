package program1;

public class RaceCar {
    //Certain setters were not used to protect data that should never be set directly
    private int points, races, ledLaps, ledMostLaps, lastPosition, wins;
    private String carNumber, driver, movement;

    public RaceCar() {
        this.points = 0;
        this.races = 0;
        this.lastPosition = 0;
        this.ledLaps = 0;
        this.ledMostLaps = 0;
        this.driver = "";
        this.movement = " ";
    }

    public boolean setCarNumber(String carNumber) {
        if (Integer.parseInt(carNumber) >= 0) {
            this.carNumber = carNumber;
            return true;
        }
        return false;
    }

    public boolean setDriver(String driver) {
        if (!driver.isEmpty()) {
            this.driver = this.driver + " " + driver;
            return true;
        }
        return false;
    }
    
    public void setLastPosition(int position){
        this.lastPosition = position;
    }
    
    public void setMovement(int position) {
        if (this.lastPosition > position) {
            this.movement = "\u25B2";
        }
        if(this.lastPosition < position){
            this.movement = "\u25BC";
        }
        if(this.lastPosition == position){
            this.movement = " ";
        }
    }

    public boolean addPoints(int points) {
        if (points >= 0) {
            this.points = this.points + points;
            return true;
        }
        return false;
    }

    public void addRace() {
        races++;
    }

    public void addWin() {
        wins++;
    }

    public void addLedLaps() {
        ledLaps++;
    }

    public void addLedMostLaps() {
        ledMostLaps++;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getPoints() {
        return points;
    }

    public int getRaces() {
        return races;
    }

    public int getWins() {
        return wins;
    }

    public int getLedLaps() {
        return ledLaps;
    }

    public int getLedMostLaps() {
        return ledMostLaps;
    }

    public String getDriver() {
        return driver;
    }
    
    public String getMovement() {
        return movement;
    }
    public int getLastPosition(){
        return lastPosition;
    }
}
