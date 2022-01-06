package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Remote
public interface MateriaONRemote {

	public void insert ( Materia op) throws Exception;
	
	public void update (Materia op) throws Exception ;
	
	public void delete (int id) throws Exception ;
	
	public List<Materia>getMateria();
	
}
