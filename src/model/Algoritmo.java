package model;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class Algoritmo<Void> extends Task<Void>{

	private Input input;
	public Rects rectangle;
	private boolean inPausa;
	private int delay=0;
	private boolean isPressed=false;

	public Algoritmo(Input i) {
		inPausa = false;
		input = i;
		delay = i.getDelay();
	}

	public void creaRects(double x, double y) {
		rectangle = new Rects(input, x, y);
		rectangle.setWidth(input.items.length);
		rectangle.setHeights(input);
	}

	public void doQuickSort(int primo, int ultimo) {
		if (isCancelled()) return;
		if (primo < ultimo) {
			updateProgress(primo, input.items.length-1);
			
			int k = this.pivot(primo, ultimo);
			
			this.doQuickSort(primo, k - 1);
			updateProgress(k, input.items.length-1);
			
			this.doQuickSort(k + 1, ultimo);
			updateProgress(ultimo, input.items.length-1);
		}
	}
	
	private int pivot(int primo, int ultimo) {
		int j = primo;
		Object p = input.items[primo];
		Object temp;
		rectangle.setPivotH(p, input);
		disegna();
		for (int i = primo; i <= ultimo; i++) {
			if (input.compareTo(input.items[i], p) < 0) {
				if (isCancelled()) return 0;
				this.passoAlgoritmo();
				j++;
				temp = input.items[i];
				input.items[i] = input.items[j];
				input.items[j] = temp;
				rectangle.switchRect(i, j);
				updateMessage("Scambio " + input.items[i] + " con " + input.items[j]+"\n"+this.getItems());
				disegna();
			}
		}
		input.items[primo] = input.items[j];
		input.items[j] = p;
		rectangle.switchRect(primo, j);
		updateMessage("Scambio " + input.items[primo] + " con " + p +"\n"+this.getItems());
		disegna();
		return j;
	}

	public String getItems() {
		String s="";
		for (int i = 0; i < input.items.length; i++)
			s+=input.items[i] + " ";
		return s;
	}

	// Esegue ogni iterazione
	private void passoAlgoritmo() {
		do {
			if (input.getByStep()) {
				while (!isPressed) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						System.out.println("sleep interrupted");
					}
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(delay);
				} catch (InterruptedException e) {
					System.out.println("sleep interrupted");
				}
			}
		} while (inPausa);
		stampaItems();
		isPressed = false;
	}

	private static void disegna(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.qDrawer.drawRects();
			}
		});
	}
	
	public void setInPausa() {
		this.inPausa = !inPausa;
	}
	
	
	public boolean isInPausa() {
		return this.inPausa;
	}
	
	public void setDelay(int n) {
		this.delay = n;
	}
	
	
	public int getDelay() {
		return this.delay;
	}
	
	public void setIsPressed() {
		this.isPressed = true;
	}
	
	
	public void stampaItems() {
		for (int i = 0; i < input.items.length; i++) {
			System.out.print(input.items[i] + " ");
		}
		System.out.println();
	}

	public void dumpRect() {
		for (int j = 0; j < input.items.length; ++j) {
			System.out.println(j + ") " + rectangle.getHeight(j));
		}
	}

	@Override
	protected Void call() throws Exception {
		this.doQuickSort(0, model.Main.i.items.length - 1);
		return null;
	}

}
