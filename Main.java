package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
* Most of the code comes from https://www.youtube.com/watch?v=7Gdxl2045l8&t=2711s
* 
*/

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
