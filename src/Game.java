import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {


    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(
            getClass().getResource("Game.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cannon Game");
        stage.setScene(scene);
        //Prevent the user from messing up our math
        stage.setResizable(false);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
