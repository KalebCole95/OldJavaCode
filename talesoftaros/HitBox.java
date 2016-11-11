
package talesoftaros;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HitBox {
    
    boolean isPassable;
    String name;
    int damage;
    Rectangle hitBoxRectangle;

    public HitBox(boolean isPassable, String name, int damage) {
        this.isPassable = isPassable;
        this.name = name;
        this.damage = damage;
    }
    
    public void createBackgroundHitBox(int xPosition, int yPosition, int xTiles, int yTiles) {
        this.hitBoxRectangle = new Rectangle(xPosition * GameData.pixels + 1, yPosition * GameData.pixels + 1, xTiles * GameData.pixels - 2, yTiles * GameData.pixels - 2);
        this.hitBoxRectangle.setFill(Color.TRANSPARENT);
        this.hitBoxRectangle.setStroke(Color.RED);
        this.hitBoxRectangle.strokeWidthProperty().set(2);
        this.hitBoxRectangle.setVisible(false);
    }
    
}
