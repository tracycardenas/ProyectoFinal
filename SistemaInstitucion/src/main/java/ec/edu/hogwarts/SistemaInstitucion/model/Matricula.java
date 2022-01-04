package ec.edu.hogwarts.SistemaInstitucion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_matriculas")
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "matri_id")
	private int id;
	
	private Usuario estudiante;
	private Grupo grupo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	
	

}
