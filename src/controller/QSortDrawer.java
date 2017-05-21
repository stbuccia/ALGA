package controller;

import model.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class QSortDrawer {

	private static final int endCanvasX = Main.u.panelx;
	private static final int endCanvasY = Main.u.panely;
	private Pane panel = null;
	private int len = model.Main.i.items.length;
	private Rectangle rs[] = new Rectangle[len];
	private double pivotH;
	private Label pivotLabel;

	public QSortDrawer(Pane panel) {
		this.panel = panel;
		for (int i = 0; i < len; ++i) {
			rs[i] = new Rectangle();
		}
		System.out.println("INFO: Drawer instantiated");
	}

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
			
			pivotLabel = new Label("" + Main.i.items[Main.a.getPivotIndex()]);
			pivotLabel.setTranslateY(rs[Main.a.getPivotIndex()].getY() - 20);
			pivotLabel.setTranslateX(rs[Main.a.getPivotIndex()].getX());
			panel.getChildren().add(pivotLabel);
			
			pivotLabel = new Label("" + Main.i.items[Main.a.getCurrentIndex()]);
			pivotLabel.setTranslateY(rs[Main.a.getCurrentIndex()].getY() - 20);
			pivotLabel.setTranslateX(rs[Main.a.getCurrentIndex()].getX());
			panel.getChildren().add(pivotLabel);
			
			pivotLabel = new Label("" + Main.i.items[Main.a.getCurrentJ()]);
			pivotLabel.setTranslateY(rs[Main.a.getCurrentJ()].getY() - 20);
			pivotLabel.setTranslateX(rs[Main.a.getCurrentJ()].getX());
			panel.getChildren().add(pivotLabel);
			
		} catch (java.lang.NullPointerException e) {
			System.out.println();
		}

	}

	private void makeRects() {

		for (int i = 0; i < len; ++i) {

			pivotH = Main.a.rectangle.getPivotH();
			rs[i].setStroke(Color.GREY);
			if (Main.a.rectangle.getHeight(i) < pivotH) {
				rs[i].setFill(Main.u.fromPalette(Palette.SHORTER));
			} else if (Main.a.getPivotIndex() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.PIVOT));
			} else {
				rs[i].setFill(Main.u.fromPalette(Palette.TALLER));
			}
			
			if (i == Main.a.getFirstToSwitch() || i == Main.a.getSecondToSwitch()) {
				rs[i].setFill(Main.u.fromPalette(Palette.SWITCHED));
			}

			if (Main.a.getCurrentIndex() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.CURRENT));
			}
			
			if (Main.a.getCurrentJ() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.CURRENT));
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
	
}
