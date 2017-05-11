package controller;

import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javafx.fxml.FXML;

// Questa classe è il controller
public class FileViewController {

    @FXML
    private TextFlow text;
    
    @FXML
    private Button indietro;
    // Metodo chiamato automaticamente durante la costruzione
    // dell'interfaccia, che fa le operazioni di inizializzazione se necessarie
    @FXML
    private void indietro(){
    	model.Main.u.setMyScene(Scenes.WELCOME);
    }
    
    @FXML
    void initialize(){
		BufferedReader reader = null;
		File source = null;
		String ftxt="";
		try {
			source = new File("/home/buccia/Università/Algoritmi/ALGA/README.md");
			reader = new BufferedReader(new FileReader(source));
			String l = reader.readLine();
			while (l!=null) {
				ftxt += l + "\n";
				l = reader.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { reader.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		Text t = new Text(ftxt);
		text.getChildren().add(t);
    }
}