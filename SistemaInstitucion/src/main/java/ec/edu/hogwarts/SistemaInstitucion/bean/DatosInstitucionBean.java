package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.InstitucionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Named
@RequestScoped
public class DatosInstitucionBean {
	
	@Inject
	private InstitucionONLocal institucionON;
	
	private Institucion institucion = new Institucion();

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public String guardarDatosInstitucion() {
		try {
			institucionON.insert(this.institucion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return null;
	}
	

}
