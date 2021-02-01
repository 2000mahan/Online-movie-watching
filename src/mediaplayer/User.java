package mediaplayer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class User extends Application {
    private StackPane root = new StackPane();
    private Stage stage;
    private String type;

    public User(String type) throws IOException {
        this.type = type;
    }

    @Override
    public void init() {
        Button button = new Button(type);
        VBox vBox = new VBox();
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(
                new Label("Your Username"),
                username,
                new Label("Your Password"),
                password,
                button);
        root.getChildren().addAll(vBox);

        button.setOnAction(actionEvent -> {
            System.out.println("here");
            if (type.equalsIgnoreCase("Sign up")) {
                if (username.getText().contains(",")){

                }
                try {
                    MediaPlayer.databaseWriter.write(username.getText() + "," + password.getText() + "\n");
                    MediaPlayer.databaseWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (type.equalsIgnoreCase("Login")){

            }
            if (stage != null) {
                stage.requestFocus();
            }
//            stage = new Stage();
//            StackPane stackPane = new StackPane();
//            stage.setScene(new Scene(stackPane, 200, 200));
//            stage.show();
        });
    }

    // @Override
    public void start(Stage stage) {
        init();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(type + " page");
        stage.setAlwaysOnTop(true);
    }

}