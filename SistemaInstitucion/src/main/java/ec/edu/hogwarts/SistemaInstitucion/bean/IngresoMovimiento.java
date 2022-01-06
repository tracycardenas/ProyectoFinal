package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MovimientoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Named
@RequestScoped
public class IngresoMovimiento {
	
	@Inject
	private MovimientoONLocal movimientoON;
	
	private Movimiento movimiento = new Movimiento();

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	public String guardarMovimiento() {
		try {
			movimientoON.insert(this.movimiento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
