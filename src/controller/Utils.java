package controller;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Utils extends Object{
	
	public void setMyScene(Scenes myscene){
		AnchorPane root;
		try{
			URL location=this.getScenePath(myscene);
			FXMLLoader welcomeViewLoader = new FXMLLoader(location);
			root = (AnchorPane)(welcomeViewLoader.load());
			Scene S = new Scene(root);
			model.Main.stage.setWidth(700);
			model.Main.stage.setHeight(720);
			model.Main.stage.setResizable(false);
			model.Main.stage.setScene(S);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public URL getScenePath(Scenes myscene){
		URL out= null;
		switch(myscene){
		case WELCOME:
			out= this.getClass().getResource("/views/WelcomeView.fxml");
			break;
		case QSORT:
			out= this.getClass().getResource("/views/QSortView.fxml");
			break;
		case FILE:
			out= this.getClass().getResource("/views/FileView.fxml");
			break;
		}
		return out;
	}
}

