package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_carrera")
public class Carrera implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carr_id")
	private int id;
	@Column(name = "carr_nombre")
	private String nombre;
	@Column(name = "carr_descripcion")
	private String descripcion;
	@OneToMany
	@JoinColumn(name="carr_id")
	private List<MallaCurricular> mallas;
	@OneToMany
	@JoinColumn(name="carr_id")
	private List<Inscripcion> inscripciones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<MallaCurricular> getMallas() {
		return mallas;
	}

	public void setMallas(List<MallaCurricular> mallas) {
		this.mallas = mallas;
	}
	
}
