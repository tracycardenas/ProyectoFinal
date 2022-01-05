package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Local
public interface MovimientoONLocal {

	public void insert ( Movimiento op) ;
	
	public void update (Movimiento op) ;
	
	public void delete (int id) ;
	
	public List<Movimiento>getMovimiento();
	
}
