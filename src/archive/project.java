package archive;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class project extends Application {

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

        Random rand = new Random();
        
// Timer ----------------------------------------------------------------------------------

        // 10-Second Countdown Variable
        int[] timeRemaining = {10};
        
        // Display timer
        Text timerCount = new Text();
        timerCount.setText("Time Remaining: 10");
        timerCount.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        timerCount.setX(10);
        timerCount.setY(20);
        
        root.getChildren().add(timerCount);
        
        // Create 10-Second Timer        
        countdown = new Timeline(
        	    new KeyFrame(Duration.seconds(1), e -> {
        	    	// Note: EventHandler<ActionEvent>() == (ActionEvent e) -> == e ->

        	        timeRemaining[0]--;

        	        timerCount.setText("Time Remaining: " + timeRemaining[0]);

        	        // Check if time is up
        	        if (timeRemaining[0] <= 0) {

        	            timer.stop();      // stops movement of everything
        	            countdown.stop();  // stops timer
        	            
        	            // Switch scene to GameOver Scene
        	            stage.setScene(GameOverScene(stage));
        	        }

        	    })
        	);
        
        // Run KeyFrame 10 times
        countdown.setCycleCount(10);
        countdown.play();
    
// Nine Targets ---------------------------------------------------------------------------
        
        for (int i = 0; i < 9; i++) {

            Rectangle target = new Rectangle(20, 60);

            target.setX(500 + (i * 40));
            target.setY(rand.nextInt(HEIGHT - 60));
            target.setFill(Color.ORANGE); 

            // Each target gets a different speed
            speeds[i] = 1 + rand.nextDouble() * 3;

            targets[i] = target;

            root.getChildren().add(target);
        }

// Blocker --------------------------------------------------------------------------------
        
        blocker = new Rectangle(20, 120);

        blocker.setX(400);
        blocker.setY(rand.nextInt(HEIGHT - 150));

        blockerSpeed = 2.5;

        root.getChildren().add(blocker);


// Animation Timer (update movement continuously) -----------------------------------------
        
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
    
/// Game Over Scene ----------------------------------------------------------------------
    private Scene GameOverScene(Stage stage) {
    	
        VBox root = new VBox(20); 
        
        root.setStyle("-fx-background-color: dimgrey;");
        root.setAlignment(Pos.CENTER);
        
        Text gameOver = new Text("GAME OVER");
        gameOver.setFill(Color.WHITE);
        gameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 45));
        
        root.getChildren().add(gameOver);
        
// Retry Button -------------------------------------------------------
        Button retryButton = new Button();
        retryButton.setText("Play Again?");
        
        retryButton.setPrefWidth(150);
        
        retryButton.setOnAction(new EventHandler<ActionEvent>() {

        	        @Override
        	        public void handle(ActionEvent event) {

        	            stage.setScene(GameScene(stage));
        	        }
        	    }
        	);
        
        root.getChildren().add(retryButton);
        
// Close Game (Exist) Button -----------------------------------------
        Button exitButton = new Button("Exit");
        
        exitButton.setPrefWidth(150);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    stage.close();
                }
            }
        );
        
        root.getChildren().add(exitButton);
            
    	
        return new Scene(root, WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        launch();
    }
}