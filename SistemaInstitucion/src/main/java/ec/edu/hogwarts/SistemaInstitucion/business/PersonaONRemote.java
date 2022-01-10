package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Remote
public interface PersonaONRemote {

	public void insert ( Persona op) throws Exception;
	
	public void update (Persona op)throws Exception ;
	
	public void delete (int id)throws Exception ;
	
	public List<Persona>getEstudiantes();
	public List<Persona>getDocentes();
	
}
