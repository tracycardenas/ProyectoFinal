package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	
	@Column(name = "mat_codigo")
	private String codigo;
	
	@Column(name = "mat_nombre")
	private String nombre;
	
	@Column(name = "mat_descripcion")
	private String descripcion;
	
	@Column(name = "mat_horas_clase")
	private int horasClase;
	
	@Column(name = "mat_horas_practicas")
	private int horasPracticas;
	
	@Column(name = "mat_horas_unidad")
	private int horasUnidad;
	
	@OneToMany
	@JoinColumn(name = "mat_requisito")
	private List<Materia> prerequisitos;
	
	@OneToOne
	@JoinColumn(name = "mat_equivalencia")
	private Materia equivalencia;
	
	@OneToOne
	@JoinColumn(name = "mat_plan_analitico")
	private PlanAnalitico planAnalitico;
	
	@OneToMany(mappedBy = "materia")
	private List<Grupo> grupos;
	
	
	@ManyToOne
	@JoinColumn(name = "niv_id")
	private Nivel nivel;

	


	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public int getHorasClase() {
		return horasClase;
	}

	public void setHorasClase(int horasClase) {
		this.horasClase = horasClase;
	}

	public int getHorasPracticas() {
		return horasPracticas;
	}

	public void setHorasPracticas(int horasPracticas) {
		this.horasPracticas = horasPracticas;
	}

	public int getHorasUnidad() {
		return horasUnidad;
	}

	public void setHorasUnidad(int horasUnidad) {
		this.horasUnidad = horasUnidad;
	}

	public List<Materia> getPrerequisitos() {
		return prerequisitos;
	}

	public void setPrerequisitos(List<Materia> prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	public Materia getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(Materia equivalencia) {
		this.equivalencia = equivalencia;
	}

	public PlanAnalitico getPlanAnalitico() {
		return planAnalitico;
	}

	public void setPlanAnalitico(PlanAnalitico planAnalitico) {
		this.planAnalitico = planAnalitico;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}
