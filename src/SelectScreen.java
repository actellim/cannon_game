import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

// ref: https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html
public class SelectScreen extends Alert{

    public SelectScreen(){
        // Aesthetically this needs to be improved.
        super(AlertType.CONFIRMATION, "Wanna play a game?");
    }
    
    public boolean promptUser(){
        Optional<ButtonType> result = showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
            return true;
        else
            return false;
    }

/* Tabula Rasa
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
    } */
}
