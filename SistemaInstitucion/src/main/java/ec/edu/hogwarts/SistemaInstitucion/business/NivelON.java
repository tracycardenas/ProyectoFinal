package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.NivelDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;


@Stateless
public class NivelON implements NivelONLocal {
	
	@Inject
	private NivelDAO daoNivel;
	
	public void insert ( Nivel op) throws Exception {
		
		daoNivel.insert(op);
		
	}
	
	public void update (Nivel op) throws Exception {
		
		daoNivel.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoNivel.Delete(id);
	}
	
	public List<Nivel>getNiveles(){
		
		return daoNivel.getList();
	}
	

}
