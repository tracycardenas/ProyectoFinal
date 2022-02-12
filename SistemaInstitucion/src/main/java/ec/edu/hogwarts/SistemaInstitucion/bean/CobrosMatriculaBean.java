package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MovimientoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Named
@ViewScoped
public class CobrosMatriculaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MatriculaONLocal matriculaON;

	@Inject
	private PersonaONLocal personasON;

	private Matricula matricula;
	
	
	@Inject
	private MovimientoONLocal movimientoON;
	
	
	private Movimiento movimientoNuevo = new Movimiento();
	
	private List<Materia> materias = new ArrayList<Materia>();

	private Estudiante estudiante = new Estudiante();
	private String cedula;
	
	private double iva;
	
	private double total;
	private double subtotal;
	
	private double ingresosAnteriores;
	
	private double saldoAnterior;
	
	private double iniciodeldiaAnterior;
	
	
	Calendar calendar = Calendar.getInstance();

	private Date fecha = calendar.getTime();
	
	@Inject
	private ConfiguracionONLocal configuracionON;
	
	private Configuracion configuracion;
	
	@Inject
	private LibroDiarioONLocal librodiarioON;

	private LibroDiario librodiario = null;
	@PostConstruct
	public void init() {
		this.cargarInstitucion();
	}

	// Getters and Setters

	
	
	
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	

	public String getCedula() {
		System.out.println("GET CEDULA " + cedula);
		return cedula;
	}

	public void setCedula(String cedula) {
		System.out.println("SET CEDULA " + cedula);
		
		try {
			estudiante = personasON.getEstudiante(cedula);
			matricula = matriculaON.getMatriculaporCedula(cedula);
			subtotal = (matricula.getCostoHora()*matricula.getTotalHoras())+matricula.getCostoMatricula();
			iva = (configuracionON.getConfiguracion().get(0).getConf_iva()*subtotal)/100;
			total = iva+subtotal;
			
			this.cedula = cedula;
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_WARN, "EL ESTUDIANTE NO ESTA MATRICULADO", "Error de Busqueda");
		}
		
	}




	// METODOS

	public void cobrarMatricula() {
		
		matricula.setEstado(true);
		
		try {
			matriculaON.update(matricula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		movimientoNuevo.setDescripcion("Matricula de estudiante con Cl: "+estudiante.getCedula());
		movimientoNuevo.setTipo("Ingreso");
		movimientoNuevo.setValor(total);
		
		for (int i = 0; i < librodiarioON.getLibroDiario().size(); i++) {
			if (librodiarioON.getLibroDiario().get(i).isEstado()==true) {
				librodiario = librodiarioON.getLibroDiario().get(i);
			}
		}
		
		movimientoNuevo.setLibroDiario(librodiario);
		
		try {
			movimientoON.insert(movimientoNuevo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ingresosAnteriores = librodiario.getIngresos();
		saldoAnterior = librodiario.getSaldo();
		iniciodeldiaAnterior = librodiario.getInicioDia();
		librodiario.setIngresos(total+ingresosAnteriores);
		librodiario.setSaldo(saldoAnterior+total);
		librodiario.setInicioDia(iniciodeldiaAnterior+total);
		
		try {
			librodiarioON.update(librodiario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		matricula = new Matricula();
		iva = 0;
		total=0;
		estudiante = new Estudiante();
		addMessage(FacesMessage.SEVERITY_INFO, "MATRICULA EXITOSA", "Ingreso");
		
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	public void cargarInstitucion() {
		configuracion = configuracionON.getConfiguracion().get(0);
	}

		

	

}
