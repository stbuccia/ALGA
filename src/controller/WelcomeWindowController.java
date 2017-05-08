package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class WelcomeWindowController {
	
	@FXML private ComboBox<String> Tipo, Modalita;
	@FXML private CheckBox auto;
	@FXML private TextField path;
	@FXML private TextField delay;
	
	@FXML 
	void onButtonPressed(ActionEvent event) {
		int n=10, ms=50;
		
		model.Input i;
		model.Algoritmo a=new model.Algoritmo();
		
		if (auto.isSelected()) a.setByStep(false);
		System.out.println(delay.getText());
		try{
			a.setDelay(Integer.parseInt(delay.getText()));
		}catch(NumberFormatException e){
			System.out.println("Il delay deve essere un numero: impostato per default a 50");
		}
		
		i=creaInput(n, Tipo.getSelectionModel().getSelectedItem());
		i.setMode(Modalita.getSelectionModel().getSelectedItem());
		i.setPath(path.getText());
		
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
		
    }
}
