package ec.edu.hogwarts.SistemaInstitucion.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.LibroDiarioONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Named
@RequestScoped
public class IngresoLibroDiario {
	
	@Inject
	private LibroDiarioONLocal libroDiarioON;
	
	private LibroDiario libroDiario = new LibroDiario();

	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}
	
	public String guardarLibroDiario() {
		try {
			libroDiarioON.insert(this.libroDiario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
