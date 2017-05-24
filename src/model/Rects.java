package model;

/**
 * Contiene la logica per calcolare e creare le informazioni associate a ogni rettangolo.
 * Da qui verranno prese per disegnare i rettangoli sulla finestra
 *
 */
public class Rects {
	
	private Double[] heights;
	private double width = 0, max_height = 0, max_width = 0;
	private double pivotHeight = 0;
	
	// Calcola altezza stringhe
	private double calcolaStringHeight(String s, int pos) {
		if (pos >= s.length())
			return 0;
		else {
			double x = ((double) (s.charAt(pos) - 'a') + 1) / 27;
			return (x + (calcolaStringHeight(s, pos + 1) / 27));
		}
	}
	
	/**
	 * 
	 * @param i	Input su cui calcolare le informazioni per i rettangoli
	 * @param cwidth	Larghezza del campo su cui disegnare
	 * @param cheight	Altezza del campo su cui disegnare
	 */
	public Rects(Input i, double cwidth, double cheight) {
		max_height = cheight - 10;
		max_width = cwidth - 10;
		if (i != null)
			heights = new Double[i.items.length];
		else
			heights = new Double[0];
	}

	/**
	 * Imposta la larghezza dei rettangoli in base alla larghezza totale del campo 
	 * @param n	Il numero di rettangoli
	 */
	public void setWidth(int n) {
		width = max_width / n;
	}

	/**
	 * Ritorna l'altezza dell' n.mo rettangolo
	 * @param n	L'n.mo rettangolo
	 * @return	L'altezza del rettangolo
	 */
	public double getHeight(int n) {
		return heights[n];
	}

	/**
	 * 
	 * @return Larghezza di un rettangolo, uguale per tutti
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Imposta l'altezza di tutti i rettangoli tenendo conto del valore massimo consentito
	 * per l'input, evitando problemi con la scala
	 * @param i	L'input su cui calcolare le altezze
	 */
	public void setHeights(Input i) {
		if (i.isString())
			for (int j = 0; j < i.items.length; j++) {
				double h = calcolaStringHeight(
						i.items[j].toString(), 0);
				heights[j] = (h) * max_height; // 1 è il massimo valore che può avere calcolaStringHeight
			}
		else
			for (int j = 0; j < i.items.length; j++)
				heights[j] = (new Double(i.items[j].toString()) / i.getMaxVal()) * max_height;
	}

	/**
	 * Scambia la posizione di due rettangoli
	 * @param x	Il primo rettangolo
	 * @param y	Il secondo rettangolo
	 */
	public void switchRect(int x, int y) {
		double temp = heights[x];
		heights[x] = heights[y];
		heights[y] = temp;
	}

	/**
	 * 
	 * @return	Il numero totale di rettangoli
	 */
	public int getHowMany() {
		return this.heights.length;
	}

	/**
	 * 
	 * @return	L'altezza del pivot corrente
	 */
	public double getPivotH() {
		return this.pivotHeight;
	}

	/**
	 * Imposta l'altezza del rettangolo corrispondente al pivot corrente
	 * @param p	Indice del pivot
	 */
	public void setPivotH(int p) {		
		pivotHeight = getHeight(p);
	}
}
