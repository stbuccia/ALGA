package model;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Algoritmo {
	
	private boolean byStep, inPausa, interrompi;
	private Scanner keyboard = null;
	private int delay=50;
	
	public Algoritmo(){
		keyboard = new Scanner(System.in);
		this.setByStep(true);
		inPausa=false;
		this.setInterrompi(false);
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
		do{
			boolean isPressed = false;
			if (byStep){
				while(!isPressed){
					isPressed = keyboard.nextLine().isEmpty();
				}
			}
			else{
	        		try {
	        			TimeUnit.MILLISECONDS.sleep(delay);
	        		} catch(InterruptedException e) {
	        			e.printStackTrace();
	        		}
			}			
		}while(inPausa);
		in.stampaItems();
	}

	
	public void setDelay(int n){
		this.delay=n;
	}
	
	public void setByStep(boolean cond){
		this.byStep=cond;
	}
	
	public void setInPausa(){
		this.inPausa=!inPausa;
	}
	
	public void setInterrompi(boolean cond){
		this.interrompi=cond;
	
	}
}
