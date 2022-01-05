package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.InscripcionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Stateless
public class InscripcionON implements InscripcionONLocal, InscripcionONRemote {

	@Inject
	private InscripcionDAO daoInscripcion;
	
	public void insert ( Inscripcion op) {
		
		daoInscripcion.insert(op);
		
	}
	
	public void update (Inscripcion op) {
		
		daoInscripcion.update(op);
	}
	
	public void delete (int id) {
		
		daoInscripcion.Delete(id);
	}
	
	public List<Inscripcion>getInscripcion(){
		
		return daoInscripcion.getList();
	}
}
