package model;

import controller.Utils;
import controller.QSortDrawer;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.stage.Stage;

public class Main extends Application {

	// La maggior parte di queste cose sono inizializzati dai controller
	
	/**
	 * Stage condiviso sul quale si inseriscono le viste
	 */
	public static Stage stage;
	
	/**
	 * Utilities
	 */
	public static Utils u = null;
	
	/**
	 * Esecutore dell'algoritmo
	 */
	public static Algoritmo<?> a = null;
	
	/**
	 * Input per l'algoritmo
	 */
	public static Input i = null;
	
	/**
	 * Disegnatore
	 */
	public static QSortDrawer qDrawer = null;
	
	/**
	 * Servizio di ordinamento in background
	 */
	public static Service<Void> backgroundSorter = null;
	
	private final static String greeting =  "\n" +  
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
