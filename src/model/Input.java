package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Input <T>{
	
	public T[] items; //Attenzione è public
	final Class<T> type;
	private String path=""; // Percorso al file sorgente dei dati
	private Integer max_val;
	private String mode;
	
	public void setMaxVal(Integer n){
		this.max_val=n;
	}
	
	public void setPath(String s){
		this.path=s;
	}
	
	public void setMode(String c){
		this.mode=c;
	}
	
	public Input(int n, Integer max_val, Class<T> tipo){
		this.items=(T[])(new Object[n]);
		this.type=tipo;
		this.setMaxVal(max_val);
		this.setPath("/home/buccia/file.txt");
		//this.setMode(mode);
	}
	
	public void riempiItems(){
		if (this.mode.equals("Casuale"))
			this.riempiRandItems();
		else if (this.mode.equals("Tastiera"))
			this.riempiKeyItems();
		else if (this.mode.equals("File"))
			this.getFromFile();
		else 
			System.out.println("Modalità input non disponibile");
	}
	
	private void riempiRandItems(){
		for (int i=0; i<items.length; i++){
			if (!this.isString())
				items[i]=fromDouble(Math.random()*max_val);
			else{
				int lunghezza=(int)(Math.random()*10+1); //massimo 10 caratteri
				String s="";
				for (int j=0; j<lunghezza; j++)
					s+=(char)(Math.random()*26+97); //Stringhe con solo lettere minuscole
				items[i]=fromString(s);
			}
		}
	}
	
	private void riempiKeyItems(){
		Scanner keyboard = new Scanner(System.in);
		for (int i=0; i<items.length; i++){
			try{
				String s=keyboard.next();
				if (validateInput(s)) items[i]=this.fromString(s);
				else i--;
			}catch(NumberFormatException e){
				System.out.println("L'input non è del tipo richiesto");
				i--;
			}
		}
		keyboard.close();
	}
	
	private void getFromFile() {
		
		String l = "";
		System.out.println(this.path);
		BufferedReader reader = null;
		File source = null;
		
		try {
			source = new File(this.path);
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
			if (l!=null){
				l = l.trim();
				if(validateInput(l)) {
					items[i]=fromString(l); 
				} else {
					items[i] = maxInRangeElement();
				}
			}
			else items[i]=nullElement();
		} catch (Exception e) {
			items[i]=nullElement();
		} 
	}
	
	// valida l'input, controllando se è nel range accettato da max_value
	private boolean validateInput(String s) {
		T tc = fromString(s);
		if(!isString() && toCompare(tc, fromInteger(max_val)) > 0 ||
			isString() && toCompare(tc, fromString("zzzzzzzzzz")) > 0) {
			System.out.println("fuori range");
			return false;
		}
		return true;
	}

	private T nullElement(){
		if (this.isInteger()) return (T)new Integer(0);
		else if (this.isDouble()) return (T)new Double(0.0);
		else return (T)String.valueOf("");
	}
	
	private T maxInRangeElement(){
		if(this.isInteger()) return fromInteger(max_val);
		else if(this.isDouble()) return fromDouble((double)(max_val));
		else return(fromString("zzzzzzzzzz"));
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
	
	public int getItemHeight(){
		int out = 0;
		return out;
	}
}
