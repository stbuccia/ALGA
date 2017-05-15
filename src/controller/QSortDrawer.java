package controller;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class QSortDrawer {

	private Pane panel = null;
	private TextArea ta = null;
	private int len = model.Main.i.items.length;
	private Rectangle rs[] = new Rectangle[len];
	private int gap = 10;

	public QSortDrawer(Pane panel, TextArea ta) {
		this.panel = panel;
		this.ta = ta;
		for (int i = 0; i < len; ++i) {
			rs[i] = new Rectangle();
		}
		System.out.println("Drawer instantiated");
	}

	public void drawRects() {
		panel.getChildren().remove(0, panel.getChildren().size());
		makeRects();
		for (int i = 0; i < model.Main.a.rectangle.getHowMany(); ++i) {
			panel.getChildren().add(rs[i]);
		}
		Line l = new Line();
		l.setStartX(0);
		l.setStartY(panel.getHeight()
				- model.Main.a.rectangle.getPivotH());
		l.setEndX(675);
		l.setEndY(l.getStartY());
		l.setStroke(Color.GREY);
		l.setStrokeWidth(1);
		panel.getChildren().add(l);
	}

	private void makeRects() {
		for (int i = 0; i < len; ++i) {
			rs[i].setFill(Color.WHITE);
			rs[i].setStroke(Color.BLACK);
			rs[i].setX((i * model.Main.a.rectangle.getWidth()));
			rs[i].setY(panel.getHeight()
					- model.Main.a.rectangle.getHeight(i));
			rs[i].setHeight(model.Main.a.rectangle.getHeight(i));
			rs[i].setWidth(model.Main.a.rectangle.getWidth());
			// System.out.println(rs[i].getX() + "," + rs[i].getY()
			// + " - " + rs[i].getWidth() + "x" +
			// rs[i].getHeight());
		}

	}

}
