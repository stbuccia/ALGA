package controller;

import model.Main;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class QSortViewController {

	@FXML
	private TextField addField;

	@FXML
	private Button addButton, pausa, indietro, byStep;

	@FXML
	private TextArea console;

	private static QSortDrawer drawer;
	
	@FXML
	private Slider delay;
	
	@FXML
	private Label delayLab;
	
	@FXML
	private Pane rectPane;

	@FXML
	void toWelcomeView(ActionEvent event) {
		model.Main.backgroundSorter.cancel();
		model.Main.backgroundSorter = null;
		model.Main.u.setMyScene(Scenes.WELCOME);
	}

	@FXML
	void initialize() {
		System.out.println("-- QSORTVIEW LOADED -- ");
		//rectPane.setMinHeight(485);
		model.Main.qDrawer = new QSortDrawer(rectPane, console);
		System.out.println(model.Main.qDrawer);
		model.Main.a = new model.Algoritmo<Void>(model.Main.getI());
		model.Main.a.creaRects(685, 485);
		
		delay.adjustValue(Main.a.getDelay());
		delayLab.setText("Delay(ms):"+Main.a.getDelay());
		if (Main.i.getByStep()){
			delay.setDisable(true);
			pausa.setDisable(true);
		}
		else{
			byStep.setDisable(true);
		}
		//model.Main.a.rectangle.setPivotH(model.Main.i.items[0], model.Main.i);
		model.Main.qDrawer.drawRects();
	}
	static QSortDrawer getDrawer() {
		return drawer;
	}

	@FXML
	void pause() {
		Main.a.setInPausa();
		if (Main.a.isInPausa())	pausa.setText("Riprendi");
		else pausa.setText("Pausa");
	}
	
	@FXML
	void changeDelay() {
		Main.a.setDelay((int)delay.getValue());
		delayLab.setText("Delay(ms):"+Main.a.getDelay());
	}
	
	@FXML
	void proceed() {
		if (Main.backgroundSorter==null) testRettangoli();
		Main.a.setIsPressed();
		Main.i.stampaItems();
	}
	
	@FXML
	void testRettangoli() {
		Main.backgroundSorter = new Service<Void>() {
			@Override
			public Task<Void> createTask(){
				return Main.a;
			}
		};
		console.textProperty().bind(model.Main.backgroundSorter.messageProperty());
		Main.backgroundSorter.restart();
//		Main.qDrawer.toConsole(Main.backgroundSorter.messageProperty().getName());
	}

}
