package controller;

import model.Main;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class QSortViewController {

	@FXML
	private TextField addField;

	@FXML
	private Button addButton;

	@FXML
	private TextArea console;

	private static QSortDrawer drawer;

	@FXML
	private Pane rectPane;

	@FXML
	void toWelcomeView(ActionEvent event) {
		model.Main.u.setMyScene(Scenes.WELCOME);
		//Main.backgroundSorter.cancel();
	}

	@FXML
	void initialize() {
		System.out.println("-- QSORTVIEW LOADED -- ");
		drawer = new QSortDrawer(rectPane, console);
		System.out.println(drawer);
		model.Main.a = new model.Algoritmo<Void>(model.Main.i);
		System.out.println(rectPane.getWidth() + "x"
				+ rectPane.getHeight());
		model.Main.a.creaRects(685, 485);

		// model.Main.a.stampaItems();
		// model.Main.a.dumpRect();

	}

	static QSortDrawer getDrawer() {
		return drawer;
	}

	@FXML
	void testConsole() {
		//drawer.toConsole("\nFunziona cazzo");
	}

	@FXML
	void testRettangoli() {
//		Main.backgroundSorter = new Service<Void>() {
//
//			@Override
//			public Task<Void> createTask() {
//				return new Task<Void>() {
//
//					@Override
//					protected Void call() throws Exception {
//						model.Main.a.doQuickSort(0, model.Main.i.items.length - 1);
//						model.Main.a.stampaItems();
//						return null;
//					}
//				};
//			}
//		};
//		console.textProperty().bind(model.Main.backgroundSorter.messageProperty());
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
