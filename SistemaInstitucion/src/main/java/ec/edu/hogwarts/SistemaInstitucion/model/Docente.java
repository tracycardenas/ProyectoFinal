package ec.edu.hogwarts.SistemaInstitucion.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_docentes")
public class Docente extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidad;

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
