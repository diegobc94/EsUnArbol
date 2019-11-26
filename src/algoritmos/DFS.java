package algoritmos;

import java.util.Stack;

import grafos.GrafoNPD;

public class DFS {
	
	private GrafoNPD grafo;
	private int origen;
	private boolean visitados[];
	
	public DFS(GrafoNPD grafo, int origen) {
		this.grafo = grafo;
		this.origen = origen;
		visitados = new boolean[grafo.getCantNodos()];
	}
	
	public void ejecutar() {
		Stack<Integer> pila = new Stack<Integer>();
		pila.push(origen);
		visitados[origen-1] = true;
		while(!pila.isEmpty()) {
			int actual = pila.pop();
			for (Integer ady : grafo.getAdyacentes(actual-1)) {
				if(!visitados[ady-1]) {
					visitados[ady-1] = true;
					pila.push(ady);
				}
			}
		}
	}
	
	public boolean fueVisitado(int nodo) {
		return visitados[nodo-1];
	}
}
