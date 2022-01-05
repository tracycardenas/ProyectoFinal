package ec.edu.hogwarts.SistemaInstitucion.model;

import javax.persistence.Entity;

@Entity
public class Estudiante extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String representante;

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	
}
