package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Remote
public interface CalificacionesONRemote {

	public void insert ( Calificacion op) throws Exception;
	
	public void update (Calificacion op) throws Exception ;
	
	public void delete (int id) throws Exception;
	
	public List<Calificacion>getCalificacion();
	
}
