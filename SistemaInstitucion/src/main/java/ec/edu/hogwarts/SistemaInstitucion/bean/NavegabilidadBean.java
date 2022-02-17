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
public class NavegabilidadBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String InscribirEstudiante() {
		return "InsertarEstudiantes?faces-redirect=true";
	}
	
	public String listarEstudiantes() {
		return "Listar_Estudiantes?faces-redirect=true";
	}
	
	public String insertarMatricula() {
		return "InsertarMatricula?faces-redirect=true";
	}
	
	public String listarMatriculas() {
		return "Listar_Matriculas?faces-redirect=true";
	}
	
	public String agregarDocente () {
		return "InsertarDocentes?faces-redirect=true";
	}
	
	public String  listarDocentes() {
		return "Listar_Docentes?faces-redirect=true";
	}
	public String cobroMatricula() {
		return "CostoMatricula?faces-redirect=true";
	}
	public String cobroInscripcion() {
		return "CostoInscripcion?faces-redirect=true";
	}
	public String libroDiario () {
		return "ArqueoCaja?faces-redirect=true";
	}
	public String movimientos() {
		return "GestionLibroDiario?faces-redirect=true";
	}
	public String configuraciones() {
		return "ConfiguracionesGenerales?faces-redirect=true";
	}
	public String agregarAdministradores() {
		return "InsertarAdministradores?faces-redirect=true";
	}
	
	public String iniciarSesion() {
		return "Login?faces-redirect=true";
	}
	
	public String insertarCarreras() {
		return "InsertarCarreras?faces-redirect=true";
	}
	
	public String insertarMaterias() {
		return "Insertar-Materia?faces-redirect=true";
	}
	
	public String insertarMallasCurriculares() {
		return "Insertar-MallaCurricular?faces-redirect=true";
	}
	
	public String generarGrupos() {
		return "Insertar-Grupo?faces-redirect=true";
	}
		

	

}
