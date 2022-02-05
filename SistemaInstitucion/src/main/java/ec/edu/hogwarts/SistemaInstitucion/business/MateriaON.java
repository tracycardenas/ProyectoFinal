package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MateriaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Stateless
public class MateriaON implements MateriaONLocal{

	@Inject
	private MateriaDAO daoMateria;
	
	public void insert ( Materia op) throws Exception {
		
		daoMateria.insert(op);
		
	}
	
	public void update (Materia op) throws Exception {
		
		daoMateria.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoMateria.Delete(id);
	}
	
	public List<Materia>getMateria(){
		
		return daoMateria.getList();
	}
	
	public Materia getMateriaporCodigo(int id) {
		return daoMateria.read(id);
	}
}
