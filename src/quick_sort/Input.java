package quick_sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.Scanner;


public class Input <T>{
	
	public T[] items; //Attenzione è public
	final Class<T> type; //final cioè non cambia (?)
	private String path = "/home/buccia/file.txt"; // Percorso al file sorgente
	
	public Input(int n, Integer max_val, char mode, Class<T> tipo){
		this.items=(T[])(new Object[n]);
		this.type=tipo;
		if (mode=='R')
			this.riempiRandItems(max_val);
		else if (mode=='K')
			this.riempiKeyItems(max_val);
		else if (mode=='F')
			this.getFromFile(max_val);
		//TODO:Implementare altri tipi di input
	}
	
	private void riempiRandItems(Integer max_val){
		for (int i=0; i<items.length; i++){
			if (!this.isString())
				items[i]=fromDouble(Math.random()*max_val);
			else{
				int lunghezza=(int)(Math.random()*10+1);//massimo 10 caratteri
				String s="";
				for (int j=0; j<lunghezza; j++)
					s+=(char)(Math.random()*26+97);//Stringhe con solo lettere minuscole
				items[i]=fromString(s);
			}
		}
	}
	
	private void riempiKeyItems(Integer max_val){
		Scanner keyboard = new Scanner(System.in);
		boolean toolarge;
		for (int i=0; i<items.length; i++){
			toolarge=false;
			try{
				items[i]=this.fromString(keyboard.next());
				if (!isString()) toolarge=(toCompare(items[i], fromInteger(max_val))>0);
				else toolarge=(toCompare(items[i], fromString("zzzzzzzzzz"))>0);
				if (toolarge){
					System.out.println("L'input troppo grande");
					i--;
				}
			}catch(NumberFormatException e){
				System.out.println("L'input non è del tipo richiesto");
				i--;
			}
		}
		keyboard.close();
	}
	
	private void getFromFile(int max_val) {
		
		String l = "";
		System.out.println(path);
		BufferedReader reader = null;
		File source = null;
		
		try {
			source = new File(path);
			reader = new BufferedReader(new FileReader(source));
			for	(int i = 0; i < items.length; ++i) {
				try {
					l = reader.readLine();
					add(l, i);
				} catch (Exception nd) {
					nd.printStackTrace();
					System.out.println("Skipping line " + i);
					l = reader.readLine();
					add(l, i);
				}
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { reader.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		
	}

	private void add(String l, int i){
		try {
			System.out.println(l);
			if (l!=null) items[i]=fromString(l.trim());
			else items[i]=nullElement();
		} catch (Exception e) {
			items[i]=nullElement();
		}
	}

	private T nullElement(){
		if (this.isInteger()) return (T)new Integer(0);
		
		else if (this.isDouble()) return (T)new Double(0.0);
		
		else return (T)String.valueOf("");
	}
	
	public T fromInteger(Integer n){
		if (this.isInteger()) return (T)n;
		
		else if (this.isDouble()) return (T)new Double((double)n);
		
		else return (T)String.valueOf(n);
	}
	
	public T fromDouble(Double x){
		if (this.isInteger()) return (T)new Integer(x.intValue());
		else if (this.isDouble()) return (T)(x);
		else return (T)String.valueOf(x);
	}
	
	public T fromString(String s){
		if (this.isInteger()) return (T)new Integer(s);
		else if (this.isDouble()) return (T)new Double(s);
		else return (T)s;
	}
	
	public boolean isInteger(){
		return Integer.class.isAssignableFrom(this.type);
	}
	
	public boolean isDouble(){
		return Double.class.isAssignableFrom(this.type);
	}
	
	public boolean isString(){
		return String.class.isAssignableFrom(this.type);
	}
	
	public int toCompare(T t1, T t2){
		if (Integer.class.isAssignableFrom(this.type)){
			return ((Integer)t1).compareTo((Integer)t2);
		}
		else if (Double.class.isAssignableFrom(this.type)){
			return ((Double)t1).compareTo((Double)t2);
		}
		else{ //Per forza una stringa
			return t1.toString().compareToIgnoreCase(t2.toString());
		}
	}
	
	public void stampaItems(){
		for (int i=0; i<items.length; i++)
			System.out.print(items[i]+" ");
		System.out.println();
	}
}