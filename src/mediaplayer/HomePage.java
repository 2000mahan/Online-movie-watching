package mediaplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends Application {
    private final StackPane root = new StackPane();
    static File database = new File("Users.txt");
    static FileWriter databaseWriter;
    static ArrayList<String> users = new ArrayList<>();
    static HashMap<String, String> userAndPass = new HashMap<>();
    private MediaPlayer mediaPlayer;
    private boolean playing = false;
    private User user = null;


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

        signup_button.setOnMouseClicked(actionEvent -> {
            UserGUI signup = null;
            try {
                signup = new UserGUI("Sign up");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert signup != null;
            signup.start(new Stage());
            user = new User(signup.getUsername());
        });


        login_button.setOnMouseClicked(actionEvent -> {
            UserGUI login = null;
            try {
                login = new UserGUI("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert login != null;
            login.start(new Stage());
            user = new User(login.getUsername());
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
        BackgroundImage backgroundImage1 = new BackgroundImage(new Image(getClass().getResource("pics/inception.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background1 = new Background(backgroundImage1);
        movie1.setBackground(background1);
        movie1.setPrefWidth(350);
        movie1.setPrefHeight(350);
        root.getChildren().add(movie1);
        StackPane.setAlignment(movie1, Pos.CENTER_LEFT);
        StackPane.setMargin(movie1, new Insets(0, 0, 0, 10));


        movie1.setOnAction(actionEvent -> {
            if (user != null) {
                playSelected("movies/Inception.mp4");
            }
        });

        Button movie2 = new Button("");
        movie2.setShape(r);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image(getClass().getResource("pics/WoW.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundImage2);
        movie2.setBackground(background2);
        movie2.setPrefWidth(350);
        movie2.setPrefHeight(350);
        root.getChildren().add(movie2);
        StackPane.setAlignment(movie2, Pos.CENTER_LEFT);
        StackPane.setMargin(movie2, new Insets(0, 0, 0, 375));

        movie2.setOnAction(actionEvent -> {
            if (user != null) {
                playSelected("movies/WoW.mp4");
            }
        });

        Button movie3 = new Button("");
        movie3.setShape(r);
        BackgroundImage backgroundImage3 = new BackgroundImage(new Image(getClass().getResource("pics/peaky.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background3 = new Background(backgroundImage3);
        movie3.setBackground(background3);
        movie3.setPrefWidth(350);
        movie3.setPrefHeight(350);
        root.getChildren().add(movie3);
        StackPane.setAlignment(movie3, Pos.CENTER_LEFT);
        StackPane.setMargin(movie3, new Insets(0, 0, 0, 740));

        movie3.setOnAction(actionEvent -> {
            if (user != null) {
                playSelected("movies/Peaky.mp4");
            }
        });
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

    public void playSelected(String path) {
        if (playing) {
            mediaPlayer.stop();
            playing = false;
        }
        Stage primaryStage = new Stage();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                playing = false;
                mediaPlayer.stop();
                primaryStage.close();
            }
        });
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 1100, 600);

// create media player
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        playing = true;
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);
        // create mediaView and add media player to the viewer
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();

        primaryStage.show();
    }
}