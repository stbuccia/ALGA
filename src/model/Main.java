package model;

import controller.Utils;
import controller.QSortDrawer;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.stage.Stage;

public class Main extends Application {

	// La maggior parte di queste cose sono inizializzati dai controller
	public static Stage stage;
	public static Utils u = null;
	public static Algoritmo<?> a = null;
	public static Input i = null;
	public static QSortDrawer qDrawer = null;
	public static Service<Void> backgroundSorter = null;
	public static final String greeting =  "\n" +  
            "             _------_ \n" +
	    "           -~        ~- \n" +
	    "          -     _      - \n" +
	    "         -      |>      - \n" + 
	    "         -      |<      - \n" +
	    "          -     |>     - \n" +
	    "           -    ||    - \n" +
	    "            -   ||   - \n" +
	    "             -__||__- \n" +
	    "             |______| \n" +
	    "             <______> \n" +
	    "             <______> \n" +
	    "                \\/\n                    Bye <3";
	
	public static void main(String[] args) {
		
		u = new Utils();
		launch(args);
		System.out.println(greeting);
		
	}
	
	@Override
	public void start(Stage firstStage) throws Exception {
		stage = firstStage;
		u.setMyScene(controller.Scenes.WELCOME);
		stage.show();
	}
}
