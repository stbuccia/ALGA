package quick_sort;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Algoritmo {
	
	private boolean byStep;
	Scanner keyboard = null;
	
	
	public Algoritmo(){
		keyboard = new Scanner(System.in);
		byStep = true;
	}
	
	public <T> void doQuickSort(Input<T> a, int primo, int ultimo){
		if (primo<ultimo){
			int k=this.pivot(a, primo, ultimo);
			this.doQuickSort(a, primo, k-1);
			this.doQuickSort(a, k+1, ultimo);
		}
	}
	
	private <T> int pivot(Input<T> a, int primo, int ultimo){
		int j=primo;
		T p=a.items[primo];
		T temp;
		for (int i=primo; i<=ultimo; i++){
			this.passoAlgoritmo(a);
			if (a.toCompare(a.items[i], p)<0){
				j++;
				temp=a.items[i]; 
				a.items[i]=a.items[j];
				a.items[j]=temp;
			}
		}
		a.items[primo]=a.items[j];
		a.items[j]=p;
		return j;
	}
	
	//Esegue ogni iterazione
	private <T> void passoAlgoritmo(Input<T> in){
		boolean isPressed = false;
		if (byStep){
			while(!isPressed){
				isPressed = keyboard.nextLine().isEmpty();
			}
		}
		else{
        		try {
        			TimeUnit.MILLISECONDS.sleep(50);
        		} catch(InterruptedException e) {
        			e.printStackTrace();
        		}
		}	
	
		in.stampaItems();
	}
}
