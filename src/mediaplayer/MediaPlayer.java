package mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MediaPlayer extends Application {
   static File database = new File("Users.txt");
   static FileWriter databaseWriter;

    static {
        try {
            databaseWriter = new FileWriter(database.getPath(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource(
                "FXMLDocument.fxml"));

        Scene scene = new Scene(root,1000,600);

        stage.setScene(scene);
//        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
