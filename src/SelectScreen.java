import javafx.scene.control.Alert;

// ref: https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html
public class SelectScreen extends Alert{

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
