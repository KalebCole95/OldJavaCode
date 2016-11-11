package project.pkg1.blocks;

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
import java.util.Random;
import java.util.Scanner;

public class Project1Blocks extends Application {

    Scanner in = new Scanner(System.in);
    final public static int pixelWidth = 3, offset = 12, spacing = 6, numColors = 4;
    int numberOfSquares, sideLength, depthOfSquares, colorAsInt, randomColorSet, generationChoice, speed = 50;
    Random randNumGen = new Random();

    Color[] blueColorSet = {Color.LIGHTBLUE, Color.BLUE, Color.DARKBLUE, Color.WHITE};
    Color[] redColorSet = {Color.PINK, Color.RED, Color.DARKRED, Color.WHITE};
    Color[] greenColorSet = {Color.LIGHTGREEN, Color.GREEN, Color.DARKGREEN, Color.WHITE};
    Color[] blackColorSet = {Color.GRAY, Color.DARKGRAY, Color.BLACK, Color.WHITE};

    Color[][] possibleColorSet = {blueColorSet, redColorSet, greenColorSet, blackColorSet};

    @Override
    public void start(Stage primaryStage) {

        //setGenerationType();
        setBlockInformation();
        
        int[][][] blockColorFromSet = new int[numberOfSquares][numberOfSquares][depthOfSquares + 1], blockColorSet = new int[numberOfSquares][numberOfSquares][depthOfSquares + 1];
        Rectangle[][][] blockObjectData = new Rectangle[numberOfSquares][numberOfSquares][depthOfSquares];
        
        randomColorSet = randNumGen.nextInt(3);
        Pane pane = new Pane();

        generationExecuter(blockColorFromSet, blockObjectData, blockColorSet, pane);

        EventHandler<ActionEvent> controlLines = e -> {
            blockShineOutward(blockColorFromSet, blockObjectData, blockColorSet);
        };
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(speed), controlLines));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        Scene scene = new Scene(pane, (offset * 2 + (sideLength + spacing) * numberOfSquares - spacing), (offset * 2 + (sideLength + spacing) * numberOfSquares) - spacing);
        primaryStage.setTitle("Kaleb's Random Block Generator!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private int setColor(int depth, int row, int column, int[][][] blockData) {
        colorAsInt = randNumGen.nextInt(numColors);
        boolean isFinished = false;
        while (isFinished == false) {
            if (depth == depthOfSquares - 1) {
                for (int depthCheck = 0; depthCheck < depthOfSquares; depthCheck++) {
                    if (blockData[row][column][depthCheck] == numColors - 1) {
                        isFinished = true;
                    }
                }
                if (isFinished == false) {
                    colorAsInt = numColors - 1;
                    isFinished = true;
                }
            } else {
                isFinished = true;
            }
        }
        return colorAsInt;
    }

    void setBlockInformation() {
        System.out.print("Number of boxes Wide: ");
        while (numberOfSquares < 1 || numberOfSquares > 50) {
            numberOfSquares = in.nextInt();
            if (numberOfSquares < 1 || numberOfSquares > 50) {
                System.out.println("Must Be Between 1 and 50!");
            }
        }
        System.out.print("Depth of Boxes: ");
        while (depthOfSquares < 1) {
            depthOfSquares = in.nextInt();
            if (depthOfSquares < 1) {
                System.out.println("Must Be At Least 1!");
            }
        }
        sideLength = depthOfSquares * pixelWidth * 2;
    }

    void setGenerationType() {
        System.out.println("Choose a type of block arrangement!");
        System.out.println("1.) Generic");
        System.out.print("Choice? : ");
        generationChoice = in.nextInt();
    }

    void generationExecuter(int[][][] blockData, Rectangle[][][] blockObjectData, int[][][] blockColorSet, Pane pane) {
        switch (generationChoice) {
            case 1:
                genericBlockGeneration(blockData, blockObjectData, blockColorSet, pane);
                break;
            default:
                genericBlockGeneration(blockData, blockObjectData, blockColorSet, pane);
                break;

        }
        System.out.println("Successfully Created Blocks!");
    }

    void genericBlockGeneration(int[][][] blockData, Rectangle[][][] blockObjectData, int[][][] blockColorSet, Pane pane) {
        for (int depth = 0; depth < depthOfSquares; depth++) {
            for (int row = 0; row < numberOfSquares; row++) {
                for (int columns = 0; columns < numberOfSquares; columns++) {
                    colorAsInt = setColor(depth, row, columns, blockData);
                    randomColorSet = randNumGen.nextInt(3);
                    blockData[row][columns][depth] = colorAsInt;
                    blockColorSet[row][columns][depth] = randomColorSet;
                    blockObjectData[row][columns][depth] = new Rectangle(
                            offset + (sideLength + spacing) * columns + (pixelWidth * depth),
                            offset + (sideLength + spacing) * row + (pixelWidth * depth),
                            (sideLength - ((pixelWidth * 2) * depth)),
                            (sideLength - ((pixelWidth * 2) * depth)));
                    blockObjectData[row][columns][depth].setFill(possibleColorSet[randomColorSet][colorAsInt]);
                    pane.getChildren().add(blockObjectData[row][columns][depth]);
                }
            }
        }
    }

    void blockShineOutward(int[][][] blockData, Rectangle[][][] blockObjectData, int[][][] blockColorSet) {
        for (int depth = 0; depth < depthOfSquares; depth++) {
            for (int row = 0; row < numberOfSquares; row++) {
                for (int columns = 0; columns < numberOfSquares; columns++) {
                    if (depth == 0) {
                        blockData[row][columns][depthOfSquares] = blockData[row][columns][depth];
                    }
                    blockObjectData[row][columns][depth].setFill(possibleColorSet[blockColorSet[row][columns][depth + 1]][blockData[row][columns][depth + 1]]);
                    blockData[row][columns][depth] = blockData[row][columns][depth + 1];
                }
            }
        }
    }
}
