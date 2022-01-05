package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.CarreraDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Stateless
public class CarreraON implements CarreraONLocal, CarreraONRemote {

	@Inject
	private CarreraDAO daoCarrera;
	
	public void insert ( Carrera op) {
		
		daoCarrera.insert(op);
		
	}
	
	public void update (Carrera op) {
		
		daoCarrera.update(op);
	}
	
	public void delete (int id) {
		
		daoCarrera.Delete(id);
	}
	
	public List<Carrera>getCarrera(){
		
		return daoCarrera.getList();
	}
}
