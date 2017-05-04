package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/*
* As the following code dictates, the handling for component variables is done within the .xml files.
* This allows for modularized field editing and rapid View redeployment without sacrificing core functionality.
*/
public class MainController implements Initializable{
	
	private boolean isRatio = false;
	
	private MediaPlayer mediaPlayer;
	
	@FXML
	private MediaView mv;
	
	private String filePath;
	
	@FXML
	private Slider slider;
	
	@FXML
	private Slider volumeSlider;
	
	@FXML
	private void handleButtonAction(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)", "*.mp4");
			fileChooser.getExtensionFilters().add(filter);
			File file = fileChooser.showOpenDialog(null);
			filePath = file.toURI().toString();
			
			if(filePath != null){
			Media media = new Media(filePath);
			mediaPlayer = new MediaPlayer(media);
			
			mv.setMediaPlayer(mediaPlayer);
				DoubleProperty width = mv.fitWidthProperty();
				DoubleProperty height = mv.fitHeightProperty();
				
				width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
				height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
				mv.setPreserveRatio(false);
				
				volumeSlider.setValue(mediaPlayer.getVolume() * 100);
				volumeSlider.valueProperty().addListener(new InvalidationListener(){

					@Override
					public void invalidated(Observable arg0) {
						mediaPlayer.setVolume(volumeSlider.getValue()/100);
					}
					
				});
				
				//slider.setMax(media.getDuration().toSeconds() * 100);
				System.out.println(media.getDuration().toString());
				
				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){

					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
						slider.setValue(newValue.toSeconds());
						slider.setMax(media.getDuration().toSeconds());
						
					}
					
				});;
				
				slider.setOnMouseClicked(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent arg0) {
						mediaPlayer.seek(Duration.seconds(slider.getValue()));
						
					}
					
				});
				
			mediaPlayer.play();
			}
			
	}
	
	@FXML
	private void exit(ActionEvent event){
		System.exit(0);
	}
	
	@FXML
	private void ratio(ActionEvent event){
		if(isRatio == true){
			mv.setPreserveRatio(false);
			isRatio = false;
		}
		else{
			mv.setPreserveRatio(true);
			isRatio = true;
		}
	}
	
	@FXML
	private void pause(ActionEvent event){
		mediaPlayer.pause();
	
	}
	@FXML
	private void rewind(ActionEvent event){
		mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
	}
	
	@FXML
	private void play(ActionEvent event){
		mediaPlayer.setRate(1);
		mediaPlayer.play();
	}
	@FXML
	private void stop(ActionEvent event){
		mediaPlayer.stop();
	}
	@FXML
	private void fastForward(ActionEvent event){
		mediaPlayer.setRate(1.5);
	}
	@FXML
	private void slowDown(ActionEvent event){
		mediaPlayer.setRate(.5);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
