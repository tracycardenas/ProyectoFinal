package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.FacturaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Named
@RequestScoped
public class IngresoFactura {
	
	@Inject
	private FacturaONLocal facturaON;
	
	private Factura factura = new Factura();

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public String guardarFactura() {
		try {
			facturaON.insert(this.factura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
