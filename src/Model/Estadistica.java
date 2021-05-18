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

	//Equals y HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coste == null) ? 0 : coste.hashCode());
		result = prime * result + ((distribucion == null) ? 0 : distribucion.hashCode());
		result = prime * result + ((numeroTotal == null) ? 0 : numeroTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estadistica other = (Estadistica) obj;
		if (coste == null) {
			if (other.coste != null)
				return false;
		} else if (!coste.equals(other.coste))
			return false;
		if (numeroTotal == null) {
			if (other.numeroTotal != null)
				return false;
		} else if (!numeroTotal.equals(other.numeroTotal))
			return false;
		return true;
	}

	
	
	
}
