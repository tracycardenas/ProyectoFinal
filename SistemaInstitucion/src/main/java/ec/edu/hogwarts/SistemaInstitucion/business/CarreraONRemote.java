package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Remote
public interface CarreraONRemote {

	public void insert ( Carrera op) ;
	
	public void update (Carrera op) ;
	
	public void delete (int id) ;
	
	public List<Carrera>getCarrera();
	
}
