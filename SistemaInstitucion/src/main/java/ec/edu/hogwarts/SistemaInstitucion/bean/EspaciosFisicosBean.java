package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.EspacioFisicoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;

@Named
@RequestScoped
public class EspaciosFisicosBean {
	
	@Inject
	private EspacioFisicoONLocal espacioFisicoON;
	
	private EspacioFisico espacioFisico = new EspacioFisico();

	public EspacioFisico getEspacioFisico() {
		return espacioFisico;
	}

	public void setEspacioFisico(EspacioFisico espacioFisico) {
		this.espacioFisico = espacioFisico;
	}
	
	public String guardarEspaciosFisicos() {
		
		try {
			espacioFisicoON.insert(this.espacioFisico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
