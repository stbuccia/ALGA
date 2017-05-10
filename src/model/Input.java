package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Input{
	
	public Object[] items;
	private String path=""; // Percorso al file sorgente dei dati
	private Integer max_val;
	private String mode, type;
	
	public void setMaxVal(Integer n){
		this.max_val=n;
	}
	
	public void setPath(String s){
		this.path=s;
	}
	
	public void setMode(String c){
		this.mode=c;
	}
	
	public Integer getMaxVal(){
		return max_val;
	}
	
	public Input(int n, String tipo){
		this.items=new Object[n];
		this.type=tipo;
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
		Object tc = fromString(s);
		if(!isString() && toCompare(tc, fromInteger(max_val)) > 0 ||
			isString() && toCompare(tc, fromString("zzzzzzzzzz")) > 0) {
			System.out.println("fuori range");
			return false;
		}
		return true;
	}

	private Object nullElement(){
		if (this.isInteger()) return new Integer(0);
		else if (this.isDouble()) return new Double(0.0);
		else return String.valueOf("");
	}
	
	private Object maxInRangeElement(){
		if(this.isInteger()) return fromInteger(max_val);
		else if(this.isDouble()) return fromDouble((double)(max_val));
		else return(fromString("zzzzzzzzzz"));
	}
	
	public Object fromInteger(Integer n){
		if (this.isInteger()) return n;
		else if (this.isDouble()) return new Double((double)n);
		else return String.valueOf(n);
	}
	
	public Object fromDouble(Double x){
		if (this.isInteger()) return new Integer(x.intValue());
		else if (this.isDouble()) return (x);
		else return String.valueOf(x);
	}
	
	public Object fromString(String s){
		if (this.isInteger()) return new Integer(s);
		else if (this.isDouble()) return new Double(s);
		else return s;
	}
	
	public boolean isInteger(){
		return type.equals("Interi");
	}
	
	public boolean isDouble(){
		return type.equals("Reali");
	}
	
	public boolean isString(){
		return type.equals("Stringhe");
	}
	
	public int toCompare(Object t1, Object t2){
		if (isInteger()){
			return ((Integer)t1).compareTo((Integer)t2);
		}
		else if (isDouble()){
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
