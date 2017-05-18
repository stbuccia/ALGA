package controller;

import java.io.File;

import model.Main;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class WelcomeWindowController {

	@FXML
	private ComboBox<String> Tipo, Modalita;
	@FXML
	private CheckBox auto;
	@FXML
	private TextField path, delay, num, value;
	@FXML
	private Slider slide_delay, slide_num, slide_value;
	@FXML
	private Label stato;
	@FXML
	private Button start;

	@FXML
	void togglePath() {
		String s = Modalita.getSelectionModel().getSelectedItem();
		path.setDisable(!s.equals("File"));
		control();
	}

	@FXML
	void toggleValue() {
		value.setText(""+Main.u.pref_value);
		String s = Tipo.getSelectionModel().getSelectedItem();
		value.setDisable(s.equals("Stringhe"));
		control();
	}

	@FXML
	void toggleDelay() {
		delay.setText(""+Main.u.pref_delay);
		delay.setDisable(!auto.isSelected());
		control();
	}

	boolean controlPath() {
		boolean disable = false;
		if (!path.isDisable()) {
			String s = path.getText();
			if (!new File(s).exists()) {
				stato.setText("Il file non esiste");
				disable = true;
				start.setDisable(true);
			}
		}
		return (!disable);
	}

	boolean controlDelay() {
		int ms = Main.u.min_delay;
		String s = delay.getText();
		boolean disable = false;
		if (!delay.isDisable()) {
			try {
				ms = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				stato.setText("Il delay deve essere un numero");
				disable = true;
			}
			if (ms < Main.u.min_delay || ms > Main.u.max_delay ) {
				stato.setText("Il delay deve essere un intero tra "+Main.u.min_delay+" e "+Main.u.max_delay );
				disable = true;
			}
		}
		if (disable)
			start.setDisable(true);
		return (!disable);
	}

	boolean controlNumber() {
		int n = 1;
		String s = num.getText();
		boolean disable = false;
		try {
			n = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			stato.setText("Il numero degli elementi non è valido");
			disable = true;
		}
		if (n < 1 || n > Main.u.max_n) {
			stato.setText("Il numero deglie elementi deve essere un intero tra 1 e " +Main.u.max_n);
			disable = true;
		}
		if (disable)
			start.setDisable(true);
		return (!disable);
	}

	boolean controlMaxValue() {
		int n = 1;
		String s = value.getText();
		boolean disable = false;
		if (!value.isDisable()) {
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				stato.setText("Il valore massimo degli elementi non è valido");
				disable = true;
			}
			if (n < 1 || n > Main.u.max_value) {
				stato.setText("Il valore massimo degli elementi deve essere un intero tra 1 e "+Main.u.max_value);
				disable = true;
			}
		}
		if (disable)
			start.setDisable(true);
		return (!disable);
	}

	@FXML
	void control() {

		if (controlPath() && controlDelay() && controlNumber() && controlMaxValue()) {
			start.setDisable(false);
			stato.setText("");
			changeSliders();
		}
	}

	@FXML
	void openReadMe() {
		model.Main.u.setMyScene(Scenes.FILE);
	}

	@FXML
	void changeSliders() {
		slide_delay.adjustValue(Double.parseDouble(delay.getText()));
		slide_num.adjustValue(Double.parseDouble(num.getText()));
		slide_value.adjustValue(Double.parseDouble(value.getText()));
	}

	@FXML
	void movedSlider() {
		delay.setText(String.valueOf((int) slide_delay.getValue()));
		num.setText(String.valueOf((int) slide_num.getValue()));
		value.setText(String.valueOf((int) slide_value.getValue()));
		stato.setText("");
	}

	@FXML
	void inizia(ActionEvent event) {

		int n;

		n = Integer.parseInt(num.getText());
		model.Main.i = new model.Input(n, Tipo.getSelectionModel().getSelectedItem());
		model.Main.i.setMode(Modalita.getSelectionModel().getSelectedItem());
		model.Main.i.setPath(path.getText());
		model.Main.i.setMaxVal(Integer.parseInt(value.getText()));
		if (auto.isSelected())
			model.Main.i.setByStep(false);
		model.Main.i.setDelay(Integer.parseInt(delay.getText()));
		model.Main.i.riempiItems();

		model.Main.u.setMyScene(Scenes.QSORT);

	}

	@FXML
	public void initialize() {

		Tipo.getSelectionModel().select("Interi");
		Modalita.getSelectionModel().select("Casuale");

		try {
			path.setText((getClass().getResource("DataSample.txt").toURI()).getPath());
		} catch (Exception e) {
			System.out.println("WARNING: Default data set non provided");
			path.setText("/percorso/al/file.txt");
		}
		path.setDisable(true);

		delay.setText(""+Main.u.pref_delay);
		delay.setDisable(false);
		slide_delay.setMin(Main.u.min_delay);
		slide_delay.setMax(Main.u.max_delay);

		num.setText(""+Main.u.pref_n);
		num.setDisable(false);
		slide_num.setMin(1);
		slide_num.setMax(Main.u.max_n);


		value.setText(""+Main.u.pref_value);
		value.setDisable(false);
		slide_value.setMin(1);
		slide_value.setMax(Main.u.max_value);

		slide_delay.adjustValue(Double.parseDouble(delay.getText()));
		slide_num.adjustValue(Double.parseDouble(num.getText()));
		slide_value.adjustValue(Double.parseDouble(value.getText()));

		auto.setSelected(true);
		stato.setText("");
		start.setDisable(false);
	}
}
