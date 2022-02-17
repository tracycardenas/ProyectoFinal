package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;


import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_matriculas")
public class Matricula implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "matri_id")
	private int id;
	@Column(name = "matri_estado")
	private boolean estado;	
	@Column(name = "matri_costo_matricula")
	private double costoMatricula;
	@Column(name = "matri_costo_hora")
	private double costoHora;
	@Column(name = "matri_total_horas")
	private int totalHoras;
	

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "est_id")
	private Estudiante estudiante;
	
	
	
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public double getCostoMatricula() {
		return costoMatricula;
	}
	public void setCostoMatricula(double costoMatricula) {
		this.costoMatricula = costoMatricula;
	}
	public double getCostoHora() {
		return costoHora;
	}
	public void setCostoHora(double costoHora) {
		this.costoHora = costoHora;
	}
	public int getTotalHoras() {
		return totalHoras;
	}
	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}

	
	
}
