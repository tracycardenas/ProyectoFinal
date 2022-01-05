package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Local
public interface CarreraONLocal {

	public void insert ( Carrera op) ;
	
	public void update (Carrera op) ;
	
	public void delete (int id) ;
	
	public List<Carrera>getCarrera();
	
}
