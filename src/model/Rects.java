package model;

public class Rects {
	private double[] heights;
	private double width=0, max_height=0, max_width=0;
	public <T> Rects(Input<T> i, double cheight, double cwidth){
		max_height=cheight;
		max_width=cwidth;
		heights= new double[i.items.length];
		setWidth(i.items.length);
		setHeights(i);
	}
	
	private void setWidth(int n){
		width=max_width/n;
	}
	
	private <T> void setHeights(Input<T> i){
		if (i.isString())
			for (int j=0; j<i.items.length; j++){
					int h=calcolaStringHeight(i.items);
					heights[j]=(h/i.getMaxVal())*max_height;
			}
		else
			for (int j=0; j<i.items.length; j++)
				heights[j]=((double)i.items[j]/i.getMaxVal())*max_height;
	}
}
