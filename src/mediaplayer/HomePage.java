package mediaplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends Application {
    private final StackPane root = new StackPane();
    static File database = new File("Users.txt");
    static FileWriter databaseWriter;
    static ArrayList<String> users = new ArrayList<>();
    static HashMap<String, String> userAndPass = new HashMap<>();

    static {
        try {
            databaseWriter = new FileWriter(database.getPath(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractUsers() throws IOException {
        FileReader fr = new FileReader(database);   //reads the file
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String line;
        while ((line = br.readLine()) != null) {
            users.add(line.trim().split(",")[0].trim());
            userAndPass.put(line.trim().split(",")[0].trim(), line.trim().split(",")[1].trim());
        }
    }

    public void init() {
        // login and sign up
        Button login_button = new Button("Login");
        Button signup_button = new Button("Sign up");
        // Button tmp = new Button("Sign up");

        login_button.setPrefWidth(100);
        signup_button.setPrefWidth(100);

        login_button.setStyle("-fx-background-color: white");
        signup_button.setStyle("-fx-background-color: rgb(122, 158, 219);");

        root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, rgb(56, 98, 132) ,rgb(240, 113, 99));");
        root.getChildren().addAll(login_button, signup_button);
        StackPane.setAlignment(login_button, Pos.TOP_LEFT);
        StackPane.setAlignment(signup_button, Pos.TOP_LEFT);
        StackPane.setMargin(signup_button, new Insets(15, 10, 10, 30));
        StackPane.setMargin(login_button, new Insets(15, 10, 10, 150));

        signup_button.setOnAction(actionEvent -> {
            UserGUI signup = null;
            try {
                signup = new UserGUI("Sign up");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert signup != null;
            signup.start(new Stage());
        });


        login_button.setOnAction(actionEvent -> {
            UserGUI login = null;
            try {
                login = new UserGUI("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert login != null;
            login.start(new Stage());
            Label welcome = new Label("Welcome dear" + login.getUsername());
            welcome.setPadding(new Insets(20, 20, 20, 20));
            welcome.setStyle("-fx-text-fill: blue;");
            root.getChildren().add(welcome);
            StackPane.setAlignment(welcome, Pos.TOP_LEFT);
        });


        // movies
        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(100);
        r.setHeight(100);
        r.setArcWidth(10);
        r.setArcHeight(10);
        Button movie1 = new Button("");
        movie1.setShape(r);
        BackgroundImage backgroundImage1 = new BackgroundImage(new Image(getClass().getResource("inception.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background1 = new Background(backgroundImage1);
        movie1.setBackground(background1);
        movie1.setPrefWidth(350);
        movie1.setPrefHeight(350);
        root.getChildren().add(movie1);
        StackPane.setAlignment(movie1, Pos.CENTER_LEFT);
        StackPane.setMargin(movie1, new Insets(0, 0, 0, 10));


        Button movie2 = new Button("");
        movie2.setShape(r);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image(getClass().getResource("WoW.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundImage2);
        movie2.setBackground(background2);
        movie2.setPrefWidth(350);
        movie2.setPrefHeight(350);
        root.getChildren().add(movie2);
        StackPane.setAlignment(movie2, Pos.CENTER_LEFT);
        StackPane.setMargin(movie2, new Insets(0, 0, 0, 375));


        Button movie3 = new Button("");
        movie3.setShape(r);
        BackgroundImage backgroundImage3 = new BackgroundImage(new Image(getClass().getResource("peaky.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background3 = new Background(backgroundImage3);
        movie3.setBackground(background3);
        movie3.setPrefWidth(350);
        movie3.setPrefHeight(350);
        root.getChildren().add(movie3);
        StackPane.setAlignment(movie3, Pos.CENTER_LEFT);
        StackPane.setMargin(movie3, new Insets(0, 0, 0, 740));

    }


    // @Override
    public void start(Stage stage) throws IOException {
        extractUsers();
        init();
        Scene scene = new Scene(root, 1100, 630);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Home page");
    }
}