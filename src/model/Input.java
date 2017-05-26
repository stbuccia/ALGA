package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Input {

	/**
	 * Array dei dati da ordinare
	 */
	public Object[] items;
	/**
	 * Copia dei dati per ricominciare dall'inizio l'esecuzione
	 */
	public Object[] initial;
	
	/**
	 * File nel quale prendere eventualmente i dati
	 */
	public File file = null;
	
	private Integer max_val = Main.u.pref_value, lunghStr = 20;
	private String mode, type;
	private int delay = Main.u.pref_delay;
	private boolean byStep = true;

	private Object maxInRangeElement() {
		if (this.isInteger())
			return fromInteger(max_val);
		else if (this.isDouble())
			return fromDouble((double) (max_val));
		else {
			String s = "";
			for (int i = 0; i < lunghStr; i++)
				s += "z";
			return (fromString(s));
		}
	}

	private Object minInRangeElement() {
		if (this.isInteger())
			return fromInteger(0);
		else if (this.isDouble())
			return fromDouble(0.0);
		else
			return (fromString("a"));
	}

	private Object nullElement() {
		if (this.isInteger())
			return new Integer(0);
		else if (this.isDouble())
			return new Double(0.0);
		else
			return String.valueOf("");
	}

	private void riempiInitial() {
		for (int i = 0; i < items.length; i++)
			initial[i] = items[i];
	}

	private void riempiRandItems() {
		for (int i = 0; i < items.length; i++) {
			if (!this.isString())
				items[i] = fromDouble(Math.random()
						* (max_val + 1));
			else {
				int lunghezza = (int) (Math.random() * lunghStr + 1); // massimo
										      // 20
										      // caratteri
				String s = "";
				for (int j = 0; j < lunghezza; j++)
					s += (char) (Math.random() * 26 + 97); // Stringhe
									       // con
									       // solo
									       // lettere
									       // minuscole
				items[i] = fromString(s);
			}
		}
	}

	private void azzeraItems() {
		// aggiunge solo 0, poi ci pensa la finestra a gestirli come si
		// deve
		for (int i = 0; i < items.length; i++) {
			items[i] = nullElement();
		}
	}

	private void getFromFile() {

		azzeraItems();
		String l = "";
		System.out.println("INFO: Loading data from " + this.file.getPath());
		BufferedReader reader = null;
		File source = null;

		try {
			source = new File(this.file.getPath());
			reader = new BufferedReader(new FileReader(source));
			for (int i = 0; i < items.length; ++i) {
				try {
					l = reader.readLine().toLowerCase();
					add(l, i);
				} catch (Exception nd) {
					nd.printStackTrace();
					System.out.println("WARNING: Skipping line "
							+ i);
					l = reader.readLine().toLowerCase();
					add(l, i);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void add(String l, int i) {
		try {
			// System.out.println(l);
			if (l != null) {
				l = l.trim();
				if (validateInput(l)) {
					items[i] = fromString(l);
				} else if (!isString()) {
					items[i] = maxInRangeElement();
				} else {
					items[i] = nullElement();
				}
			} else
				items[i] = nullElement();
		} catch (Exception e) {
			items[i] = nullElement();
		}
	}

	/**
	 * Costruttore per l'algoritmo
	 * @param n	Il numero degli elementi in input
	 * @param tipo	Il tipo di dato da ordinare (Interi | Reali | Stringhe)
	 */
	public Input(int n, String tipo) {
		this.items = new Object[n];
		this.initial = new Object[n];
		this.type = tipo;
	}

	/**
	 * 
	 * @param n	Imposta il valore massimo ammesso dall'input
	 */
	public void setMaxVal(Integer n) {
		this.max_val = n;
	}

	/**
	 * Imposta come verranno presi i dati
	 * @param c	"Tastiera", "Casuale", "File"
	 */
	public void setMode(String c) {
		this.mode = c;
	}

	/**
	 * Imposta il delay
	 * @param n	Il delay
	 */
	public void setDelay(int n) {
		this.delay = n;
	}

	/**
	 * Imposta l'esecuzione step by step
	 * @param cond true se si vuole eseguire step by step
	 */
	public void setByStep(boolean cond) {
		this.byStep = cond;
	}

	/**
	 * 
	 * @return true sse l'esecuzione è impostata a step
	 */
	public boolean getByStep() {
		return this.byStep;
	}

	/**
	 * 
	 * @return il delay correntemente impostato
	 */
	public int getDelay() {
		return this.delay;
	}

	/**
	 * 
	 * @return	La modalità di acquisizione dei dati
	 */
	public String getMode() {
		return this.mode;
	}

	/**
	 * 
	 * @return	Il massimo valore accettato dall'algoritmo
	 */
	public Integer getMaxVal() {
		return max_val;
	}

	/**
	 * Acquisisce i dati in accordo alla modalità impostata
	 */
	public void riempiItems() {
		if (this.mode.equals("Casuale"))
			this.riempiRandItems();
		else if (this.mode.equals("Tastiera"))
			this.azzeraItems();
		else if (this.mode.equals("File"))
			this.getFromFile();
		else
			System.out.println("ERROR: Modalità input non disponibile");
		riempiInitial();
	}

	/**
	 * 	Valida l'input, controllando se è nel range accettato da max_value
	 *	@param s	Input da validare
	 *
	 *	@return		true sse input è valido
	 */
	public boolean validateInput(String s) {
		Object tc = fromString(s);
		
		if (compareTo(tc, maxInRangeElement()) > 0
				|| compareTo(tc, minInRangeElement()) < 0) {
			// System.out.println("Comparison: "+ compareTo(tc,
			// minInRangeElement()));
			System.out.println("ERROR: fuori range");
			return false;
		}

		if (isString() && (!s.matches("[a-z]+"))) {
			System.out.println("ERROR: Solo lettere, grazie");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param n	Il valore del dato (in Integer)
	 * @return	L'oggetto costruito a partire dal dato
	 */
	public Object fromInteger(Integer n) {
		if (this.isInteger())
			return n;
		else if (this.isDouble())
			return new Double((double) n);
		else
			return String.valueOf(n);
	}
	
	/**
	 * 
	 * @param x	Il valore del dato (in Double)
	 * @return	L'oggetto costruito a partire dal dato
	 */
	public Object fromDouble(Double x) {
		if (this.isInteger())
			return new Integer(x.intValue());
		else if (this.isDouble())
			return (x);
		else
			return String.valueOf(x);
	}
	
	/**
	 * 
	 * @param s	Il valore del dato (in Stringa)
	 * @return	L'oggetto costruito a partire dal dato
	 */
	public Object fromString(String s) {
		if (this.isInteger())
			return new Integer(s);
		else if (this.isDouble())
			return new Double(s);
		else
			return s;
	}

	/**
	 * 
	 * @return	true sse tipo di dato è Intero
	 */
	public boolean isInteger() {
		return type.equals("Interi");
	}

	/**
	 * 
	 * @return true sse tipo di dato è Double
	 */
	public boolean isDouble() {
		return type.equals("Reali");
	}

	/**
	 * 
	 * @return true sse tipo di dato è Stringa
	 */
	public boolean isString() {
		return type.equals("Stringhe");
	}

	/**
	 * 
	 * @param t1	Il primo oggetto da confrontare
	 * @param t2	Il secondo oggetto da confrontare
	 * @return	0 se gli oggetti sono uguali, 1 se t1 > t2, -1 se t2 > t1
	 */
	public int compareTo(Object t1, Object t2) {
		if (isInteger()) {
			return ((Integer) t1).compareTo((Integer) t2);
		} else if (isDouble()) {
			return ((Double) t1).compareTo((Double) t2);
		} else {
			return t1.toString().compareTo(t2.toString());
		}
	}
}
