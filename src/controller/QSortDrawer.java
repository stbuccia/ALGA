package controller;

import model.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Crea l'oggetto che si preoccupa di gestire il disegno della finestra per mostrare l'algoritmo.
 * @see QSortViewController
 */
public class QSortDrawer {

	private static final int endCanvasX = Main.u.panelx;
	private static final int endCanvasY = Main.u.panely;
	private Pane panel = null;
	private int len = model.Main.i.items.length;
	private Rectangle rs[] = new Rectangle[len];
	private double pivotH;

	
	// Crea i rettangoli da aggiungere al campo
	private void makeRects() {

		for (int i = 0; i < len; ++i) {

			pivotH = Main.a.rectangle.getPivotH();
			rs[i].setStroke(Color.GREY);
			if (Main.a.rectangle.getHeight(i) < pivotH) {
				rs[i].setFill(Main.u.fromPalette(Palette.SHORTER));
			}else {
				rs[i].setFill(Main.u.fromPalette(Palette.TALLER));
			}
			
			if (i == Main.a.getFirstSwitched() || i == Main.a.getSecondSwitched()) {
				rs[i].setFill(Main.u.fromPalette(Palette.SWITCHED));
			}

			if (Main.a.getCurrentIndex() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.CURRENT));
			}
			
			if (Main.a.getPivotIndex() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.PIVOT));
			}

			rs[i].setX((i * model.Main.a.rectangle.getWidth()));

			if (panel.getHeight() == 0)
				rs[i].setY((endCanvasY) - model.Main.a.rectangle.getHeight(i));
			else
				rs[i].setY(panel.getHeight()- model.Main.a.rectangle.getHeight(i));
			rs[i].setHeight(model.Main.a.rectangle.getHeight(i));
			rs[i].setWidth(model.Main.a.rectangle.getWidth());
		}
	}
	
	private void makeLabel(int index){
		Label lb = new Label("" + Main.i.items[index]);
		lb.setTranslateY(rs[index].getY() - 20);
		lb.setTranslateX(rs[index].getX());
		panel.getChildren().add(lb);
	}

	/**
	 * Istanzia i rettangoli e si collega al pannello su cui disegnare
	 * @param panel		Il Pannello su cui si disegna
	 */
	public QSortDrawer(Pane panel) {
		this.panel = panel;
		for (int i = 0; i < len; ++i) {
			rs[i] = new Rectangle();
		}
		System.out.println("INFO: Drawer instantiated");
	}

	/**
	 * Disegna i rettangoli sul pannello associato all'oggetto disegnatore
	 */
	public void drawRects() {
		try {
			panel.getChildren().remove(0,
					panel.getChildren().size());
			makeRects();
			for (int i = 0; i < model.Main.a.rectangle.getHowMany(); ++i) {
				panel.getChildren().add(rs[i]);
			}
			Line l = new Line();
			l.setStartX(0);
			if (panel.getHeight() != 0)
				l.setStartY(panel.getHeight() - model.Main.a.rectangle.getPivotH());
			else
				l.setStartY(endCanvasY - model.Main.a.rectangle.getPivotH());

			l.setEndX(endCanvasX);
			l.setEndY(l.getStartY());
			l.setStroke(Main.u.fromPalette(Palette.PIVOT));
			l.setStrokeWidth(1);
			panel.getChildren().add(l);
			
			makeLabel(Main.a.getPivotIndex());
			makeLabel(Main.a.getCurrentIndex());
			makeLabel(Main.a.getFirstSwitched());
			makeLabel(Main.a.getSecondSwitched());
			
		} catch (java.lang.NullPointerException e) {
			// Viene sollevata se si interrompe il disegno dei rettangoli.
			// Visto che succede solamente su esplicita richiesta dell'utente
			// (con Redo o Indietro) la ignoriamo...
			System.out.println();
		}

	}

	/**
	 * Colora di verde tutti i rettangoli 
	 */
	public void clearRects(){
		for (int i = 0; i < rs.length; ++i){
			rs[i].setFill(Main.u.fromPalette(Palette.DONE));
			rs[i].setStroke(Color.WHITE);
		}
	}
	
}
