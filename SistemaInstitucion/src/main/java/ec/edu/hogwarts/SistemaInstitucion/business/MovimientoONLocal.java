package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Local
public interface MovimientoONLocal {

	public void insert ( Movimiento op) throws Exception;
	
	public void update (Movimiento op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Movimiento>getMovimiento();
	
}
