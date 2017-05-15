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

	public Algoritmo(Input i) {
		keyboard = new Scanner(System.in);
		inPausa = false;
		input = i;
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
		for (int i = primo; i <= ultimo; i++) {
			this.passoAlgoritmo();
			if (input.compareTo(input.items[i], p) < 0) {
				j++;
				temp = input.items[i];
				input.items[i] = input.items[j];
				input.items[j] = temp;
				rectangle.switchRect(i, j);
				updateMessage("Scambio " + i + " con " + j);
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
		updateMessage("Scambio " + primo + " con " + p);
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
			boolean isPressed = false;
			if (input.getByStep()) {
				while (!isPressed) {
					isPressed = keyboard.nextLine()
							.isEmpty();
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(input
							.getDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (inPausa);
		stampaItems();
	}

	public void setInPausa() {
		this.inPausa = !inPausa;
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
