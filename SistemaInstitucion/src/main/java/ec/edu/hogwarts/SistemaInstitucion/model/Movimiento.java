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
@Table(name = "tbl_movimientos")
public class Movimiento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mov_id")
	private int id;
	
	@Column(name = "mov_descripcion")
	private String descripcion;
	
	@Column(name = "mov_valor")
	private Double valor;
	
	@Column(name = "mov_tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "lib_id")
	private LibroDiario libroDiario;
	
	

	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
