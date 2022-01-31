package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;

@Named
@RequestScoped
public class GestionMateriasBean {
	
	@Inject
	private MateriaONLocal materiaON;
	
	
	
}
