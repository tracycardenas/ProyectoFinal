package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Local
public interface PersonaONLocal {

	public void insert ( Persona op) throws Exception ;
	
	public void update (Persona op)throws Exception ;
	
	public void delete (int id) throws Exception;
	
	public List<Persona>getPersona();
	
}
