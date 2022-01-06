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
	
	public void insert ( Factura op) throws Exception{
		
		daoFactura.insert(op);
		
	}
	
	public void update (Factura op) throws Exception{
		
		daoFactura.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoFactura.Delete(id);
	}
	
	public List<Factura>getFactura(){
		
		return daoFactura.getList();
	}
}
