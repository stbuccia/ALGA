package controller;

import model.Main;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class QSortDrawer {

	private static final int endCanvasY = 486;
	private static final int endCanvasX = 730;
	private Pane panel = null;
	private int len = model.Main.i.items.length;
	private Rectangle rs[] = new Rectangle[len];
	private double pivotH;

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
				l.setStartY(panel.getHeight()
						- model.Main.a.rectangle
								.getPivotH());
			else
				l.setStartY(endCanvasY
						- model.Main.a.rectangle
								.getPivotH());

			l.setEndX(endCanvasX);
			l.setEndY(l.getStartY());
			l.setStroke(Main.u.fromPalette(Palette.PIVOT));
			l.setStrokeWidth(1);
			panel.getChildren().add(l);
		} catch (java.lang.NullPointerException e) {
			System.out.println();
		}

	}

	private void makeRects() {

		for (int i = 0; i < len; ++i) {

			pivotH = Main.a.rectangle.getPivotH();
			rs[i].setStroke(Color.GREY);
			if (Main.a.rectangle.getHeight(i) > pivotH) {
				rs[i].setFill(Main.u
						.fromPalette(Palette.TALLER));
			} else if (Main.a.getPivotIndex() == i) {
				rs[i].setFill(Main.u.fromPalette(Palette.PIVOT));
			} else {
				rs[i].setFill(Main.u
						.fromPalette(Palette.SHORTER));
			}

			if (Main.a.getCurrentIndex() == i) {
				rs[i].setFill(Main.u
						.fromPalette(Palette.CURRENT));
			}
			
			if (i == Main.a.getFirstToSwitch() || i == Main.a.getSecondToSwitch()) {
				rs[i].setFill(Main.u.fromPalette(Palette.SWITCHED));
			}

			rs[i].setX((i * model.Main.a.rectangle.getWidth()));

			if (panel.getHeight() == 0)
				rs[i].setY(485 - model.Main.a.rectangle
						.getHeight(i));
			else
				rs[i].setY(panel.getHeight()
						- model.Main.a.rectangle
								.getHeight(i));
			rs[i].setHeight(model.Main.a.rectangle.getHeight(i));
			rs[i].setWidth(model.Main.a.rectangle.getWidth());
		}
	}

}
