package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
public class CobrosInscripcionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private InscripcionONLocal inscripcionON;

	@Inject
	private PersonaONLocal personasON;

	private Inscripcion inscripcion;
	
	
	@Inject
	private MovimientoONLocal movimientoON;
	
	
	private Movimiento movimientoNuevo = new Movimiento();
	
	private List<Materia> materias = new ArrayList<Materia>();

	private Estudiante estudiante = new Estudiante();
	private String cedula;
	
	private double iva;
	
	private double total;
	
	private double ingresosAnteriores;
	
	private double saldoAnterior;
	
	private double iniciodeldiaAnterior;
	
	
	
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

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
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
		estudiante = personasON.getEstudiante(cedula);
		inscripcion = inscripcionON.getInscripcionEstudiante(cedula);
		iva = configuracionON.getConfiguracion().get(0).getConf_iva()*inscripcion.getCostoInscripcion();
		total = iva+inscripcion.getCostoInscripcion();
		this.cedula = cedula;
	}




	// METODOS

	public void cobrarInscripcion() {
		
		inscripcion.setEstadoPago(true);
		
		try {
			inscripcionON.update(inscripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		movimientoNuevo.setDescripcion("Inscripcion de estudiante con Cl: "+estudiante.getCedula()+" en la carrera de "+inscripcion.getCarrera().getNombre());
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
		
		
		inscripcion = new Inscripcion();
		iva = 0;
		total=0;
		estudiante = new Estudiante();
		addMessage(FacesMessage.SEVERITY_INFO, "INSCRIPCION EXITOSA", "Ingreso");
		
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	public void cargarInstitucion() {
		configuracion = configuracionON.getConfiguracion().get(0);
	}

		

	

}
