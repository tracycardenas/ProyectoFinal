package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.CarreraDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Stateless
public class CarreraON implements CarreraONLocal{

	@Inject
	private CarreraDAO daoCarrera;
	
	public void insert ( Carrera op) throws Exception{
		
		daoCarrera.insert(op);
		
	}
	
	public void update (Carrera op) throws Exception {
		
		daoCarrera.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoCarrera.Delete(id);
	}
	
	public List<Carrera>getCarrera(){
		
		return daoCarrera.getList();
	}
	
	public Carrera getCarrera(int id) {
		
		return daoCarrera.read(id);
	}
}
