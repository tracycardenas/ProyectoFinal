package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.PersonaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Stateless
public class PersonaON implements PersonaONLocal, PersonaONRemote {

	@Inject
	private PersonaDAO daoPersona;
	
	public void insert( Persona op) throws Exception{
		
		daoPersona.insert(op);
		
	}
	
	public void update (Persona op) throws Exception {
		
		daoPersona.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoPersona.Delete(id);
	}
	
	public List<Persona>getPersona(){
		
		return daoPersona.getList();
	}
}
