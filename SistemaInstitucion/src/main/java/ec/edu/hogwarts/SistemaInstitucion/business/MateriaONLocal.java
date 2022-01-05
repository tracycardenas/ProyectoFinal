package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Local
public interface MateriaONLocal {

	public void insert ( Materia op) ;
	
	public void update (Materia op) ;
	
	public void delete (int id) ;
	
	public List<Materia>getMateria();
	
}
