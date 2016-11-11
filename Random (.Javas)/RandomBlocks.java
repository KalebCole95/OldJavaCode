package randomblocks;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class RandomBlocks extends Application {

    @Override
    public void start(Stage primaryStage) {
        int offset = 10, spacing = 5, sideLength = 25, number = 25;
        int numColors = 7, randomInt, randomColorSet;
        int startX = offset, startY = offset;
        Color colorSquare;
        Random randomGenerator = new Random();
        Color[] blueColorSet = {Color.LIGHTCYAN, Color.CYAN, Color.DARKCYAN, Color.LIGHTBLUE, Color.BLUE, Color.DARKBLUE, Color.WHITE};
        Color[] redColorSet = {Color.PINK, Color.RED, Color.DARKRED, Color.CRIMSON, Color.MAROON, Color.DEEPPINK, Color.WHITE};
        Color[] greenColorSet = {Color.LIGHTGREEN, Color.GREEN, Color.DARKGREEN, Color.FORESTGREEN, Color.LAWNGREEN, Color.MEDIUMSEAGREEN, Color.WHITE};
        Color[] blackColorSet = {Color.LIGHTGRAY, Color.GRAY, Color.DARKGRAY, Color.BLACK, Color.SLATEGRAY, Color.WHITESMOKE, Color.WHITE};
        
        Color[][] possibleColorSet = {blueColorSet, redColorSet, greenColorSet, blackColorSet};
        randomColorSet = randomGenerator.nextInt(4);
        
        Pane pane = new Pane();
        for (int numSquares = 0; numSquares < 4; numSquares++) {
            for (int row = 0; row < number; row++) {
                for (int cols = 0; cols < number; cols++) {
                    randomInt = randomGenerator.nextInt(numColors);
                    colorSquare = possibleColorSet[randomColorSet][randomInt];
                    Rectangle r = new Rectangle(
                            startX + (sideLength + spacing) * cols + (3 * numSquares),
                            startY + (sideLength + spacing) * row + (3 * numSquares),
                            (sideLength - (6 * numSquares)),
                            (sideLength - (6 * numSquares)));
                    r.setFill(colorSquare);
                    pane.getChildren().add(r);
                }
            }
        }
        Scene scene = new Scene(pane, (offset * 2 + (sideLength + spacing) * number - spacing), (offset * 2 + (sideLength + spacing) * number) - spacing);
        primaryStage.setTitle("Kaleb's Random Block Generator!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
