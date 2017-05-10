package controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class WelcomeWindowController {
	
	@FXML private ComboBox<String> Tipo, Modalita;
	@FXML private CheckBox auto;
	@FXML private TextField path, delay, num, value;
	@FXML private Label stato;
	@FXML private Button start;
	
	@FXML
	void togglePath(){
		String s = Modalita.getSelectionModel().getSelectedItem();
		path.setDisable(!s.equals("File"));
		control();
	}
	
	@FXML
	void toggleValue(){
		String s = Tipo.getSelectionModel().getSelectedItem();
		value.setDisable(s.equals("Stringhe"));
		control();
	}
	
	@FXML
	void toggleDelay(){
		delay.setDisable(!auto.isSelected());
		control();
	}

	boolean controlPath(){
		boolean disable=false;
		if (!path.isDisable()){
			String s=path.getText();
			if (!new File(s).exists()){
				stato.setText("Il file non esiste");
				disable=true;
				start.setDisable(true);
			}
		}
		return (!disable);
	}

	boolean controlDelay(){
		int ms=0;
		String s=delay.getText();
		boolean disable=false;
		if (!delay.isDisable()){
			try{
				ms=Integer.parseInt(s);
			}
			catch(NumberFormatException e){
				stato.setText("Il delay deve essere un numero");
				disable=true;
			}
			if(ms <0 || ms >5000){
				stato.setText("Il delay deve essere un intero tra 0 e 5000 ");
				disable=true;
			}
		}
		if (disable) start.setDisable(true);
		return (!disable);
	}
	
	boolean controlNumber(){
		int n=1;
		String s=num.getText();
		boolean disable=false;
		try{
			n=Integer.parseInt(s);
		}
		catch(NumberFormatException e){
			stato.setText("Il numero degli elementi non è valido");
			disable=true;
		}
		if(n <1 || n >1000){
			stato.setText("Il numero deglie elementi deve essere un intero tra 1 e 1000 ");
			disable=true;
		}
		if (disable) start.setDisable(true);
		return (!disable);
	}
	
	boolean controlMaxValue(){
		int n=1;
		String s=value.getText();
		boolean disable=false;
		if (!value.isDisable()){
			try{
				n=Integer.parseInt(s);
			}
			catch(NumberFormatException e){
				stato.setText("Il valore massimo degli elementi non è valido");
				disable=true;
			}
			if(n <1 || n >1000){
				stato.setText("Il valore massimo degli elementi deve essere un intero tra 1 e 1000 ");
				disable=true;
			}
		}
		if (disable) start.setDisable(true);
		return (!disable);
	}
	
	@FXML
	void control(){
		if (controlPath() && controlDelay() && controlNumber() && controlMaxValue()){
			start.setDisable(false);
			stato.setText("");
		}
	}
	
	@FXML
	void inizia(ActionEvent event){
		
		int n;
		model.Main.u.setMyScene(Scenes.QSORT);
		
		model.Input i;
		model.Algoritmo a=new model.Algoritmo();
		if (auto.isSelected()) a.setByStep(false);
		
		a.setDelay(Integer.parseInt(delay.getText()));
		
		n=Integer.parseInt(num.getText());
		
		i=new model.Input(n, Tipo.getSelectionModel().getSelectedItem());
		i.setMode(Modalita.getSelectionModel().getSelectedItem());
		i.setPath(path.getText());
		i.setMaxVal(Integer.parseInt(value.getText()));
		i.riempiItems();
		i.stampaItems();
		
		a.doQuickSort(i, 0, n-1);
		i.stampaItems();
	}
	
	@FXML
    public void initialize() {
		
		Tipo.getSelectionModel().select("Interi");
		Modalita.getSelectionModel().select("Casuale");
		
		path.setText("/home/buccia/file.txt");
		path.setDisable(true);
		
		delay.setText("50");
		delay.setDisable(false);
		
		num.setText("100");
		num.setDisable(false);
		
		value.setText("100");
		value.setDisable(false);
		
		auto.setSelected(true);
		stato.setText("");
		start.setDisable(false);
    }
}
