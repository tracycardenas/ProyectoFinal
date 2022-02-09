package ec.edu.hogwarts.SistemaInstitucion.bean;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@ViewScoped
public class IngresoPersonasBean implements Serializable{
	
	@Inject
	private PersonaONLocal personasON;
	
	private Persona persona = new Persona();
	private Estudiante estudiante = new Estudiante();
	private Docente docente = new Docente();
	private List<Estudiante> estudiantes;
	private List<Docente> docentes;
	private String cedula;
	


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
	
	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}


	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}


	public List<Docente> getDocentes() {
		return docentes;
	}


	public void setDocentes(List<Docente> docentes) {
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
		return null;
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


	// David Paguay
	
	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	public String editar(String cedula){

		return "ActualizarEstudiantes?faces-redirect=true&id="+cedula;
	}
	
	public String matricular(String cedula){

		return "InsertarMatricula?faces-redirect=true&id="+cedula;
	}
	
	
	public String perfil(String cedula){
		System.out.println(cedula);
		return "ActualizarEstudiantes?faces-redirect=true&id="+cedula;
	}
	
	public String eliminar(String cedula){
		System.out.println(cedula);
		
		try {
			personasON.delete(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Listar_Estudiantes?faces-redirect=true";
	}
	
	public String update() {
		
		try {
			estudiante.setRol("Estudiante");
			personasON.update(this.estudiante);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Listar_Estudiantes?faces-redirect=true";
	}
	
	public String registrar(String cedula) {
		
		return "InsertarInscripcion?faces-redirect=true&id="+cedula;
	}
	
	// Imagen
	
	
	public void loadDataEst(String cedula) {
		Estudiante p = personasON.getEstudiante(estudiante.getCedula());
		estudiante = p;
	}
	

	
	
	
	
	
			

	
	
	


}
