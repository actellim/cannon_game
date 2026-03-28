import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectScreen extends Alert{

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
    }
}
