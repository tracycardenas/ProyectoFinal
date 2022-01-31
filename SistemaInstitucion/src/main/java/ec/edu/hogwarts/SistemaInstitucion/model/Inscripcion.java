package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_inscripciones")
public class Inscripcion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ins_id")
	private int id;
	@Column(name = "ins_fecha")
	private Date fecha;
	@Column(name = "ins_costo_inscripcion")
	private double costoInscripcion;
	@Column(name = "ins_estado_pago")
	private boolean estadoPago;
	@OneToOne
	@JoinColumn(name = "carr_id")
	private Carrera carrera;
	@OneToOne
	@JoinColumn(name = "est_id")   //Apuesta de 20$ - que si va a funcionar
	private Estudiante estudiante;
	@OneToOne
	@JoinColumn(name = "malla_id")
	private MallaCurricular mallaCurricular;
	
	public double getCostoInscripcion() {
		return costoInscripcion;
	}

	public void setCostoInscripcion(double costoInscripcion) {
		this.costoInscripcion = costoInscripcion;
	}

	public boolean isEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public MallaCurricular getMallaCurricular() {
		return mallaCurricular;
	}

	public void setMallaCurricular(MallaCurricular mallaCurricular) {
		this.mallaCurricular = mallaCurricular;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
}
