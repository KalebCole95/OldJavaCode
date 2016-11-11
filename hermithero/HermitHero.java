package hermithero;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class HermitHero extends Application {
    int COUNT = 0;
    boolean collision = false;
    ArrayList WALLS = new ArrayList();
    public static String[][] floor = new String[20][20];
    public static final int speed = 200;
    final Rectangle r = new Rectangle(0, 0, 50, 50);
    final ImageView player;
    final Rectangle playerHitBox = new Rectangle(0, 0, 50, 50);
    final DoubleProperty HorizVelocity, VertVelocity;
    final LongProperty lastUpdateTime;
    final AnimationTimer rectAnimation;
    public static boolean UP = false, DOWN = false, LEFT = false, RIGHT = false, isMoving = false;
    final double minX = 0;
    final double maxX = 1000;
    final double minY = 0;
    final double maxY = 1000;

    public HermitHero() {
        this.player = new ImageView(new Image("file:Player.png", 50, 50, true, false));
        this.HorizVelocity = new SimpleDoubleProperty();
        this.VertVelocity = new SimpleDoubleProperty();
        this.lastUpdateTime = new SimpleLongProperty();
        this.rectAnimation = new AnimationTimer() {

            @Override
            public void handle(long timestamp) {
                movement();
                if (lastUpdateTime.get() > 0) {
                    //tileMovement();
                    normalMovement(timestamp);

                }
                lastUpdateTime.set(timestamp);
            }
        };
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1000, 1000);
        Rectangle w = new Rectangle(0, 0, 1000, 1000);
        Image grass = new Image("file:Grass.png", 50, 50, true, false);
        Image flower = new Image("file:Flower.png", 50, 50, true, false);
        ImageView[][] iv1 = new ImageView[20][20];
        w.setFill(Color.BLUE);
        root.getChildren().add(w);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (Math.random() < .1 && i != 0) {
                    iv1[i][j] = new ImageView(flower);
                    WALLS.add(new Rectangle(j * 50, i * 50, 50, 50));
                    ((Rectangle) WALLS.get(WALLS.size()-1)).setVisible(true);
                    root.getChildren().add((Rectangle) WALLS.get(WALLS.size() - 1));
                    floor[i][j] = "flower";
                } else {
                    iv1[i][j] = new ImageView(grass);
                    floor[i][j] = "grass";
                }
                iv1[i][j].relocate(i * 50, j * 50);
                root.getChildren().add(iv1[i][j]);
            }
        }

        r.setFill(Color.RED);
        playerHitBox.setVisible(false);
        root.getChildren().add(player);
        root.getChildren().add(playerHitBox);
        rectAnimation.start();

        primaryStage.setTitle(
                "Version 1.0.0");
        primaryStage.setScene(scene);

        primaryStage.show();

        scene.setOnKeyPressed(event
                -> {
                    if (event.getCode() == KeyCode.W) {
                        UP = true;
                    } else if (event.getCode() == KeyCode.S) {
                        DOWN = true;
                    } else if (event.getCode() == KeyCode.A) {
                        LEFT = true;
                    } else if (event.getCode() == KeyCode.D) {
                        RIGHT = true;
                    } else if (event.getCode() == KeyCode.I) {
                        player.setVisible(false);
                    }
                }
        );

        scene.setOnKeyReleased(event
                -> {
                    if (event.getCode() == KeyCode.W) {
                        UP = false;
                    } else if (event.getCode() == KeyCode.S) {
                        DOWN = false;
                    } else if (event.getCode() == KeyCode.A) {
                        LEFT = false;
                    } else if (event.getCode() == KeyCode.D) {
                        RIGHT = false;
                    } else if (event.getCode() == KeyCode.I) {
                        player.setVisible(true);
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void movement() {
        System.out.println(COUNT);
        COUNT++;
        if (RIGHT && LEFT) {
            HorizVelocity.set(0);
        } else if (RIGHT) {
            HorizVelocity.set(speed);
        } else if (LEFT) {
            HorizVelocity.set(-speed);
        } else {
            HorizVelocity.set(0);
        }
        if (UP && DOWN) {
            VertVelocity.set(0);
        } else if (UP) {
            VertVelocity.set(-speed);
        } else if (DOWN) {
            VertVelocity.set(speed);
        } else {
            VertVelocity.set(0);
        }
    }

    public void normalMovement(long timestamp) {
        final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0;
        final double deltaX = elapsedSeconds * HorizVelocity.get();
        final double deltaY = elapsedSeconds * VertVelocity.get();
        final double oldX = player.getTranslateX();
        final double oldY = player.getTranslateY();
        final double newX = Math.max(minX, Math.min(maxX - 50, oldX + deltaX));
        final double newY = Math.max(minY, Math.min(maxY - 50, oldY + deltaY));
        isValidMovement();
        if (!collision) {
            player.setTranslateX(newX);
            player.setTranslateY(newY);
            playerHitBox.setTranslateX(newX);
            playerHitBox.setTranslateX(newX);
        }
    }

    public void tileMovement() {
        if (RIGHT) {
            double temp = player.getTranslateX();
            RIGHT = false;
            while (player.getTranslateX() < temp + 50) {
                player.setTranslateX(player.getTranslateX() + .01);
            }
        }
    }

    public void isValidMovement() {
        Shape intersect;
        for (int i = 0; i < WALLS.size(); i++) {
            intersect = Shape.intersect(playerHitBox, (Rectangle) WALLS.get(i));
            if (intersect.getBoundsInLocal().getWidth() != -1 || intersect.getBoundsInLocal().getHeight() != -1) {
                collision = true;
            }
        }
        collision = false;
    }
}
