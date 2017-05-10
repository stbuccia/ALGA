package model;

import java.net.URL;

import controller.Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static Stage stage;
	public static Utils u=null;
	
	public static void main(String[] args) {
		u=new Utils();
		launch(args);

	}

	@Override
	public void start(Stage firstStage) throws Exception {
		stage=firstStage;
		u.setMyScene(controller.Scenes.WELCOME);
		stage.show();
	}
}
