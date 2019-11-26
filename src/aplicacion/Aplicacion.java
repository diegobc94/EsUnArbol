package aplicacion;

import java.io.IOException;

import grafos.GrafoNPD;

public class Aplicacion {
	public static void main(String[] args) throws IOException {
		String pathEntrada = "C:\\Users\\Diego\\eclipse-workspace\\EsUnArbol\\entrada01.in";
		String pathSalida = "C:\\Users\\Diego\\eclipse-workspace\\EsUnArbol\\salida.01.out";
		GrafoNPD grafo = new GrafoNPD(pathEntrada);
		grafo.escribirSolucion(pathSalida);
	}
}
