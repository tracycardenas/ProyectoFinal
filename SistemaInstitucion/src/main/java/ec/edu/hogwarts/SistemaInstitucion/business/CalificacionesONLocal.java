package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Local
public interface CalificacionesONLocal {

	public void insert ( Calificacion op) ;
	
	public void update (Calificacion op) ;
	
	public void delete (int id) ;
	
	public List<Calificacion>getCalificacion();
	
}
