package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CarreraONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;

@Named
@RequestScoped
public class IngresoCarreras {
	
	@Inject
	private CarreraONLocal carreraON;
	
	private Carrera carrera = new Carrera();

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	public String guardarCarreras() {
		
		try {
			carreraON.insert(this.carrera);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
