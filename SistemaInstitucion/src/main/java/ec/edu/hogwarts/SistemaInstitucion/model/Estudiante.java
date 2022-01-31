package ec.edu.hogwarts.SistemaInstitucion.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Estudiante extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "est_representante")
	private String representante;
	
	@OneToMany
	@JoinColumn(name = "est_cedula")
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
