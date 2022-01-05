package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MovimientoDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Stateless
public class MovimientoON implements MovimientoONLocal, MovimientoONRemote {

	@Inject
	private MovimientoDAO daoMovimiento;
	
	public void insert ( Movimiento op) {
		
		daoMovimiento.insert(op);
		
	}
	
	public void update (Movimiento op) {
		
		daoMovimiento.update(op);
	}
	
	public void delete (int id) {
		
		daoMovimiento.Delete(id);
	}
	
	public List<Movimiento>getMovimiento(){
		
		return daoMovimiento.getList();
	}
}
