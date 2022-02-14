package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Estudiante extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "est_representante")
	private String representante;
	
	@OneToMany(mappedBy = "estudiante" ,orphanRemoval= true)
	private List<Calificacion> calificacioness;

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public List<Calificacion> getCalificacioness() {
		return calificacioness;
	}

	public void setCalificacioness(List<Calificacion> calificacioness) {
		this.calificacioness = calificacioness;
	}
	
}
