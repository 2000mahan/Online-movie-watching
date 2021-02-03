package mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MediaPlayer extends Application {

    //@Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(
                "FXMLDocument.fxml"));

        Scene scene = new Scene(root, 1000, 600);

        stage.setScene(scene);
        stage.show();
    }


}
