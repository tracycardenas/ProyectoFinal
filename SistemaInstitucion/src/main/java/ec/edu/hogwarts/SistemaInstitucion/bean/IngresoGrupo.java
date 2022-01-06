package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.GrupoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;

@Named
@RequestScoped
public class IngresoGrupo {
	
	@Inject
	private GrupoONLocal grupoON;
	
	private Grupo grupo = new Grupo();

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public String guardarGrupo() {
		
		try {
			grupoON.insert(this.grupo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
