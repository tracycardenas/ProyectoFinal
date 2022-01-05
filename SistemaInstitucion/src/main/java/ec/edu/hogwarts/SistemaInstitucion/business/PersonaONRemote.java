package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Remote
public interface PersonaONRemote {

	public void insert ( Persona op) ;
	
	public void update (Persona op) ;
	
	public void delete (int id) ;
	
	public List<Persona>getPersona();
	
}
