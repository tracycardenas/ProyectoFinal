package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.CalificacionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Stateless
public class CalificacionesON implements CalificacionesONLocal{

	@Inject
	private CalificacionDAO daoCalificacion;
	
	public void insert ( Calificacion op) throws Exception{
		
		daoCalificacion.insert(op);
		
	}
	
	public void update (Calificacion op) throws Exception{
		
		daoCalificacion.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoCalificacion.Delete(id);
	}
	
	public List<Calificacion>getCalificacion(){
		
		return daoCalificacion.getList();
	}
	
	
}
