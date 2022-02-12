package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.ConfiguracionONLocal;

import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;


@Named
@ViewScoped
public class ConfiguracionesGeneralesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Inject
	private ConfiguracionONLocal configuracionesON;
	
	
	
	private Configuracion configuracion = new Configuracion();
	
	private List<Configuracion> configuraciones;
	

	public Configuracion getConfiguracion() {
		return configuracion;
	}


	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}


	@PostConstruct
	public void init() {
	
			this.cargarConfiguracion();
		
	}
	
	public void cargarConfiguracion() {
		configuraciones = configuracionesON.getConfiguracion();
		if (configuraciones.size()>0) {
			configuracion = configuraciones.get(0);
		} else {
			configuracion = new Configuracion();
		}
		
		
	}


	
	public void guardarConfiguraciones() {
		
		configuracion.setId(1);
		
		try {
			configuracionesON.guardar(configuracion);
			addMessage(FacesMessage.SEVERITY_INFO, "CONFIGURACION GENERAL REALIZADA CON EXITO", "Inicio");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public void actualizarConfiguraciones() {
		try {
			configuracionesON.update(configuracion);
			addMessage(FacesMessage.SEVERITY_INFO, "CONFIGURACION GENERAL ACTUALIZADA CON EXITO", "Inicio");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	

		

	

}
