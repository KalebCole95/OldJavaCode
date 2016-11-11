/*
 * Your Name
 * Lab 10
 * Date
 * Lab Section
 * Lecture Section
 * Lecture Instructor
 */
package lab10;

import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Lab10 extends Application {

    final int WIDTH = 600;
    final int HEIGHT = 400;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #888888;");

        // Add the lines to the root pane here
                Rectangle line4 = new Rectangle(0, 0, 5, HEIGHT);
        line4.setFill(Color.RED);
        root.getChildren().add(line4);
        
        Rectangle line1 = new Rectangle(0, HEIGHT - 5, WIDTH, 5);
        line1.setFill(Color.GOLDENROD);
        root.getChildren().add(line1);

        Rectangle line2 = new Rectangle(0, HEIGHT - 50, WIDTH, 5);
        line2.setFill(Color.BLACK);
        root.getChildren().add(line2);

        Rectangle line3 = new Rectangle(0, 0, 5, HEIGHT);
        line3.setFill(Color.GREEN);
        root.getChildren().add(line3);



        EventHandler<ActionEvent> controlLines = e -> {
            // Code that executes during animation goes here
            int line1Y = moveLineUp(line1, 5);
            line1.setY(line1Y);
            int line2Y = moveLineUp(line2, 10);
            line2.setY(line2Y);
            int line3X = moveLineOver(line3, 10);
            line3.setX(line3X);
            int line4X = moveLineOver(line4, 5);
            line4.setX(line4X);
            line4.setWidth(moveAndExpandLine(line4, 1)/4);
            line4.setHeight(moveAndExpandLine2(line4, 5));

        };
        // create an animation for this stage
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(50), controlLines));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Lines");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int moveLineUp(Rectangle line, int speed) {
        if (line.getY() < speed) {
            line.setY(HEIGHT);
        } else {
            line.setY(line.getY() - speed);
        }
        return (int) line.getY();
    }

    private int moveLineOver(Rectangle line, int speed) {
        if (line.getX() > WIDTH) {
            line.setX(0);
        } else {
            line.setX(line.getX() + speed);
        }
        return (int) line.getX();
    }

       private int moveAndExpandLine(Rectangle line, int speed) {
        if (line.getX() > WIDTH) {
            line.setX(0);
        } else {
            line.setX(line.getX() + speed);
        }
        return (int) line.getX();
    }
              private int moveAndExpandLine2(Rectangle line, int speed) {
        if (line.getWidth() <0) {
            line.setWidth(0);
        } else {
            line.setWidth(line.getWidth() - speed);
        }
        return (int) line.getX();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
