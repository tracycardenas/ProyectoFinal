package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Remote
public interface MovimientoONRemote {

	public void insert ( Movimiento op) throws Exception;
	
	public void update (Movimiento op) throws Exception;
	
	public void delete (int id) throws Exception ;
	
	public List<Movimiento>getMovimiento();
	
}
