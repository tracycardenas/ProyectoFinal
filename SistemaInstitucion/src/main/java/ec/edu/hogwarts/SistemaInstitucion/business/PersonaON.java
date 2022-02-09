package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.PersonaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Stateless
public class PersonaON implements PersonaONLocal {

	@Inject
	private PersonaDAO daoPersona;
	
	public void insert( Persona op) throws Exception{
		
		daoPersona.insert(op);
		
	}
	
	public void update (Persona op) throws Exception {
		
		daoPersona.update(op);
	}
	
	public void delete (String id) throws Exception{
		
		daoPersona.Delete(id);
	}
	
	public List<Estudiante> getEstudiantes(){
		return daoPersona.getListEstudiantes();
	}
	
	
	public List<Docente> getDocentes(){
		return daoPersona.getListDocentes();
	}
	public Estudiante getEstudiante(String cedula) {
		return daoPersona.readEstudiante(cedula);
	}
	public Docente getDocente(String cedula) {
		return daoPersona.readDocente(cedula);
	}
	public Persona getPersona(String cedula) {
		return daoPersona.read(cedula);
	}
	public Persona acceder(Persona persona) {
		return daoPersona.iniciarSesion(persona);
	}

}
