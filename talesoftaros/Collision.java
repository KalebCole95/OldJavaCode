
package talesoftaros;

import java.util.Iterator;
import javafx.scene.shape.Shape;

public class Collision {
    
    public static boolean playerCollisionDetection(Shape playerShape){
        for (Iterator<HitBox> IT = GameData.areaSet.get(GameData.currentArea).hitBoxList.iterator(); IT.hasNext();) {
            HitBox currentTest = IT.next();
            if(currentTest.hitBoxRectangle != playerShape){
                Shape intersection = Shape.intersect(currentTest.hitBoxRectangle, playerShape);
                if(intersection.getBoundsInLocal().getWidth() > 0 || intersection.getBoundsInLocal().getHeight() > 0){
                    switch (currentTest.name){
                        case "door":
                            break;
                        case "wall":
                            return true;
                        case "pickup":
                            break;
                        default:
                            System.out.println("ERROR: NO SUCH HITBOX FOUND");
                    }
                }
            }
        }
        return false;
    }
}
