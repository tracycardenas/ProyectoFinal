package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_calificaciones")
public class Calificacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cal_id")
	private int id;
	@Column(name = "cal_aporte1")
	private double aporte1;
	@Column(name = "cal_examen1")
	private double examen1;
	@Column(name = "cal_aporte2")
	private double aporte2;
	@Column(name = "cal_examen2")
	private double examen2;
	@Column(name = "cal_estado")
	private String estado; //aprobado-reprobado-cursando
	
	@Column(name = "cal_total")
	private double total;
	
	
	@ManyToOne
	@JoinColumn(name = "per_id")
	private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name = "grup_id")
	private Grupo grupo;
	
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAporte1() {
		return aporte1;
	}
	public void setAporte1(double aporte1) {
		this.aporte1 = aporte1;
	}
	public double getExamen1() {
		return examen1;
	}
	public void setExamen1(double examen1) {
		this.examen1 = examen1;
	}
	public double getAporte2() {
		return aporte2;
	}
	public void setAporte2(double aporte2) {
		this.aporte2 = aporte2;
	}
	public double getExamen2() {
		return examen2;
	}
	public void setExamen2(double examen2) {
		this.examen2 = examen2;
	}
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	

	
}
