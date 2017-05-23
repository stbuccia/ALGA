package model;

import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.concurrent.Task;

/** *
 * @param <Void>
 * Classe che continene la logica per eseguire il QuickSort. Estende la classe task, così da avere un servizio indipendente quando si
 * istanzia la classe. Contiene anche la logica per richiedere un aggiornamento dei componenti della finestra, per mostrare
 * l'esecuzione dell'algoritmo a ogni iterazione.
 */

@SuppressWarnings("hiding")
public class Algoritmo<Void> extends Task<Void> {
	
	/**
	 * Oggetto che tiene le informazioni su tutti i rettangoli necessari nella view
	 *
	 */
	public Rects rectangle;
	
	private Input input;
	private boolean inPausa;
	private boolean isPressed = false;
	private int pivotIndex = 0, currentIndex = 0, currentJ = 0;
	private int firstSwitched = 0, secondSwitched = 0;
	
	/**
	 * 
	 * @param i	Oggetto di classe Input, contentente tutti i dettagli per l'esecuzione, 
	 * tra cui i dati da ordinare, la modalità di esecuzione, ed un eventuale ritardo automatico
	 * @see model.Input
	 */
	public Algoritmo(Input i) {
		inPausa = false;
		input = i;
	}
	
	@Override
	protected Void call() throws Exception {
		this.doQuickSort(0, model.Main.i.items.length - 1);
		updateMessage(this.getItems()+"\nFinito!");
		return null;
	}

	/**
	 * 
	 * @return L'indice del pivot attuale
	 */
	public int getPivotIndex() {
		return this.pivotIndex;
	}
	
	/**
	 * 
	 * @return l'indice del primo elemento da scambiare
	 */
	public int getCurrentIndex() {
		return this.currentIndex;
	}
	
	/**
	 * 
	 * @return l'indice del secondo elemento da scambiare
	 */
	public int getCurrentJ() {
		return this.currentJ;
	}
	
	/**
	 * 
	 * @param x	Larghezza del pannello su cui i rettangoli andranno disegnati
	 * @param y	Altezza del pannello su cui i rettagnoli andranno disegnati
	 * 
	 * Si occupa della creazione dei dati dei rettangoli che andranno disegnati, calcolando di ognuno la sua altezza e la sua larghezza
	 * 
	 */
	public void creaRects(double x, double y) {
		rectangle = new Rects(input, x, y);
		rectangle.setWidth(input.items.length);
		rectangle.setHeights(input);
	}
	
	/**
	 * 
	 * @param primo
	 * @param ultimo
	 * 
	 * È dove succede la magia. Ordina l'array dalla posizione di primo alla posizione di ultimo
	 */
	public void doQuickSort(int primo, int ultimo) {
		if (isCancelled())
			return;
		if (primo < ultimo) {
				updateProgress(primo, input.items.length - 1);
			int k = this.pivot(primo, ultimo);
			this.doQuickSort(primo, k - 1);
				updateProgress(k, input.items.length - 1);
			this.doQuickSort(k + 1, ultimo);
				updateProgress(ultimo, input.items.length - 1);
		}
		stampaItems();
	}

	private int pivot(int primo, int ultimo) {
		int j = primo;
		Object p = input.items[primo];
		Object temp;
			pivotChanged(primo);
			updateMessage(this.getItems()+"\nIl pivot è diventato " + input.items[primo]);
		for (int i = primo; i <= ultimo; i++) {
			if (input.compareTo(input.items[i], p) < 0) {
					if (isCancelled())
						return 0;
					this.currentIndex = i;
					this.currentJ = j+1;
					tick();
				j++;
				temp = input.items[i];
				input.items[i] = input.items[j];
				input.items[j] = temp;
					switchedItems(i, j);
					updateMessage(this.getItems() + "\nScambiati "+ input.items[j] + " con "+ input.items[i] + " (pivot: "+ input.items[pivotIndex] + ")");
			}
		}
			this.currentIndex = primo;
			this.currentJ = j;
			tick();
		input.items[primo] = input.items[j];
		input.items[j] = p;
			switchedItems(primo, j);
			pivotChanged(primo);
			updateMessage(this.getItems() +"\nScambiati " + input.items[primo] + " con " + input.items[j] + "\nIl pivot è diventato " + input.items[primo]);
		return j;
	}
	
	private void switchedItems(int i, int j){
		rectangle.switchRect(i, j);
		setSwitch(i, j);
		updateCanvas();
	}
	
	private void pivotChanged(int n){
		this.pivotIndex = n;
		rectangle.setPivotH(input.items[n], input);
		updateCanvas();
	}
	
	/**
	 *
	 * @return Gli elementi dell'array in forma di stringa, tutti su un'unica riga
	 */
	public String getItems() {
		String s = "";
		for (int i = 0; i < input.items.length; i++)
			s += input.items[i] + " ";
		return s;
	}

	private void tick() {
		do {
			if (input.getByStep()) {
				while (!isPressed) {
					try {
						TimeUnit.MILLISECONDS.sleep(Main.u.pref_delay);
					} catch (InterruptedException e) {
						System.out.println("ERROR: sleep interrupted");
					}
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(Main.i.getDelay());
				} catch (InterruptedException e) {
					System.out.println("ERROR: sleep interrupted");
				}
			}
		} while (inPausa);
		stampaItems();
		isPressed = false;
	}

	private static void updateCanvas() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.qDrawer.drawRects();
			}
		});
	}

	private void setSwitch(int i, int j) {
		this.firstSwitched = i;
		this.secondSwitched = j;
	}
	
	/**
	 * Ferma o fa riprendere l'esecuzione dell'algoritmo
	 */
	public void setInPausa() {
		this.inPausa = !inPausa;
	}
	
	/**
	 * @return true se l'algoritmo è in pausa alla chiamata della funzione
	 */
	public boolean isInPausa() {
		return this.inPausa;
	}

	/**
	 * Consente l'esecuzione di un singolo passo dell'algoritmo, nel momento in cui questo non viene eseguito automaticamente
	 */
	public void setIsPressed() {
		this.isPressed = true;
	}

	/**
	 * Funzione di debug. Stampa a console lo stato corrente dell'array
	 */
	public void stampaItems() {
		for (int i = 0; i < input.items.length; i++) {
			System.out.print(input.items[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 
	 * @return Il primo elemento scambiato nel giro precedente dell'algoritmo
	 */
	public int getFirstSwitched(){
		return firstSwitched;
	}

	/**
	 * 
	 * @return Il secondo elemento scambiato nel giro precedente dell'algoritmo
	 */
	public int getSecondSwitched(){
		return secondSwitched;
	}
}
