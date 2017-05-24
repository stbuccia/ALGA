package controller;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Classe di metodi di appoggio
 *
 */
public class Utils extends Object {

	/**
	 * Delay massimo 
	 */
	public int max_delay = 500;
	/**
	 * Delay minimo
	 */
	public int min_delay = 0;
	/**
	 * Delay di default
	 */
	public int pref_delay = 50;
	/**
	 * Quantità massima di elementi da ordinare
	 */
	public int max_n = 1000;
	
	/**
	 * Quantità predefinita di elementi
	 */
	public int pref_n = 50;
	/**
	 * Massimo valore da consentire in input
	 */
	public int max_value = 1000;
	/**
	 * Valore predefinito da consentire in input
	 */
	public int pref_value = 100;
	
	/**
	 * Larghezza predefinita del pannello
	 */
	public int panelx = 723;
	
	/**
	 * Altezza predefinita del pannello
	 */
	public int panely = 480;
	
	/**
	 * Imposta dinamicamente la scena della finestra, facendo tutte le operazioni necessarie
	 * @param myscene	La scena da impostare
	 */
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

	/**
	 * Associa alla scena l'URL del file fxml che la descrive
	 * @param myscene	La scena di cui si vuole l'URL
	 * @return	L'URL cercato
	 */
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
	
	/**
	 * Ritorna un colore a partire da un elemento della palette dei colori
	 * @param p	Richiesta del colore nella Palette
	 * @return	Il colore vero e proprio
	 */
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
		case DONE:
			return Color.web("#8AE38A");
		default:
			return Color.WHITE;
		}

	}
}
