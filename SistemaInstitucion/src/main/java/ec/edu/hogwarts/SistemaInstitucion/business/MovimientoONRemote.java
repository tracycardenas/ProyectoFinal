package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Remote
public interface MovimientoONRemote {

	public void insert ( Movimiento op) ;
	
	public void update (Movimiento op) ;
	
	public void delete (int id) ;
	
	public List<Movimiento>getMovimiento();
	
}
