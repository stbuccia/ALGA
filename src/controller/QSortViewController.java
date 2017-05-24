package controller;

import java.net.URL;

import model.Main;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Gestisce la finestra che visualizza l'algoritmo
 *
 */
public class QSortViewController {

	@FXML
	private URL location;

	@FXML
	private TextField addField;

	@FXML
	private Button addButton, pausa, indietro, byStep, go, add, help;

	@FXML
	private ProgressBar bar;

	@FXML
	private TextArea console;

	@FXML
	private Slider delay;

	@FXML
	private Label delayLab;

	@FXML
	private Pane rectPane;

	@FXML
	private Rectangle legRPivot;

	@FXML
	private Text legLPivot;

	@FXML
	private Rectangle legRCurrent;

	@FXML
	private Text legLCurrent;

	@FXML
	private Rectangle legRChanged;

	@FXML
	private Text legLChanged;

	@FXML
	private Rectangle legRShorter;

	@FXML
	private Text legLShorter;
	
	@FXML
	private Label pivotLbl;
	
	private int counterKey = 0;


	@FXML
	private void toWelcomeView(ActionEvent event) {
		if (model.Main.a != null) {
			model.Main.a.cancel(true);
			model.Main.a = null;
		}
		Main.backgroundSorter = null;
		counterKey = 0;
		model.Main.u.setMyScene(Scenes.WELCOME);
	}

	private void setDefault() {
		setPausaText();
		go.setDisable(false);
		byStep.setDisable(false);
		pausa.setDisable(false);
		delay.setMin(Main.u.min_delay);
		delay.setMax(Main.u.max_delay);
		delay.adjustValue(Main.i.getDelay());
		delayLab.setText("Delay: " + Main.i.getDelay() + "ms");
		add.setDisable(true);
		addField.setDisable(true);
		if (Main.i.getByStep()) {
			delay.setDisable(true);
			pausa.setDisable(true);
			go.setDisable(true);
		} else {
			byStep.setDisable(true);
		}
	}

	
	@FXML
	private void openReadMe() {
		model.Main.u.setMyScene(Scenes.FILE);
	}

	@FXML
	private void addItem() {
		String s = addField.getText().toLowerCase();
		try {
			if (Main.i.validateInput(s)) {
				Main.i.items[counterKey] = Main.i.fromString(s);
				Main.i.initial[counterKey] = Main.i.fromString(s);
				model.Main.a.creaRects(Main.u.panelx, Main.u.panely);
				console.appendText("Inserito " + s + "\n");
				addField.setText("");
				model.Main.qDrawer.drawRects();
				counterKey++;
			} else {
				if (Main.i.isString())
					console.appendText("Input solo lettere\n");
				else
					console.appendText("Input tra 0 e "+ Main.i.getMaxVal()+ "\n");
			}
		} catch (NumberFormatException e) {
			console.appendText("L'input non è del tipo richiesto\n");
		}
		if (counterKey == model.Main.i.items.length) {
			setDefault();
			console.setText(Main.a.getItems());
		}
	}

	@FXML
	private void pause() {
		if (Main.a.isRunning()) {
			Main.a.setInPausa();
			setPausaText();
			if (Main.a.isInPausa())
				help.setDisable(false);
			else
				help.setDisable(true);
		}
	}
	
	private void setPausaText() {
		if (Main.a.isInPausa())
			pausa.setText("Riprendi");
		else
			pausa.setText("Pausa");
	}

	@FXML
	private void changeDelay() {
		Main.i.setDelay((int) delay. getValue());
		delayLab.setText("Delay: " + Main.i.getDelay() + "ms");
	}

	@FXML
	private void reset() {
		if (Main.a.isRunning() && !Main.a.isInPausa()) Main.a.setInPausa();
        		if (model.Main.a != null) {
        			model.Main.a.cancel(true);
        			model.Main.a = null;
        			help.setDisable(false);
        		}
        		bar.progressProperty().unbind();
        		bar.setProgress(0.0);
        		console.textProperty().unbind();
        		Main.backgroundSorter = null;
        		for (int j = 0; j < Main.i.items.length; j++)
        			Main.i.items[j] = Main.i.initial[j];
        		initialize();
	}

	@FXML
	private void proceed() {
		if (Main.backgroundSorter == null)
			go();
		Main.a.setIsPressed();
		Main.a.stampaItems();
	}

	@FXML
	private void go() {
		Main.backgroundSorter = new Service<Void>() {
			@SuppressWarnings("unchecked")
			@Override
			public Task<Void> createTask() {
				return (Task<Void>) Main.a;
			}
		};
		help.setDisable(true);
		console.textProperty().bind(
				model.Main.backgroundSorter.messageProperty());
		bar.progressProperty().bind(
				model.Main.backgroundSorter.progressProperty());
		Main.backgroundSorter.restart();
	}
	
	private void makeLegend(){
		this.legRChanged.setFill(Main.u.fromPalette(Palette.SWITCHED));
		this.legLChanged.setText("Scambiati");
		this.legRCurrent.setFill(Main.u.fromPalette(Palette.CURRENT));
		this.legLCurrent.setText("Controllo");
		this.legRPivot.setFill(Main.u.fromPalette(Palette.PIVOT));
		this.legLPivot.setText("Pivot");
		this.legRShorter.setFill(Main.u.fromPalette(Palette.SHORTER));
		this.legLShorter.setText("Più piccoli del pivot");
	}
	
	@FXML
	private void initialize() {
		System.out.println("INFO: QSortView loaded");
		model.Main.qDrawer = new QSortDrawer(rectPane);
		makeLegend();
		if (Main.backgroundSorter == null) {
			model.Main.a = new model.Algoritmo<Void>(model.Main.i);
			model.Main.a.creaRects(Main.u.panelx, Main.u.panely);
			console.setText(Main.a.getItems()+"\n");
		} else {
			console.textProperty().bind(
					model.Main.backgroundSorter
							.messageProperty());
			bar.progressProperty().bind(
					model.Main.backgroundSorter
							.progressProperty());
		}
		model.Main.qDrawer.drawRects();
		setDefault();

		if (Main.i.getMode().equals("Tastiera") && counterKey < model.Main.i.items.length) {
			go.setDisable(true);
			byStep.setDisable(true);
			pausa.setDisable(true);
			add.setDisable(false);
			addField.setDisable(false);
		}
	}

}
