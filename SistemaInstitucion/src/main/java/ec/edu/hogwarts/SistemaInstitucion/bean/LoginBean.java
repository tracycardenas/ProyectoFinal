package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	
	private Persona persona;
	private Estudiante estudiante;
	private Docente docente;
	private String cedula;
	
	@Inject
	private PersonaONLocal personasON;
	
	@PostConstruct
	public void init() {
		persona = new Persona();
		estudiante = new Estudiante();
		docente = new Docente();
	}
	

	
	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public Docente getDocente() {
		return docente;
	}


	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String iniciarSesion() {
		Persona per;
		String redireccion=null;
		try {
			per = personasON.acceder(persona);
			if (per==null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales Incorrectas"));
			} else if (per.getRol().equals("Estudiante")) {
				redireccion="Estudiante_Perfil?faces-redirect=true&id="+per.getCedula();
			} else if (per.getRol().equals("Docente")) {
				redireccion="Docente_Perfil?faces-redirect=true&id="+per.getCedula();
			}else if (per.getRol().equals("Administrador")) {
				redireccion = "ConfiguracionesGenerales?faces-redirect=true";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","ERROR"));

		}
		return redireccion;
	}
	
	
	
	public void loadData() {
		if (cedula==null) 
			return;
			
		Persona p = personasON.getPersona(cedula);
		persona =p;
		Estudiante est = personasON.getEstudiante(cedula);
		estudiante = est;
		Docente doc = personasON.getDocente(cedula);
		docente = doc;
		
	}
	
	public String Calificaciones(String cedula) {
		String redireccion ="GestionAcademicaDocente?faces-redirect=true&id="+cedula;
		return redireccion;

	}
	
	public String calificacionesEstudiante(String cedula) {
		return "GestionAcademicaEstudiante?faces-redirect=true&id="+cedula;
	}
	
	public void update() {
		try {
			
			this.personasON.update(this.estudiante);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateDocente() {
		try {
			
			this.personasON.update(this.docente);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
