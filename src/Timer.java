import javafx.scene.text.Text;

public class Timer {

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
}
