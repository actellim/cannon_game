import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController {
    // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/Canvas.html
    @FXML private Canvas gameCanvas;
    // Needs to be instantiated here to prevent scope issues
    // ref: https://openjfx.io/javadoc/24/javafx.graphics/javafx/animation/AnimationTimer.html
    // private AnimationTimer timer;


    public void initialize() {
        // Instantiate variables that refer to GUI components.
        double canvasWidth = gameCanvas.getWidth();
        double canvasHeight = gameCanvas.getHeight();
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        ArrayList<PotentialTarget> blockerAndTargets = new ArrayList<PotentialTarget>();
        // Instantiate the blocker first so we can pass the speed.
        Blocker blocker = new Blocker(canvasWidth, canvasHeight);
        gameObjects.add(blocker);
        blockerAndTargets.add(blocker);
        // Create a List of targets.
        // ref: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
        ArrayList<Target> targets = new ArrayList<Target>();
        for (int i = 0; i < 9; i++){
            Target target = new Target(
                i,                  // Target number
                blocker.getSpeed(), // Ensure no speed ties with the blocker.
                canvasWidth,
                canvasHeight);
            gameObjects.add(target);
            blockerAndTargets.add(target);
            targets.add(target);
        }
        // CollisionDetector collision = new CollisionDetector(canvasWidth, canvasHeight);
        // Create the timer
        Timer t = new Timer(); // Game duration hardcoded to 10s.
        gameObjects.add(t);
        // Get the canvas graphics writer.
        // ref: https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/canvas/GraphicsContext.html
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Renderer r = new Renderer(gc, gameObjects, canvasWidth, canvasHeight);
        
        
        // Start the handle(long now) method of 
        // the GameObjects.
        for (GameObject gameObject : gameObjects){
            gameObject.start();
        }
       
        // Render the objects
        r.start();
        
    }
}
        // From here each game object updates its position using handle(long now)
        // The renderer holds the gc and the list of renderables, and draws them every frame.

        // Start game alert
        // Due to scoping issue with AnimationTimer's previousTime
        // AnimationTimer() needs to be declared inside of the button.
        // This is a clear SoC issue. 
        /*Alert gameStart = new Alert(AlertType.CONFIRMATION, "Wanna play a game?");
        Optional<ButtonType> result = gameStart.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){ */

        /* Deprecated 
            // Declare the animation timer
            // For future SoC: This could be refactored into Renderer class?
            timer = new AnimationTimer() {
                // Init the previous time
                long previousTime = System.nanoTime();

                // Called every frame after timer.start() is called.
                // All rendering happens INSIDE this method.
                @Override
                public void handle(long now) {
                    // Get the elapsed time in seconds.
                    double elapsedTime = (now - previousTime) / 1000000000.0;
                    previousTime = now;
     
                    // Start the handle(long now) method of 
                    // the GameObjects.
                    for (GameObject gameObject : gameObjects){
                        gameObject.start();
                    }

                    // Reverse the blocker if it's hitting a wall
                    if (collision.checkCollision(blocker)){
                        blocker.setSpeed(blocker.getSpeed()*-1);
                    }

                    // Update the location of the targets.
                    for (Target target : targets){
                        // If targets collide, reverse them.
                        if (collision.checkCollision(target)){
                            target.setSpeed(target.getSpeed()*-1);
                        }
                        target.setY(
                            target.getY()-(elapsedTime*target.getSpeed())
                        );
                    }
     
                    // Draw the blocker.
                    gc.setFill(Paint.valueOf("BLACK"));
                    gc.fillRect(blocker.getX(), blocker.getY(),
                        blocker.getWidth(), blocker.getHeight());
     
                    // Draw the targets.
                    gc.setFill(Paint.valueOf("ORANGE"));
                    for (int i = 0; i < targets.size(); i++){
                        Target draw = targets.get(i);
                        gc.fillRect(draw.getX(), draw.getY(), 
                            draw.getWidth(), draw.getHeight());
                    }
     
                    // If there's no time left we're out of time.
                    // We progress to the next state
                    if (t.getTime() <= 0) {
                        timer.stop(); // Basically break?
                    }
                }
            }; // This is all one giant declaration.
            
            // Render frames; basically timer.start() = while true
            timer.start();
        //}
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
//}
