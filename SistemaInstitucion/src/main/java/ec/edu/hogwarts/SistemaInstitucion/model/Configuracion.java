package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_configuraciones")
public class Configuracion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "conf_id")
	private int id;	
	@Column(name ="conf_nombre_institucion")
	private String nombreInstitucion;	
	@Column(name = "conf_razon_social")
	private String razonSocial;	
	@Column(name = "conf_ruc")
	private String RUC;	
	@Column(name = "conf_direccion")
	private String direccion;	
	@Column(name = "conf_telefono")
	private String telefono;
	@Column(name = "conf_iva")
	private double conf_iva;
	@Column(name = "conf_costo_matricula")
	private double conf_costoMatricula;
	@Column(name = "conf_costo_inscripcion")
	private double conf_costoInscripcion;
	
	
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRUC() {
		return RUC;
	}
	public void setRUC(String rUC) {
		RUC = rUC;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getConf_iva() {
		return conf_iva;
	}
	public void setConf_iva(double conf_iva) {
		this.conf_iva = conf_iva;
	}
	public double getConf_costoMatricula() {
		return conf_costoMatricula;
	}
	public void setConf_costoMatricula(double conf_costoMatricula) {
		this.conf_costoMatricula = conf_costoMatricula;
	}
	public double getConf_costoInscripcion() {
		return conf_costoInscripcion;
	}
	public void setConf_costoInscripcion(double conf_costoInscripcion) {
		this.conf_costoInscripcion = conf_costoInscripcion;
	}

}
