package ec.edu.hogwarts.SistemaInstitucion.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_docentes")
public class Docente extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "doc_especialidad")
	private String especialidad;
	
	@OneToMany
	@JoinColumn(name = "cod_docente")
	private List<Grupo> gruposClase;

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Grupo> getGruposClase() {
		return gruposClase;
	}

	public void setGruposClase(List<Grupo> gruposClase) {
		this.gruposClase = gruposClase;
	}
	
}
