package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.ConfiguracionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.InscripcionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.LibroDiarioON;
import ec.edu.hogwarts.SistemaInstitucion.business.LibroDiarioONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MovimientoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Named
@ViewScoped
public class ArqueoCajaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Inject
	private LibroDiarioONLocal librodiarioON;
	
	
	private LibroDiario libroDiarioAnterior;
	
	@Inject
	private MovimientoONLocal movimientoON;
	
	private List<LibroDiario> librosDiarios;

	private LibroDiario librodiario = null;
	
	private double ingresos=0;
	
	private double egresos=0;
	
	private Date fecha;
	
	private double iniciodeldia=0;
	
	private String observacion;
	
	private double saldo=0;
	
	private boolean estado;
	
	
	public List<LibroDiario> getLibrosDiarios() {
		return librosDiarios;
	}

	public void setLibrosDiarios(List<LibroDiario> librosDiarios) {
		this.librosDiarios = librosDiarios;
	}

	public LibroDiario getLibrodiario() {
		return librodiario;
	}

	public void setLibrodiario(LibroDiario librodiario) {
		this.librodiario = librodiario;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getIniciodeldia() {
		return iniciodeldia;
	}

	public void setIniciodeldia(double iniciodeldia) {
		this.iniciodeldia = iniciodeldia;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void init() {
		this.cargarDatos();
		this.cargarlibrosDiarios();
	}


	// METODOS
	public void cargarlibrosDiarios() {
		librosDiarios = librodiarioON.getLibroDiario();
	}
	

	public void cargarDatos() {
		double contadorIngresos= 0;
		double contadorEgresos =0;
		for (int i = 0; i < librodiarioON.getLibroDiario().size(); i++) {
			if (librodiarioON.getLibroDiario().get(i).isEstado()==true) {

				ingresos = librodiarioON.getLibroDiario().get(i).getIngresos()+contadorIngresos;
				contadorIngresos= librodiarioON.getLibroDiario().get(i).getIngresos();
			
			
				egresos=librodiarioON.getLibroDiario().get(i).getEgresos()+contadorEgresos;
				contadorEgresos = librodiarioON.getLibroDiario().get(i).getEgresos();
				
			
			}
		}
		
		for (int i = 0; i < librodiarioON.getLibroDiario().size(); i++) {
			if (librodiarioON.getLibroDiario().get(i).isEstado()==true) {
				libroDiarioAnterior= librodiarioON.getLibroDiario().get(i);
				iniciodeldia = libroDiarioAnterior.getInicioDia();
				
				
			}
		}
		
		
		
		saldo = ingresos-egresos;
		fecha = new Date();
		
		
		
	}
	
	public void generarLibroDiario() {
		
				libroDiarioAnterior.setEstado(false);
				try {
					librodiarioON.update(libroDiarioAnterior);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
		LibroDiario libroDiarioNuevo = new LibroDiario();
		libroDiarioNuevo.setFecha(fecha);
		libroDiarioNuevo.setIngresos(ingresos);
		libroDiarioNuevo.setEgresos(egresos);
		libroDiarioNuevo.setInicioDia(iniciodeldia);
		libroDiarioNuevo.setObservacion(observacion);
		libroDiarioNuevo.setSaldo(saldo);
		libroDiarioNuevo.setEstado(true);
		
		
		try {
			librodiarioON.insert(libroDiarioNuevo);
			addMessage(FacesMessage.SEVERITY_INFO, "GENERACION DE LIBRO DIARIO REALIZADA CON EXITO", "Inicio");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	

		

	

}
