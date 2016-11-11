/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author kaleb
 */
public class Program3 extends Application {

    @Override
    public void start(Stage displayStage) {
        Stage buttonStage = new Stage();
        VBox buttonPane = new VBox(20);
        Pane displayPane = new Pane();

        buttonPane.setPadding(new Insets(5, 5, 5, 5));
        buttonPane.setStyle("-fx-border-color: green");
        buttonPane.setStyle("-fx-border-width: 2px; -fx-border-color: green");
        RadioButton btn1 = new RadioButton(), btn2 = new RadioButton(), btn3 = new RadioButton();
        buttonPane.getChildren().addAll(btn1, btn2, btn3);

        ToggleGroup group = new ToggleGroup();
        btn1.setToggleGroup(group);
        btn2.setToggleGroup(group);
        btn3.setToggleGroup(group);
        btn1.setText("Creeper");
        btn2.setText("Sun");
        btn3.setText("Snowman");

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pane displayPane = new Pane();
                readFile("creeper.txt", displayPane, displayStage);
                Scene displayScene = new Scene(displayPane, 675, 675);
                displayStage.setTitle("Creeper");
                displayStage.setScene(displayScene);
                displayStage.show();
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pane displayPane = new Pane();
                readFile("sun.txt", displayPane, displayStage);
                Scene displayScene = new Scene(displayPane, 675, 675);
                displayStage.setTitle("Sun");
                displayStage.setScene(displayScene);
                displayStage.show();
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pane displayPane = new Pane();
                readFile("snowman.txt", displayPane, displayStage);
                Scene displayScene = new Scene(displayPane, 675, 675);
                displayStage.setTitle("Snowman");
                displayStage.setScene(displayScene);
                displayStage.show();
            }
        });

        displayStage.setOnCloseRequest(e -> Platform.exit());
        buttonStage.setOnCloseRequest(e -> Platform.exit());

        Scene buttonScene = new Scene(buttonPane, 675, 675);
        buttonStage.setTitle("Hello World!");
        buttonStage.setScene(buttonScene);
        buttonStage.show();

        Scene displayScene = new Scene(displayPane, 675, 675);
        displayStage.setTitle("Graphic Displayer");
        displayStage.setScene(displayScene);
        displayStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void readFile(String fileName, Pane displayPane, Stage displayStage) {
        try {
            File file = new File(fileName);
            Scanner inputScanner = new Scanner(file);
            displayImage(inputScanner, displayPane);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void displayImage(Scanner inputScanner, Pane displayPane) {
        String currentOperation;
        double currentColor1 = 0, currentColor2 = 0, currentColor3 = 0;
        while (inputScanner.hasNext()) {
            currentOperation = inputScanner.next();
            switch (currentOperation) {
                case "COLOR":
                    currentColor1 = inputScanner.nextDouble();
                    currentColor2 = inputScanner.nextDouble();
                    currentColor3 = inputScanner.nextDouble();
                    break;
                case "LINE":
                    Line line = new Line(inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt());
                    line.setStroke(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    displayPane.getChildren().add(line);
                    break;
                case "OVAL":
                    Ellipse ellipse = new Ellipse(inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt());
                    ellipse.setStroke(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    ellipse.setFill(Color.TRANSPARENT);
                    displayPane.getChildren().add(ellipse);
                    break;
                case "FILLEDOVAL":
                    Ellipse ellipseFilled = new Ellipse(inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt());
                    ellipseFilled.setStroke(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    ellipseFilled.setFill(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    displayPane.getChildren().add(ellipseFilled);
                    break;
                case "RECTANGLE":
                    Rectangle rectangle = new Rectangle(inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt());
                    rectangle.setStroke(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    rectangle.setFill(Color.TRANSPARENT);
                    displayPane.getChildren().add(rectangle);
                    break;
                case "FILLEDRECTANGLE":
                    Rectangle rectangleFilled = new Rectangle(inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt(), inputScanner.nextInt());
                    rectangleFilled.setStroke(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    rectangleFilled.setFill(Color.color(currentColor1, currentColor2, currentColor3, 1));
                    displayPane.getChildren().add(rectangleFilled);
                    break;
            }
        }
    }
}
