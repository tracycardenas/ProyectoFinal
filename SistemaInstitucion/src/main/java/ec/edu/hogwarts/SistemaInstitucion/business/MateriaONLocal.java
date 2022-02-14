package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Local
public interface MateriaONLocal {

	public void insert ( Materia op) throws Exception ;
	
	public void update (Materia op) throws Exception;
	
	public void delete (int id) throws Exception ;
	
	public List<Materia>getMaterias();
	
	public Materia getMateriaporCodigo(int id);
	
}
