package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public class QSortViewController {

    @FXML
    private Canvas Canvas;

    @FXML
    private TextFlow Console;

    @FXML
    private TextField addField;

    @FXML
    private Button addButton;

    @FXML
    void toWelcomeView(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Canvas != null : "fx:id=\"Canvas\" was not injected: check your FXML file 'QSortView.fxml'.";
        assert Console != null : "fx:id=\"Console\" was not injected: check your FXML file 'QSortView.fxml'.";
        assert addField != null : "fx:id=\"addField\" was not injected: check your FXML file 'QSortView.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'QSortView.fxml'.";
        
        model.Main.a=new model.Algoritmo(model.Main.i);
		model.Main.a.creaRects(100, 100);
		model.Main.a.stampaItems();
		model.Main.a.doQuickSort(0, model.Main.i.items.length-1);
		model.Main.a.dumpRect();
		model.Main.a.stampaItems();

    }
}
