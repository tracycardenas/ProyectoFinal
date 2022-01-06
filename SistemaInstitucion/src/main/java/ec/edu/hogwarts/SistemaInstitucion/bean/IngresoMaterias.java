package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Named
@RequestScoped
public class IngresoMaterias {
	
	@Inject
	private MateriaONLocal materiasON;
	
	private Materia materia = new Materia();

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public String guardarMateria() {
		
		try {
			materiasON.insert(this.materia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
