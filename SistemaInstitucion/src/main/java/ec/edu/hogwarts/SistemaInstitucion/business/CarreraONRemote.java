package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Remote
public interface CarreraONRemote {

	public void insert ( Carrera op) throws Exception ;
	
	public void update (Carrera op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Carrera>getCarrera();
	
}
