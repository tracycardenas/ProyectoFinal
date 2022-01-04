package ec.edu.hogwarts.SistemaInstitucion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_grupos")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grup_id")
	private int id;
	
	@Column(name = "grup_nombre")
	private String nombre;
	
	@Column(name = "grup_cupos")
	private int cupos;
	
	@Column(name = "grup_costo")
	private double costo;
	
	@Column(name = "grup_modalidad")
	private String modalidad;
	
	private Materia materia;
	private Usuario docente;
	private EspacioFisico espacioFisico;
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
	public int getCupos() {
		return cupos;
	}
	public void setCupos(int cupos) {
		this.cupos = cupos;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Usuario getDocente() {
		return docente;
	}
	public void setDocente(Usuario docente) {
		this.docente = docente;
	}
	public EspacioFisico getEspacioFisico() {
		return espacioFisico;
	}
	public void setEspacioFisico(EspacioFisico espacioFisico) {
		this.espacioFisico = espacioFisico;
	}
	
	

}
