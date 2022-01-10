package ec.edu.hogwarts.SistemaInstitucion.bean;


import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@RequestScoped
public class IngresoPersonasBean {
	
	@Inject
	private PersonaONLocal personasON;
	
	private Persona persona = new Persona();
	private Estudiante estudiante = new Estudiante();
	private Docente docente = new Docente();
	private List<Persona> personas;
	private List<Estudiante> estudiantes;
	private List<Docente> docentes;
	

	
	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@PostConstruct
	public void init () {
		this.loadPersonas();
	}


	public List<Persona> getPersonas() {
		return personas;
	}


	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	
	public Docente getDocente() {
		return docente;
	}


	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}


	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public String guardarEstudiante() {
		
		try {
			
			personasON.insert(this.estudiante);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Listar_Estudiantes?faces-redirect=true";
	}
	
	public String guardarDocente() {
		
		try {
			personasON.insert(this.docente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Listar_Estudiantes?faces-redirect=true";
	}
	
	public void loadPersonas() {
		
		this.personas= personasON.getPersona();
		
	    for (int i = 0; i < personas.size(); i++) {
			
	    	Persona p = personas.get(i);
	    	
			
		}
	}
	
	


}
