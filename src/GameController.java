import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

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
    }

    public static void main(String[] args) {
        launch();
    }
}
