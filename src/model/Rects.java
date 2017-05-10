package model;

public class Rects {
	private Double[] heights;
	private double width=0, max_height=0, max_width=0;
	
	public Rects(Input i, double cwidth, double cheight){
		max_height=cheight;
		max_width=cwidth;
		heights= new Double[i.items.length];
		setWidth(i.items.length);
		setHeights(i);
	}
	
	public void setWidth(int n){
		width=max_width/n;
	}
	
	public double getHeight(int n){
		return heights[n];
	}
	public double getWidth(){
		return width;
	}
	public void setHeights(Input i){
		if (i.isString())
			for (int j=0; j<i.items.length; j++){
					double h=calcolaStringHeight(i.items[j].toString(), 0);
					System.out.println(h);
					heights[j]=(h)*max_height; //1 è il massimo valore che può avere calcolaStringHeight
			}
		else
			for (int j=0; j<i.items.length; j++)
				heights[j]=(new Double(i.items[j].toString())/i.getMaxVal())*max_height;
	}
	
	private double calcolaStringHeight(String s, int pos){
		if (pos>=s.length())
			return 0;
		else{
			double x=((double)(s.charAt(pos)-'a')+1)/27;
			return x+(1/27)*calcolaStringHeight(s, pos+1);
		}
	}
	
	public void switchRect(int x, int y){
		double temp=heights[x];
		heights[x]=heights[y];
		heights[y]=temp;
	}
}
