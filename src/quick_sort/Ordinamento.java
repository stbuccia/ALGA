package quick_sort;

public class Ordinamento {
	public static void main(String[] args){
		int n=10;
		Algoritmo a=new Algoritmo();
		Input<Integer> i =new Input<Integer> (n, 100, 'R', Integer.class);
		i.stampaItems();
		a.doQuickSort(i, 0, n-1);
		i.stampaItems();
	}
}
