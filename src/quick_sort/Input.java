package quick_sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

public class Input {
	
	public Object[] items; //Attenzione è public
	private String path = ""; // Percorso al file sorgente
	
	// type mi indica il tipo (forse non è il modo migliore per farlo) D: double, I: integer, S: string
	// mode lamodalità di input R: randomic, K: da tastiera, F: da file
	public Input(int n, int max_val, char type, char mode, String path){
		this.items=new Object[n];
		this.path = path;
		if (mode=='R')
			this.riempiRandItems(max_val, type);
		else if (mode=='K')
			this.riempiKeyItems(max_val, type);
		else if (mode == 'F')
			this.getFromFile(max_val, type);
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
	
	private void getFromFile(int max_val, char type) {
		
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
					add(l, i, type);
				} catch (Exception nd) {
					nd.printStackTrace();
					System.out.println("Skipping line " + i);
					l = reader.readLine();
					add(l, i, type);
				}
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { reader.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		
	}
	
	private boolean add(String l, int i, char type){
		boolean out = true;
		try {
			System.out.println(l);
			switch (type) {
			case 'I':
				if (l != null) items[i] = Integer.parseInt(l.trim()); else items[i] = 0;
				break;
			case 'D':
				if (l != null) items[i] = Double.parseDouble(l.trim()); else items[i] = 0.0;
				break;
			case 'S':
				// TODO: Da cambiare se decidiamo di ordinare all'interno di una stringa invece che tra strighe
				if(l != null) items[i] = l.trim(); else items[i] = "";
				break;
			default:
				System.out.println("ERRORE: TIPO NON ANCORA IMPLEMENTATO");	
				break;
			}
			out = true;
		} catch (Exception e) {
			switch (type) {
			case 'I':
				items[i] = 0;
				break;
			case 'D':
				items[i] = 0.0;
				break;
			case 'S':
				// TODO: Da cambiare se decidiamo di ordinare all'interno di una stringa invece che tra strighe
				items[i] = "";
				break;
			default:
				System.out.println("ERRORE: TIPO NON ANCORA IMPLEMENTATO");	
				break;
			}
		}
		return out;
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
