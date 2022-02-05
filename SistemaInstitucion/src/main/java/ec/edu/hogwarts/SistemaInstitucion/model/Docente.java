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
	
	@OneToMany(mappedBy = "docente")
	private List<Grupo> grupos;

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	

	
	
}
