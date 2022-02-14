package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_libroDiario")
public class LibroDiario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lib_id")
	private int id;
	
	@Column(name = "lib_fecha")
	private Date fecha;
	
	@Column(name = "lib_inicioDia")
	private double inicioDia;
	
	@Column(name = "lib_ingresos")
	private double ingresos;
	
	@Column(name = "lib_egresos")
	private double egresos;
	
	@Column(name = "lib_saldo")
	private double saldo;
	
	@Column(name = "lib_observacion")
	private String observacion;
	
	@Column(name = "lib_estado")
	private boolean estado;
	
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "libroDiario")
	@JsonbTransient
	private List<Movimiento> movimientos;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getInicioDia() {
		return inicioDia;
	}

	public void setInicioDia(double inicioDia) {
		this.inicioDia = inicioDia;
	}

	public double getIngresos() {
		return ingresos;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public double getEgresos() {
		return egresos;
	}

	public void setEgresos(double egresos) {
		this.egresos = egresos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return fecha.getDate()+"/"+fecha.getMonth()+"/"+fecha.getYear();
	}

}
