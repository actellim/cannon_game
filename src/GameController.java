import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class GameController {
    // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/Canvas.html
    @FXML private Canvas gameCanvas;


    // Due to the initial scaffolding a seperate
    // renderer class is beyond scope. :(
    public void initialize() {
        // Instantiate variables that refer to GUI components.
        double canvasWidth = gameCanvas.getWidth();
        double canvasHeight = gameCanvas.getHeight();
        // Instantiate the blocker first so we can pass the speed.
        Blocker blocker = new Blocker(canvasWidth, canvasHeight);
        // Create a List of targets.
        // ref: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
        ArrayList<Target> targets = new ArrayList<Target>();
        for (int i = 0; i < 9; i++){
            Target target = new Target(
                // proper SoC we can probably put most of this in the constructor
                i, // blocker number
                blocker.getSpeed(),
                canvasWidth,
                canvasHeight); //canvas height
            targets.add(target);
        }
        CollisionDetector collision = new CollisionDetector(
            canvasHeight, canvasWidth);
        Timer t = new Timer();

        // Get the canvas graphics writer.
        // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/GraphicsContext.html
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        

        // Start an animation timer
        // ref: https://openjfx.io/javadoc/24/javafx.graphics/javafx/animation/AnimationTimer.html
        AnimationTimer timer = new AnimationTimer() {
            // Init the previous time
            long previousTime = System.nanoTime();


            // Called every frame after timer.start() is called.
            // All rendering happens INSIDE this method.
            @Override
            public void handle(long now) {
                // Get the elapsed time in seconds.
                double elapsedTime = (now - previousTime) / 1000000000.0;
                previousTime = now;
                // Update the game time.
                t.removeTime(elapsedTime);
                if (t.getTime() < 0) {
                    // Do something? Need alerts and game state here.
                }
                
                // Update the location of the blocker
                if (collision.checkCollision(blocker))
                    blocker.setSpeed(blocker.getSpeed()*-1);
                blocker.setY(blocker.getY() - 
                    (elapsedTime*blocker.getSpeed()));

                // Update the location of the targets.
                for (int i = 0; i < targets.size(); i++){
                    Target update = targets.get(i);
                    // If targets collide, reverse them.
                    if (collision.checkCollision(update))
                        update.setSpeed(update.getSpeed()*-1);
                    update.setY(update.getY()-
                        (elapsedTime*update.getSpeed()));
                    targets.set(i, update);
                }
                
                // Clear the canvas.
                gc.clearRect(0, 0, gameCanvas.getWidth(), 
                    gameCanvas.getHeight());
                
                // Draw the blocker
                gc.setFill(Paint.valueOf("BLACK"));
                gc.fillRect(blocker.getX(), blocker.getY(),
                    blocker.getWidth(), blocker.getHeight());
                
                // Re-draw the targets.
                gc.setFill(Paint.valueOf("ORANGE"));
                for (int i = 0; i < targets.size(); i++){
                    Target draw = targets.get(i);
                    gc.fillRect(draw.getX(), draw.getY(), 
                        draw.getWidth(), draw.getHeight());
                }
                
                // Render the time left.
                gc.setFill(Paint.valueOf("BLACK"));
                if (t.getTime() > 0)
                    gc.fillText("Time Remaining: " + t.toString(), 10, 20);
            }
        };

    // Render frames.
    timer.start();
    }

    /* Tabula Rasa 

    private Rectangle[] targets = new Rectangle[9];
    private double[] speeds = new double[9];

    private Rectangle blocker;
    private double blockerSpeed;
    
    //N: private AnimationTimer timer is to prevent issues with timer.stop(); in the timer section
    private AnimationTimer timer;
    private Timeline countdown;
    
    @Override
    public void start(Stage stage) {
    	
        stage.setTitle("Cannon Game");
        stage.setScene(GameScene(stage));
        stage.show();

    }

/// Game Scene ----------------------------------------------------------------------------
    private Scene GameScene(Stage stage) {

        Pane root = new Pane();
        root.setStyle("-fx-background-color: azure;");
        return null;
    }
    
            timer = new AnimationTimer() {

        	// runs every frame
            @Override
            public void handle(long now) {

                // loop moves all 9 targets
                for (int i = 0; i < 9; i++) {

                	// get current Target
                    Rectangle t = targets[i];

                    // move Target vertically
                    t.setY(t.getY() + speeds[i]);

                    // boundary check - reverse at top or bottom
                    if (t.getY() <= 0 || t.getY() >= HEIGHT - t.getHeight()) {
                    	// Reverse Speed 
                        speeds[i] *= -1;
                    }
                }

                // Move blocker
                blocker.setY(blocker.getY() + blockerSpeed);

                if (blocker.getY() <= 0 || blocker.getY() >= HEIGHT - blocker.getHeight()) {
                    blockerSpeed *= -1;
                }
            }
        };

        // starts the AnimationTimer
        timer.start();

        return new Scene(root, WIDTH, HEIGHT);
    }*/
}
