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
	
	public void insert ( Persona op) {
		
		daoPersona.insert(op);
		
	}
	
	public void update (Persona op) {
		
		daoPersona.update(op);
	}
	
	public void delete (int id) {
		
		daoPersona.Delete(id);
	}
	
	public List<Persona>getPersona(){
		
		return daoPersona.getList();
	}
}
