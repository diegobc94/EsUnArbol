package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import algoritmos.DFS;

public class GrafoNPD {
	private int cantNodos;
	private int cantAristas;
	private int[][] grafo;
	private int[] aristasEntrantes;
	private boolean cumpleRegla1;
	private boolean cumpleRegla2;
	private boolean cumpleRegla3;
	private int nodoRaiz;
	
	public GrafoNPD(String path) throws FileNotFoundException{
		File file = new File(path);
		Scanner sc = new Scanner(file);
		cantNodos = sc.nextInt();
		cantAristas = sc.nextInt();
		grafo = new int[cantNodos][cantNodos];
		aristasEntrantes = new int[cantNodos];
		while(sc.hasNext()) {
			int origen = sc.nextInt();
			int destino = sc.nextInt();
			grafo[origen-1][destino-1] = 1;
			aristasEntrantes[destino-1]++;
		}
		sc.close();
	}
	
	public int getNodoRaiz() {
		return nodoRaiz;
	}
	
	public int getAristasEntrantes(int destino) {
		return aristasEntrantes[destino];
	}
	
	public int getCantNodos() {
		return cantNodos;
	}
	
	public int hallarRaiz() {
		int raiz = -1;
		for(int i=0; i<cantNodos; i++) 
			if(aristasEntrantes[i]==0)
				raiz = i+1;
		return raiz;
	}
	
	public String regla1() {
		StringBuffer sb = new StringBuffer();
		int nodosQueNoLleganArcos = 0;
		String raiz = null;
		for(int i=0; i<cantNodos; i++) {
			if(aristasEntrantes[i]==0) {
				sb.append((i+1)+" ");
				raiz = Integer.toString(i+1);
				nodosQueNoLleganArcos++;
			}
		}
		switch (nodosQueNoLleganArcos) {
		case 0:
			return "0";
		case 1:
			cumpleRegla1 = true;
			return raiz;
		default:
			return sb.toString();
		}
		
	}
	
	public String regla2() {
		StringBuffer sb = new StringBuffer();
		int cantNodosQueNoCumplen = 0;
		for(int i=0; i<cantNodos; i++) {
			if(aristasEntrantes[i]>1) {
				sb.append((i+1)+" ");
				cantNodosQueNoCumplen++;
			}
		}
		if(cantNodosQueNoCumplen==0) {
			cumpleRegla2 = true;
			return "0";
		}else {
			return sb.toString();
		}
	}
	
	public String regla3() {
		nodoRaiz = hallarRaiz();
		if(nodoRaiz==-1)
			return "0";
		StringBuffer sb = new StringBuffer();
		DFS dfs = new DFS(this,nodoRaiz);
		dfs.ejecutar();
		cumpleRegla3 = true;
		for(int i=0; i<cantNodos; i++) {
			if(!dfs.fueVisitado(i+1)) {
				String nodo = Integer.toString(i+1);
				sb.append(nodo);
				cumpleRegla3 = false;
			}
		}
		if(cumpleRegla3)
			return "0";
		else 
			return sb.toString();
	}

	public ArrayList<Integer> getAdyacentes(int origen) {
		ArrayList<Integer> ady = new ArrayList<Integer>();
		for(int i=0; i<cantNodos; i++) {
			if(grafo[origen][i]==1) {
				ady.add(i+1);
			}
		}
		return ady;
	}

	public void escribirSolucion(String pathSalida) throws IOException {
		FileWriter fw = new FileWriter(pathSalida);
		PrintWriter pw = new PrintWriter(fw);
		String salidaRegla1 = regla1();
		String salidaRegla2 = regla2();
		String salidaRegla3 = regla3();
		if(cumpleRegla1 && cumpleRegla2 && cumpleRegla3) {
			pw.print("Si "+nodoRaiz);
		}else {
			pw.println("No");
			pw.println(salidaRegla1);
			pw.println(salidaRegla2);
			pw.println(salidaRegla3);
		}
		pw.close();
	}

	
}
