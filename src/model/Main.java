package model;

import controller.Utils;
import controller.QSortDrawer;
import controller.QSortViewController;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class Main extends Application {

	// La maggior parte di queste cose sono inizializzati dai controller
	public static Stage stage;
	public static Utils u = null;
	public static Algoritmo a = null;
	public static Input i = null;
	public static QSortDrawer qDrawer = null;
	public static Service<Void> backgroundSorter;

	public static void main(String[] args) {

		u = new Utils();
		launch(args);

	}

	@Override
	public void start(Stage firstStage) throws Exception {
		stage = firstStage;
		u.setMyScene(controller.Scenes.WELCOME);
		stage.show();
	}
}
