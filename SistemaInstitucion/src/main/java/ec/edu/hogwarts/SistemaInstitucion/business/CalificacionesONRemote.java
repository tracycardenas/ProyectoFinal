package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Remote
public interface CalificacionesONRemote {

	public void insert ( Calificacion op) ;
	
	public void update (Calificacion op) ;
	
	public void delete (int id) ;
	
	public List<Calificacion>getCalificacion();
	
}
