package mediaplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLDocumentController implements Initializable {
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;
    @FXML
    private Slider slider; 
    @FXML
    private Slider volumeSlider;
    private String filePath;
    @FXML
    public void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Select a Media File (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        if(filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            //DoubleProperty width = mediaView.fitWidthProperty();
            //DoubleProperty height = mediaView.fitHeightProperty();
            
           //width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
           //height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
          
            
            //MediaView mv = new MediaView(videoPlayer);
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);

        mediaView.setViewport(Rectangle2D.EMPTY);
        mediaPlayer.play();
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                        @Override
                        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                            slider.setValue(newValue.toSeconds());
                        }
                    });
        slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            mediaPlayer.seek(Duration.seconds(slider.getValue()));
                        }
                    });
        
        volumeSlider.setValue(mediaPlayer.getVolume()*100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }
            });
        
 //Slider speedSlider = initializeSlider();

        }
    }

//  public   MediaPlayer getMediaplayer(){
//       return mediaPlayer;
//   }

 @FXML
    private void playVideo(ActionEvent event){

        mediaPlayer.play();
    }
    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(.75);
    }
    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(.5);
    }
    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
