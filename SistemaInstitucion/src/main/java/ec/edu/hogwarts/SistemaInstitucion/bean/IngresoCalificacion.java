package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CalificacionesONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Named
@RequestScoped
public class IngresoCalificacion {
	
	@Inject
	private CalificacionesONLocal calificacionON;
	
	private Calificacion calificacion = new Calificacion();

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}
	
	public String guardarCalificacion() {
		try {
			calificacionON.insert(this.calificacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
