
package talesoftaros;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class GameDriver extends Application {
    
    public final AnimationTimer timer;
    
    public GameDriver() {
        this.timer = new AnimationTimer(){
            @Override
            public void handle (long time){
                System.out.println(GameData.timeStamp - time);
                GameData.timeStamp = time;
                driver();
            }
        };
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage mainStage) {
        timer.start();
    }
    
    public static void driver(){
        
    }
}
