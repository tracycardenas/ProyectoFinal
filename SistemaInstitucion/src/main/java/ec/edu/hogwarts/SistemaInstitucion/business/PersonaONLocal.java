package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;

import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Local
public interface PersonaONLocal {

	public void insert ( Persona op) throws Exception ;
	
	public void update (Persona op)throws Exception ;
	
	public void delete (int id) throws Exception;
	
	
	public Estudiante getEstudiante(String cedula) ;
	public Docente getDocente(String cedula);
	public Persona acceder(Persona persona) ;
	public Persona getPersona(String cedula);
	
	public List<Estudiante> getEstudiantes();
	public List<Docente> getDocentes();
	
}
