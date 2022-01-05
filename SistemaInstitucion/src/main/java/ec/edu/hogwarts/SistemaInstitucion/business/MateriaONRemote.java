package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Remote
public interface MateriaONRemote {

	public void insert ( Materia op) ;
	
	public void update (Materia op) ;
	
	public void delete (int id) ;
	
	public List<Materia>getMateria();
	
}
