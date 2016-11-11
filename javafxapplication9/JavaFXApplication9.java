
package javafxapplication9;
import javafx.animation.FadeTransition;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import javafx.util.Duration;

public class JavaFXApplication9 extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      ImageView iv = new ImageView();
      Image image = new Image("file:image/TEST.png");
      iv.setImage(image);

      FadeTransition ft = new FadeTransition();
      ft.setNode(iv);
      ft.setDuration(new Duration(2000));
      ft.setFromValue(1.0);
      ft.setToValue(0.0);
      ft.setCycleCount(1);
      ft.setAutoReverse(true);
       for (int i = 0; i < 100; i++) {
           ft.play();
           System.out.println(i);
       }
      iv.setOnMouseClicked(me -> ft.play());

      Group root = new Group();
      root.getChildren().add(iv);
      Scene scene = new Scene(root, image.getWidth(), image.getHeight());

      primaryStage.setTitle("FadeTransition Demo");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}