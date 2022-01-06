package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.InscripcionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Named
@RequestScoped
public class IngresoInscripcion {
	
	@Inject
	private InscripcionONLocal inscripcionON;
	
	private Inscripcion inscripcion = new Inscripcion();

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
	
	public String guardarInscripcion() {
		
		try {
			inscripcionON.insert(this.inscripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
