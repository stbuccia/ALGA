package controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class QSortDrawer {

	private Pane panel = null;
	private int len = model.Main.i.items.length;
	private Rectangle rs[] = new Rectangle[len];

	public QSortDrawer(Pane panel) {
		this.panel = panel;
		for (int i = 0; i < len; ++i) {
			rs[i] = new Rectangle();
		}
		System.out.println("Drawer instantiated");
	}

	public void drawRects() {
		try{
			panel.getChildren().remove(0, panel.getChildren().size());
			makeRects();
			for (int i = 0; i < model.Main.a.rectangle.getHowMany(); ++i) {
				panel.getChildren().add(rs[i]);
			}
			Line l = new Line();
			l.setStartX(0);
			if (panel.getHeight()!=0) l.setStartY(panel.getHeight()- model.Main.a.rectangle.getPivotH());
			else l.setStartY(476- model.Main.a.rectangle.getPivotH());
			l.setEndX(675);
			l.setEndY(l.getStartY());
			l.setStroke(Color.GREY);
			l.setStrokeWidth(1);
			panel.getChildren().add(l);
		}catch(java.lang.NullPointerException e){
			System.out.println();
		}
	}

	private void makeRects() {
		for (int i = 0; i < len; ++i) {
			rs[i].setFill(Color.WHITE);
			rs[i].setStroke(Color.BLACK);
			rs[i].setX((i * model.Main.a.rectangle.getWidth()));
			if (panel.getHeight()==0) rs[i].setY(476 - model.Main.a.rectangle.getHeight(i));
			else rs[i].setY(panel.getHeight() - model.Main.a.rectangle.getHeight(i));
			rs[i].setHeight(model.Main.a.rectangle.getHeight(i));
			rs[i].setWidth(model.Main.a.rectangle.getWidth());
			
		}
	}

}
