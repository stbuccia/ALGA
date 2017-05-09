package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class WelcomeWindowController {
	
	@FXML private ComboBox<String> Tipo, Modalita;
	@FXML private CheckBox auto;
	@FXML private TextField path, delay, num, value;
	@FXML private Label stato;
	@FXML private Button start;
	
	@FXML
	void togglePath(){
		String s = Modalita.getSelectionModel().getSelectedItem();
		if (s.equals("File")) path.setDisable(false);
		else path.setDisable(true);
	}
	
	@FXML
	void toggleDelay(){
		delay.setText("50");
		controlDelay();
		controlNumber();
		controlMaxValue();
		delay.setDisable(!auto.isSelected());
	}
	
	@FXML
	void controlDelay(){
		int ms=0;
		String s=delay.getText();
		stato.setText("");
		boolean disable=false;
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
		start.setDisable(disable);
	}
	
	@FXML
	void controlNumber(){
		int n=1;
		String s=num.getText();
		stato.setText("");
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
		start.setDisable(disable);
	}
	
	@FXML
	void controlMaxValue(){
		int n=1;
		String s=value.getText();
		stato.setText("");
		boolean disable=false;
		
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
		start.setDisable(disable);
	}
	
	@FXML 
	void onButtonPressed(ActionEvent event) {
		int n;
		
		model.Input i;
		model.Algoritmo a=new model.Algoritmo();
		if (auto.isSelected()) a.setByStep(false);
		
		a.setDelay(Integer.parseInt(delay.getText()));
		
		n=Integer.parseInt(num.getText());
		
		i=creaInput(n, Tipo.getSelectionModel().getSelectedItem());
		i.setMode(Modalita.getSelectionModel().getSelectedItem());
		i.setPath(path.getText());
		i.setMaxVal(Integer.parseInt(value.getText()));
		i.riempiItems();
		i.stampaItems();
		
		a.doQuickSort(i, 0, n-1);
		i.stampaItems();
	}
	
	static model.Input creaInput(int n, String s){
		if (s.equals("Interi")) return new model.Input<Integer> (n, 100, Integer.class);
		else if (s.equals("Reali")) return new model.Input<Double> (n, 100, Double.class);
		else return new model.Input<String> (n, 100, String.class);
	}
	
	@FXML
    public void initialize() {
		path.setDisable(true);
		//start.setDisable(false);
    }
}
