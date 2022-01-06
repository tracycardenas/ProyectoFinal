package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Named
@RequestScoped
public class IngresoMatricula {
	
	@Inject
	private MatriculaONLocal matriculaON;
	
	private Matricula matricula = new Matricula();

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public String guardarMatricula() {
		try {
			matriculaON.insert(this.matricula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
