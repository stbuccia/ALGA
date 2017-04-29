package quick_sort;

public class Algoritmo {
	public Algoritmo(){
		
	}
	
	public void doQuickSort(Input a, int primo, int ultimo){
		if (primo<ultimo){
			int k=this.pivot(a, primo, ultimo);
			this.doQuickSort(a, primo, k-1);
			this.doQuickSort(a, k+1, ultimo);
		}
	}
	
	private int pivot(Input a, int primo, int ultimo){
		int j=primo;
		Object p=a.items[primo];
		Object temp;
		for (int i=primo; i<=ultimo; i++){
			this.passoAlgoritmo(a);
			if (isMin(a.items[i],p)){
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
	
	private boolean isMin(Object a, Object b){ //a e b stesso tipo
		if (a instanceof Integer){
			return (int)a<(int)b;
		}
		else if (a instanceof Double){
			return (double)a<(double)b;
		}
		else{ //Per forza una stringa
			return a.toString().compareToIgnoreCase(b.toString())<0;
		}
	}
	
	//Esegue ogni iterazione
	private void passoAlgoritmo(Input in){
		
		try{
			Thread.sleep(50); //aspetta 0.05 s
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		in.stampaItems();
	}
}
