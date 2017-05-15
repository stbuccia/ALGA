package model;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class Algoritmo<Void> extends Task<Void>{

	private Input input;
	public Rects rectangle;
	private boolean inPausa;
	private Scanner keyboard = null;
	private int delay=0;
	private boolean isPressed=false;

	public Algoritmo(Input i) {
		keyboard = new Scanner(System.in);
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
		if (primo < ultimo) {
			int k = this.pivot(primo, ultimo);
			this.doQuickSort(primo, k - 1);
			this.doQuickSort(k + 1, ultimo);
		}
	}
	
	private int pivot(int primo, int ultimo) {
		
		int j = primo;
		Object p = input.items[primo];
		Object temp;
		rectangle.setPivotH(p, input);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.qDrawer.drawRects();
				
			}
		});
		for (int i = primo; i <= ultimo; i++) {
			if (input.compareTo(input.items[i], p) < 0) {
				this.passoAlgoritmo();
				j++;
				temp = input.items[i];
				input.items[i] = input.items[j];
				input.items[j] = temp;
				rectangle.switchRect(i, j);
				updateMessage("Scambio " + input.items[i] + " con " + input.items[j]);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.qDrawer.drawRects();
						
					}
				});
			}
		}
		input.items[primo] = input.items[j];
		input.items[j] = p;
		rectangle.switchRect(primo, j);
		updateMessage("Scambio " + input.items[primo] + " con " + p);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.qDrawer.drawRects();
				
			}
		});
		return j;
	}

	// Esegue ogni iterazione
	private void passoAlgoritmo() {
		do {
			if (input.getByStep()) {
				while (!isPressed) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (inPausa);
		stampaItems();
		isPressed = false;
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

//	public void sendItemsToConsole() {
//		for (int i = 0; i < input.items.length; i++) {
//			Main.qDrawer.toConsole(input.items[i] + " ");
//		}
//	}

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
