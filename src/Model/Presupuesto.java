package Model;

import java.util.ArrayList;
import java.util.Date;

public class Presupuesto {

	private Integer identificador;
	private String empresa;
	private Double presupuesto;
	private Date fechaInicio;
	private Integer duracion;
	private ArrayList<String> material;
	private Integer personal;
	
	//Constructor
	//vacío para construcción con setters
	public Presupuesto() {
		
	}
	
	//Con todos los parametros
	public Presupuesto(Integer identificador, String empresa, Double presupuesto, Date fechaInicio, Integer duracion,
			ArrayList<String> material, Integer personal) {
		this.identificador = identificador;
		this.empresa = empresa;
		this.presupuesto = presupuesto;
		this.fechaInicio = fechaInicio;
		this.duracion = duracion;
		this.material = material;
		this.personal = personal;
	}

	//Getters y Setters
	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public ArrayList<String> getMaterial() {
		return material;
	}

	public void setMaterial(ArrayList<String> material) {
		this.material = material;
	}

	public Integer getPersonal() {
		return personal;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
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
		Presupuesto other = (Presupuesto) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String cadena = "\tPresupuesto{\n"
				+ "\t\tidentificador=" + identificador + ",\n"
				+ "\t\tempresa=" + empresa + ",\n"
				+ "\t\tpresupuesto=" + presupuesto+",\n"
				+ "\t\tfechaInicio=" + fechaInicio + ",\n"
				+ "\t\tduracion=" + duracion + ",\n";
		if(material != null)
			cadena +="\t\tmaterial=" + material.toString() + ",\n";
		else
			cadena +="\t\tmaterial=" + material + ",\n";
		cadena+= "\t\tpersonal="+ personal 
				+ "\n\t\t}";
		return cadena;
	}
	
	
	
		
	
	
}
