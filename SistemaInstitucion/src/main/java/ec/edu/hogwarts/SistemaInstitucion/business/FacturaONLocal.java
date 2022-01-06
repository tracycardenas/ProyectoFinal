package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Local
public interface FacturaONLocal {

	public void insert ( Factura op) throws Exception;
	
	public void update (Factura op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Factura>getFactura();
	
}
