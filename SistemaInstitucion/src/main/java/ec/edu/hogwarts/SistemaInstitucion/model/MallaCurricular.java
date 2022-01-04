package ec.edu.hogwarts.SistemaInstitucion.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mallaCurricular")
public class MallaCurricular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "malla_id")
	private int id;
	
	@Column(name = "malla_descripcion")
	private String descripcion;
	
	@Column(name = "malla_niveles")
	private int niveles;
	
	private List<Materia> materias;
	
	

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNiveles() {
		return niveles;
	}

	public void setNiveles(int niveles) {
		this.niveles = niveles;
	}
	
	

}
