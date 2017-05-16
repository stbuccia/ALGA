package model;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class Algoritmo<Void> extends Task<Void> {

	private Input input;
	public Rects rectangle;
	private boolean inPausa;
	private int delay = 0;
	private boolean isPressed = false;
	private int pivotIndex = 0, currentIndex = 0;
	private int firstToSwitch = 0, secondToSwitch = 0;

	public Algoritmo(Input i) {
		inPausa = false;
		input = i;
		delay = i.getDelay();
	}

	public int getPivotIndex() {
		return this.pivotIndex;
	}

	public int getCurrentIndex() {
		return this.currentIndex;
	}

	public void creaRects(double x, double y) {
		rectangle = new Rects(input, x, y);
		rectangle.setWidth(input.items.length);
		rectangle.setHeights(input);
	}

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
	}

	private int pivot(int primo, int ultimo) {
		int j = primo;
		Object p = input.items[primo];
		Object temp;
		rectangle.setPivotH(p, input);
		this.pivotIndex = primo;
		updateCanvas();
		for (int i = primo; i <= ultimo; i++) {
			if (input.compareTo(input.items[i], p) < 0) {
				if (isCancelled())
					return 0;
				this.currentIndex = i;
				tick();
				j++;
				temp = input.items[i];
				input.items[i] = input.items[j];
				input.items[j] = temp;
				rectangle.switchRect(i, j);
				
				setSwitch(i, j);
				updateMessage(this.getItems() + "\nScambio "
						+ input.items[i] + " con "
						+ input.items[j] + " (pivot: "
						+ input.items[pivotIndex] + ")");
				updateCanvas();
			}
		}
		
		input.items[primo] = input.items[j];
		input.items[j] = p;
		rectangle.switchRect(primo, j);
		this.pivotIndex = j;
		
		setSwitch(primo, j);
		updateMessage("Il pivot diventa " + input.items[j]);
		updateCanvas();
		return j;
	}

	public String getItems() {
		String s = "";
		for (int i = 0; i < input.items.length; i++)
			s += input.items[i] + " ";
		return s;
	}

	// Esegue ogni iterazione
	private void tick() {
		do {
			if (input.getByStep()) {
				while (!isPressed) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						System.out.println("ERROR: sleep interrupted");
					}
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(delay);
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
		this.firstToSwitch = i;
		this.secondToSwitch = j;
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
	
	public int getFirstToSwitch(){
		return firstToSwitch;
	}

	public int getSecondToSwitch(){
		return secondToSwitch;
	}
}
