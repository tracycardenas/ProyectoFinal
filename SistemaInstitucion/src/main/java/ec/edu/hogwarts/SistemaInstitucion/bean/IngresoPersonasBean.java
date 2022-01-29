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
	private List<Persona> estudiantes;
	private List<Persona> docentes;
	

	
	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@PostConstruct
	public void init() {
		this.loadPersonas();
	}
	

	

	
	public List<Persona> getEstudiantes() {
		return estudiantes;
	}


	public void setEstudiantes(List<Persona> estudiantes) {
		this.estudiantes = estudiantes;
	}


	public List<Persona> getDocentes() {
		return docentes;
	}


	public void setDocentes(List<Persona> docentes) {
		this.docentes = docentes;
	}


	public Docente getDocente() {
		return docente;
	}


	public void setDocente(Docente docente) {
		this.docente = docente;
	}


	


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public String guardarEstudiante() {
		
		String r="";
		try {
			estudiante.setRol("Estudiante");
			personasON.insert(this.estudiante);
			r= "Listar_Estudiantes?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r= "InsertarEstudiantes?faces-redirect=true";
		}
		return r;
	}
	
	public String guardarDocente() {
		
		try {
			docente.setRol("Docente");
			personasON.insert(this.docente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Listado_Docentes?faces-redirect=true";
	}
	
	public void loadPersonas() {
		this.estudiantes= personasON.getEstudiantes();

			for (int i = 0; i < estudiantes.size(); i++) {
				Persona estudiante = estudiantes.get(i);
			}
			
			this.docentes= personasON.getDocentes();

			for (int i = 0; i < docentes.size(); i++) {
				Persona docente = docentes.get(i);
			}
		
			
	}
			

	
	
	


}
