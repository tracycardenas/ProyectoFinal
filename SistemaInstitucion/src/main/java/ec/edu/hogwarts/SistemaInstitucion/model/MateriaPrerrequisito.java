package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_materia_prerrequisito")
public class MateriaPrerrequisito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mat_pre_codigo")
	private int id;
	
	@Column(name = "mat_pre_cod_req")
	private Materia materiaRequerida;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Materia getMateriaRequerida() {
		return materiaRequerida;
	}
	public void setMateriaRequerida(Materia materiaRequerida) {
		this.materiaRequerida = materiaRequerida;
	}
	
}
