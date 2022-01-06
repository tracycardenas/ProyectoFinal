package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MallaCurricularON;
import ec.edu.hogwarts.SistemaInstitucion.business.MallaCurricularONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Named
@RequestScoped
public class IngresoMallaCurricular {
	
	@Inject
	private MallaCurricularONLocal mallaCurricularON;
	
	private MallaCurricular mallaCurricular = new MallaCurricular();

	public MallaCurricular getMallaCurricular() {
		return mallaCurricular;
	}

	public void setMallaCurricular(MallaCurricular mallaCurricular) {
		this.mallaCurricular = mallaCurricular;
	}
	
	public String guardarMallaCurricular(){
		
		try {
			mallaCurricularON.insert(this.mallaCurricular);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}
