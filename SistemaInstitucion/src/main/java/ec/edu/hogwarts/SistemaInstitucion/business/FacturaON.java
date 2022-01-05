package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.FacturaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Stateless
public class FacturaON implements FacturaONLocal, FacturaONRemote {

	@Inject
	private FacturaDAO daoFactura;
	
	public void insert ( Factura op) {
		
		daoFactura.insert(op);
		
	}
	
	public void update (Factura op) {
		
		daoFactura.update(op);
	}
	
	public void delete (int id) {
		
		daoFactura.Delete(id);
	}
	
	public List<Factura>getFactura(){
		
		return daoFactura.getList();
	}
}
