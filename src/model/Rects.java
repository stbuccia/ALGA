package model;

public class Rects {
	
	private Double[] heights;
	private double width = 0, max_height = 0, max_width = 0;
	private double pivotHeight = 0;

	public Rects(Input i, double cwidth, double cheight) {
		max_height = cheight - 10;
		max_width = cwidth - 10;
		if (i != null)
			heights = new Double[i.items.length];
		else
			heights = new Double[0];
	}

	public void setWidth(int n) {
		width = max_width / n;
	}

	public double getHeight(int n) {
		return heights[n];
	}

	public double getWidth() {
		return width;
	}

	public void setHeights(Input i) {
		if (i.isString())
			for (int j = 0; j < i.items.length; j++) {
				double h = calcolaStringHeight(
						i.items[j].toString(), 0, 0);
				heights[j] = (h) * max_height; // 1 è il massimo
							       // valore che può
							       // avere
							       // calcolaStringHeight
			}
		else
			for (int j = 0; j < i.items.length; j++)
				heights[j] = (new Double(i.items[j].toString()) / i
						.getMaxVal()) * max_height;
	}

	private double calcolaStringHeight(String s, int pos, int iter) {
		if (pos >= s.length())
			return 0;
		else {
			double x = ((double) (s.charAt(pos) - 'a') + 1) / 27;
			//System.out.println(x + " " + iter);
			return (x + (calcolaStringHeight(s, pos + 1, iter + 1) / 27));
		}
	}

	public void switchRect(int x, int y) {
		double temp = heights[x];
		heights[x] = heights[y];
		heights[y] = temp;
	}

	public void dumpRect() {
		for (int j = 0; j < heights.length; ++j) {
			System.out.println(j + ") " + heights[j]);
		}
	}

	public int getHowMany() {
		return this.heights.length;
	}

	public double getPivotH() {
		return this.pivotHeight;
	}

	public void setPivotH(Object p, Input i) {
		if (i.isString()) {
			pivotHeight = calcolaStringHeight(p.toString(), 0, 0);
		} else {
			pivotHeight = (new Double(p.toString()) / i.getMaxVal())
					* max_height;
		}
		
		//System.out.println("************* " + pivotHeight);
	}
}
