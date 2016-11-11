
package talesoftaros;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WindowController {
    
    Pane mainPane, hitBoxPane, hudPane;
    FadeTransition doorFadeDefault;
    Rectangle doorFadeDefaultRectangle;
    Scene scene;
    
    public WindowController(){
        mainPane = new Pane();
        hitBoxPane = new Pane();
        hudPane = new Pane();
        doorFadeDefault = new FadeTransition();
        doorFadeDefaultRectangle = new Rectangle(0,0,GameData.windowWidth,GameData.windowHeight);
        scene = new Scene(mainPane, GameData.windowWidth, GameData.windowHeight, Color.BLACK);
        
        mainPane.getChildren().addAll(hitBoxPane, hudPane);
        doorFadeDefault.setNode(mainPane);
    }
}
