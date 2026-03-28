import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Renderer extends AnimationTimer{
    
    private GraphicsContext graphicsContext;
    private ArrayList<GameObject> gameObjects;
    public double canvasWidth, canvasHeight;

    public Renderer(GraphicsContext graphicsContext, 
        ArrayList<GameObject> gameObjects, double canvasWidth, 
        double canvasHeight){
        this.graphicsContext = graphicsContext;
        this.gameObjects = gameObjects;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void handle(long now){
        // Clear the canvas.
        graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
        for (GameObject gameObject:gameObjects){
            gameObject.render(graphicsContext);
        }
    }
}
