package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.CalificacionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Stateless
public class CalificacionesON implements CalificacionesONLocal, CalificacionesONRemote{

	@Inject
	private CalificacionDAO daoCalificacion;
	
	public void insert ( Calificacion op) {
		
		daoCalificacion.insert(op);
		
	}
	
	public void update (Calificacion op) {
		
		daoCalificacion.update(op);
	}
	
	public void delete (int id) {
		
		daoCalificacion.Delete(id);
	}
	
	public List<Calificacion>getCalificacion(){
		
		return daoCalificacion.getList();
	}
}
