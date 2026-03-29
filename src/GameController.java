import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class GameController implements GameListener{
    // ref: https://openjfx.io/javadoc/24/javafx.graphics/javafx/animation/AnimationTimer.html
    // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/Canvas.html
    @FXML private Canvas gameCanvas;
    private GraphicsContext gc;
    private Blocker blocker;
    private Cannon cannon;
    private ArrayList<Target> targets;
    private ArrayList<PotentialTarget> blockerAndTargets;
    private ArrayList<GameObject> gameObjects;
    private Renderer r;
    private double canvasWidth, canvasHeight, blockerSpeed;
    private Timer t;
    private SelectScreen popup;

    public void initialize() {
        // Instantiate variables fresh for every game.
        canvasWidth = gameCanvas.getWidth();
        canvasHeight = gameCanvas.getHeight();
        targets = new ArrayList<Target>();
        blockerAndTargets = new ArrayList<PotentialTarget>();
        gameObjects = new ArrayList<GameObject>();
        // Instantiate the blocker first so we can pass the speed.
        blocker = new Blocker(canvasWidth, canvasHeight);
        gameObjects.add(blocker);
        blockerAndTargets.add(blocker);
        // Grab the speed once instead of in every loop
        blockerSpeed = blocker.getSpeed();
        // Create a List of targets.
        // ref: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
        targets = new ArrayList<Target>();
        for (int i = 0; i < 9; i++){
            Target target = new Target(
                i,                  // Target number
                blockerSpeed,       // Ensure no speed ties with the blocker.
                canvasWidth,
                canvasHeight);
            targets.add(target);
            blockerAndTargets.add(target);
            gameObjects.add(target);
        }
        t = new Timer(this); // Initial duration hardcoded to 10s.
        gameObjects.add(t);
        cannon = new Cannon(canvasHeight);
        gameObjects.add(cannon);
        // Get the canvas graphics writer.
        // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/GraphicsContext.html
        gc = gameCanvas.getGraphicsContext2D();
        // Needs to come after we get the context from the canvas!
        r = new Renderer(gc, gameObjects, canvasWidth, canvasHeight);
 
        // ref: https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html
        popup = new SelectScreen();
        if (popup.promptUser()){ 
            // Start the handle(long now) method of the GameObjects.
            for (GameObject gameObject : gameObjects){
                gameObject.start(); // non-blocking!
            }
            // Start rendering the objects. **Happens in another thread, non-blocking.**
            r.start();
        }
        else
            // Exit gracefully -- need to use Platform.exit() here to avoid
            // potential memory leaks.
            // https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Platform.html
            Platform.exit();
 
    }
 
    
    // Interface necessary to access game over method.
    @Override
    public void gameOver(){
        // Stop the presses -- they're out of time!
        for (GameObject gameObject : gameObjects){
            gameObject.stop();
            r.stop();            
        }
        // Naievely call init again to see what happens... blocked the thread.
        // ref: https://openjfx.io/javadoc/17/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
        // ref: https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                initialize();
            }
        });
    }
    
    @FXML
    private void canvasMouseClicked(MouseEvent e){
        double mouseX = e.getX();
        double mouseY = e.getY();
        cannon.calcNewTheta(mouseX, mouseY);
    }
}