package quick_sort;

public class Ordinamento {
	public static void main(String[] args){
		int max=100;
		int n=20;
		Algoritmo a=new Algoritmo();
		Input i=new Input(n, max, 'S', 'R');
		i.stampaItems();
		a.doQuickSort(i, 0, n-1);
		i.stampaItems();
	}
}
