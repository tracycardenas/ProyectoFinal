package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Local
public interface CalificacionesONLocal {

	public void insert ( Calificacion op) throws Exception;
	
	public void update (Calificacion op) throws Exception;
	
	public void delete (int id) throws Exception ;
	
	public List<Calificacion>getCalificacion();
	
	
}
