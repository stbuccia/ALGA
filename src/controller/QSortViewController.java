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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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

	public static int counter = 0, panelx = 730, panely = 500;

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
	private Rectangle legRTaller;

	@FXML
	private Text legLTaller;

	@FXML
	void toWelcomeView(ActionEvent event) {
		if (model.Main.a != null) {
			model.Main.a.cancel(true);
			model.Main.a = null;
		}
		Main.backgroundSorter = null;
		counter = 0;
		model.Main.u.setMyScene(Scenes.WELCOME);
	}

	void setDefault() {
		setPausaText();
		go.setDisable(false);
		byStep.setDisable(false);
		pausa.setDisable(false);
		delay.adjustValue(Main.a.getDelay());
		delayLab.setText("Delay: " + Main.a.getDelay() + "ms");
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
	void initialize() {
		System.out.println("INFO: QSortView loaded");
		model.Main.qDrawer = new QSortDrawer(rectPane);
		makeLegend();
		if (Main.backgroundSorter == null) {
			model.Main.a = new model.Algoritmo<Void>(model.Main.i);
			model.Main.a.creaRects(panelx, panely);
			console.setText(Main.a.getItems());
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

		if (Main.i.getMode().equals("Tastiera")
				&& counter < model.Main.i.items.length) {
			go.setDisable(true);
			byStep.setDisable(true);
			pausa.setDisable(true);
			add.setDisable(false);
			addField.setDisable(false);
		}
	}

	@FXML
	void openReadMe() {
		model.Main.u.setMyScene(Scenes.FILE);
	}

	@FXML
	void addItem() {
		String s = addField.getText().toLowerCase();
		try {
			if (Main.i.validateInput(s)) {
				Main.i.items[counter] = Main.i.fromString(s);
				Main.i.initial[counter] = Main.i.fromString(s);
				model.Main.a.creaRects(panelx, panely);
				console.appendText("Inserito " + s + "\n");
				model.Main.qDrawer.drawRects();
				counter++;
			} else {
				if (Main.i.isString())
					console.appendText("Input solo lettere\n");
				else
					console.appendText("Input tra 0 e "
							+ Main.i.getMaxVal()
							+ "\n");
			}
		} catch (NumberFormatException e) {
			console.appendText("L'input non è del tipo richiesto\n");
		}
		if (counter == model.Main.i.items.length) {
			setDefault();
			console.setText(Main.a.getItems());
		}
	}

	@FXML
	void pause() {
		if (Main.a.isRunning()) {
			Main.a.setInPausa();
			setPausaText();
			if (Main.a.isInPausa())
				help.setDisable(false);
			else
				help.setDisable(true);
		}
	}

	void setPausaText() {
		if (Main.a.isInPausa())
			pausa.setText("Riprendi");
		else
			pausa.setText("Pausa");
	}

	@FXML
	void changeDelay() {
		Main.a.setDelay((int) delay.getValue());
		delayLab.setText("Delay: " + Main.a.getDelay() + "ms");
	}

	@FXML
	void reset() {
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
	void proceed() {
		if (Main.backgroundSorter == null)
			testRettangoli();
		Main.a.setIsPressed();
		Main.a.stampaItems();
	}

	@FXML
	void testRettangoli() {
		Main.backgroundSorter = new Service<Void>() {
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
		this.legLChanged.setText("Da scambiare");
		this.legRCurrent.setFill(Main.u.fromPalette(Palette.CURRENT));
		this.legLCurrent.setText("Corrente");
		this.legRPivot.setFill(Main.u.fromPalette(Palette.PIVOT));
		this.legLPivot.setText("Pivot");
		this.legRTaller.setFill(Main.u.fromPalette(Palette.TALLER));
		this.legLTaller.setText("Più grandi del pivot");
	}

}
