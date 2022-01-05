package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Local
public interface FacturaONLocal {

	public void insert ( Factura op) ;
	
	public void update (Factura op) ;
	
	public void delete (int id) ;
	
	public List<Factura>getFactura();
	
}
