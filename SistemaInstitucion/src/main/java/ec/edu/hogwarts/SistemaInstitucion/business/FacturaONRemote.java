package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Remote
public interface FacturaONRemote {

	public void insert ( Factura op) throws Exception;
	
	public void update (Factura op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Factura>getFactura();
	
}
