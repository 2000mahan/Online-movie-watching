package mediaplayer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.IOException;


public class UserGUI extends Application {
    private final StackPane root = new StackPane();
    private final String type;
    private final Button button;
    private final VBox vBox = new VBox();
    private final TextField username = new TextField();
    private final PasswordField password = new PasswordField();
    private final Label usernameLabel = new Label("Your Username");
    private final Label passwordLabel = new Label("Your Password");

    public UserGUI(String type) throws IOException {
        this.type = type;
        button = new Button(type);
    }

    @Override
    public void init() {


        if (type.equalsIgnoreCase("Sign up")) {
            vBox.setStyle("-fx-background-color: rgb(56, 98, 132);");
            usernameLabel.setStyle("-fx-text-fill: white;");
            passwordLabel.setStyle("-fx-text-fill: white;");
            username.setStyle("-fx-background-color: rgb(122, 149, 215);");
            password.setStyle("-fx-background-color: rgb(122, 149, 215);");
        } else if (type.equalsIgnoreCase("Login")) {
            vBox.setStyle("-fx-background-color: rgb(240, 113, 99);");
            usernameLabel.setStyle("-fx-text-fill: white;");
            passwordLabel.setStyle("-fx-text-fill: white;");
            username.setStyle("-fx-background-color: rgb(255, 195, 145);");
            password.setStyle("-fx-background-color: rgb(255, 195, 145);");
        }

        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(
                usernameLabel,
                username,
                passwordLabel,
                password,
                button);
        root.getChildren().addAll(vBox);

        button.setOnAction(actionEvent -> {
            Label message;
            if (type.equalsIgnoreCase("Sign up")) {
                if (username.getText().contains(",")) {
                    if (vBox.getChildren().size() == 6) {
                        vBox.getChildren().remove(5);
                    }
                    message = new Label("Username shouldn't contain , ");
                    message.setFont(new Font("default", 14));
                    message.setStyle("-fx-text-fill: red;");
                    vBox.getChildren().addAll(message);
                    System.out.println(vBox.getChildren().size());
                } else if (HomePage.users.contains(username.getText().trim())) {
                    if (vBox.getChildren().size() == 6) {
                        vBox.getChildren().remove(5);
                    }
                    message = new Label("This username already exists!");
                    message.setFont(new Font("default", 14));
                    message.setStyle("-fx-text-fill: red;");
                    vBox.getChildren().addAll(message);
                } else if (username.getText().trim().length() == 0 || password.getText().trim().length() == 0) {
                    if (vBox.getChildren().size() == 6) {
                        vBox.getChildren().remove(5);
                    }
                    message = new Label("Username and password must be filled!");
                    message.setStyle("-fx-text-fill: red;");
                    vBox.getChildren().addAll(message);
                } else {
                    if (vBox.getChildren().size() == 6) {
                        vBox.getChildren().remove(5);
                    }
                    try {
                        HomePage.users.add(username.getText().trim());
                        HomePage.userAndPass.put(username.getText().trim(), password.getText().trim());
                        HomePage.databaseWriter.write(username.getText() + "," + password.getText() + "\n");
                        HomePage.databaseWriter.flush();
                        message = new Label("You signed up successfully!");
                        message.setFont(new Font("default", 14));
                        message.setStyle("-fx-text-fill: rgb(144, 254, 145);");
                        vBox.getChildren().addAll(message);
                        Thread.sleep(2000);
                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (type.equalsIgnoreCase("Login")) {
                if (HomePage.userAndPass.containsKey(username.getText().trim())) {
                    if (HomePage.userAndPass.get(username.getText().trim()).equals(password.getText().trim())) {
                        if (vBox.getChildren().size() == 6) {
                            vBox.getChildren().remove(5);
                        }
                        message = new Label("You logged in successfully!");
                        message.setFont(new Font("default", 14));
                        message.setStyle("-fx-text-fill: rgb(144, 254, 145);");
                        vBox.getChildren().addAll(message);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    } else {
                        if (vBox.getChildren().size() == 6) {
                            vBox.getChildren().remove(5);
                        }
                        message = new Label("Incorrect password!");
                        message.setFont(new Font("default", 14));
                        message.setStyle("-fx-text-fill: red;");
                        vBox.getChildren().addAll(message);
                    }
                } else {
                    if (vBox.getChildren().size() == 6) {
                        vBox.getChildren().remove(5);
                    }
                    message = new Label("You haven't signed up yet!");
                    message.setFont(new Font("default", 14));
                    message.setStyle("-fx-text-fill: red;");
                    vBox.getChildren().addAll(message);
                }
            }
        });
    }


    public void start(Stage stage) {
        init();
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(type + " page");
    }

    public String getUsername() {
        return username.getText();
    }

}