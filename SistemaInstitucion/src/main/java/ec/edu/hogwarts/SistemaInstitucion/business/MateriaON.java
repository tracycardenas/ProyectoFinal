package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MateriaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Stateless
public class MateriaON implements MateriaONLocal, MateriaONRemote {

	@Inject
	private MateriaDAO daoMateria;
	
	public void insert ( Materia op) {
		
		daoMateria.insert(op);
		
	}
	
	public void update (Materia op) {
		
		daoMateria.update(op);
	}
	
	public void delete (int id) {
		
		daoMateria.Delete(id);
	}
	
	public List<Materia>getMateria(){
		
		return daoMateria.getList();
	}
}
