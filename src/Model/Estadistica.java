package Model;

import java.util.HashMap;

public class Estadistica {
	Integer numeroTotal;
	Double coste;
	HashMap<String, Estadistica> distribucion;

	public Estadistica(Integer numeroTotal, Double coste, HashMap<String, Estadistica> distribucion) {
		this.numeroTotal = numeroTotal;
		this.coste = coste;
		this.distribucion = distribucion;
	}

	public Estadistica(Integer numeroTotal, Double coste) {
		this.numeroTotal = numeroTotal;
		this.coste = coste;
		this.distribucion = null;
	}
	
	public Estadistica() {
	}

	public Integer getNumeroTotal() {
		return numeroTotal;
	}

	public void setNumeroTotal(Integer numeroTotal) {
		this.numeroTotal = numeroTotal;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public HashMap<String, Estadistica> getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(HashMap<String, Estadistica> distribucion) {
		this.distribucion = distribucion;
	}

}
