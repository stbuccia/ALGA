package controller;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Utils extends Object {

	public void setMyScene(Scenes myscene) {
		AnchorPane root;
		try {
			URL location = this.getScenePath(myscene);
			FXMLLoader welcomeViewLoader = new FXMLLoader(location);
			root = (AnchorPane) (welcomeViewLoader.load());
			Scene S = new Scene(root);
			model.Main.stage.setResizable(false);
			model.Main.stage.setScene(S);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public URL getScenePath(Scenes myscene) {
		URL out = null;
		switch (myscene) {
		case WELCOME:
			out = this.getClass().getResource(
					"/views/WelcomeView.fxml");
			model.Main.stage.setWidth(650);
			model.Main.stage.setHeight(600);
			break;
		case QSORT:
			out = this.getClass().getResource(
					"/views/QSortView.fxml");
			model.Main.stage.setWidth(750);
			model.Main.stage.setHeight(735);
			break;
		case FILE:
			out = this.getClass().getResource(
					"/views/FileView.fxml");
			model.Main.stage.setWidth(750);
			model.Main.stage.setHeight(735);
			break;
		}
		return out;
	}
	
	public Color fromPalette(Palette p) {
		switch(p){
		case PIVOT:
			return Color.web("#0092C3");
		case SHORTER:
			return Color.web("#E0E0E0");
		case SWITCHED:
			return Color.web("#4ACDD0");
		case CURRENT:
			return Color.web("#00D288");
		default:
			return Color.WHITE;
		}

	}
}
