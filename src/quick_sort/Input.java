package quick_sort;

import java.lang.Math;
import java.util.Scanner;

public class Input {
	
	public Object[] items; //Attenzione è public
	
	// type mi indica il tipo (forse non è il modo migliore per farlo) D: double, I: integer, S: string
	// mode lamodalità di input R: randomic, K: da tastiera, f: da file
	public Input(int n, int max_val, char type, char mode){
		this.items=new Object[n];
		if (mode=='R')
			this.riempiRandItems(max_val, type);
		else if (mode=='K')
			this.riempiKeyItems(max_val, type);
		//TODO:Implementare altri tipi di input
	}
	
	private void riempiRandItems(int max_val, char type){
		if (type=='D')
			for (int i=0; i<items.length; i++)
				items[i]=new Double(Math.random()*max_val);
		else if (type=='I')
			for (int i=0; i<items.length; i++)
				items[i]=new Integer((int)(Math.random()*max_val));
		else if (type=='S'){
			for (int i=0; i<items.length; i++){
				int lunghezza=(int)(Math.random()*10+1);//massimo 10 caratteri
				String s="";
				for (int j=0; j<lunghezza; j++)
					s+=(char)(Math.random()*26+97);//Stringhe con solo lettere minuscole
				items[i]=s;
			}
		}
	}
	
	private void riempiKeyItems(int max_val, char type){
		Scanner keyboard = new Scanner(System.in);
		for (int i=0; i<items.length; i++){
			if (type=='I') items[i]=keyboard.nextInt();
			else if (type=='D') items[i]=keyboard.nextDouble();
			else if (type=='S') items[i]=keyboard.next();
		}
		keyboard.close();
	}
	
	public void stampaItems(){
		for (int i=0; i<items.length; i++)
			System.out.print(items[i]+" ");
		System.out.println();
	}
}
