package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Main;

/**
 * Vista del README
 *
 */
public class FileViewController {

    @FXML
    private TextFlow text;
    
    @FXML private Pane pane;
    
    private WebView view = new WebView();
    private WebEngine engine = view.getEngine();
    
    @FXML
    private Button indietro;
    
    @FXML
    private void indietro(){
    	if (model.Main.a==null) model.Main.u.setMyScene(Scenes.WELCOME);
    	else model.Main.u.setMyScene(Scenes.QSORT);
    }
    
    @FXML
    private void initialize(){
	    System.out.println(Main.u.getClass().getResource("/assets/README.html"));
	   engine.load(Main.u.getClass().getResource("/assets/README.html").toString());
	   pane.getChildren().add(view);
    }
}