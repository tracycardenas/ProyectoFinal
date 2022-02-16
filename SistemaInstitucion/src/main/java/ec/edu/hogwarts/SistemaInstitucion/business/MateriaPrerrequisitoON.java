package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MateriaPrerrequisitoDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;


@Stateless
public class MateriaPrerrequisitoON implements MateriaPrerrequisitoONLocal {
	
	@Inject
	private MateriaPrerrequisitoDAO daoMateriaPrerrequisito;
	
	public void insert ( MateriaPrerrequisito op) throws Exception {
		
		daoMateriaPrerrequisito.insert(op);
		
	}
	
	public void update (MateriaPrerrequisito op) throws Exception {
		
		daoMateriaPrerrequisito.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoMateriaPrerrequisito.Delete(id);
	}
	
	public List<MateriaPrerrequisito>getMateriaPrerrequisitos(){
		
		return daoMateriaPrerrequisito.getList();
	}
	
	public MateriaPrerrequisito getMateriaPrerrequisito(int id) {
		
		return daoMateriaPrerrequisito.read(id);
	}
	

}
