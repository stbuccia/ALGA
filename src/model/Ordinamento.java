package model;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ordinamento extends Application{
	public static void main(String[] args) {
	
		launch(args);

	}

	@Override
	public void start(Stage firstStage) throws Exception {
		URL welcomeViewLocation;
		AnchorPane root;
		try {
			// Preparo il file della vista iniziale
			welcomeViewLocation = getClass().getResource("/views/WelcomeView.fxml");;
			System.out.println(welcomeViewLocation);
			FXMLLoader welcomeViewLoader = new FXMLLoader(welcomeViewLocation);
        		// Prendo la radice della vista
			root = (AnchorPane)(welcomeViewLoader.load());
        		// Mostro la scena
        		Scene welcomeScene = new Scene(root);
        		firstStage.setScene(welcomeScene);
        		firstStage.show();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
