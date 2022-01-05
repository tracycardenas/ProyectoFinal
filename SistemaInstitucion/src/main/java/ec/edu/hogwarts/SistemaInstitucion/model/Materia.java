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
@Table(name = "tbl_materias")
public class Materia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mat_id")
	private int id;
	
	@Column(name = "mat_nombre")
	private String nombre;
	
	@Column(name = "mat_nivel")
	private int nivel;
	
	@Column(name = "mat_docencia")
	private int h_docencia;
	
	@Column(name = "mat_practica")
	private int h_practica;
	
	@Column(name = "mat_autonomo")
	private int h_autonomo;
	
	@Column(name = "mat_periodo")
	private int periodo;
	
	@Column(name = "mat_matricula")
	private int n_matricula;
	
	@Column(name = "mat_estado")
	private String estado;
	
	@OneToMany
	@JoinColumn(name="mat_req")
	private List<Materia> prerequisitos;

	public List<Materia> getPrerequisitos() {
		return prerequisitos;
	}

	public void setPrerequisitos(List<Materia> prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getH_docencia() {
		return h_docencia;
	}

	public void setH_docencia(int h_docencia) {
		this.h_docencia = h_docencia;
	}

	public int getH_practica() {
		return h_practica;
	}

	public void setH_practica(int h_practica) {
		this.h_practica = h_practica;
	}

	public int getH_autonomo() {
		return h_autonomo;
	}

	public void setH_autonomo(int h_autonomo) {
		this.h_autonomo = h_autonomo;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getN_matricula() {
		return n_matricula;
	}

	public void setN_matricula(int n_matricula) {
		this.n_matricula = n_matricula;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
