package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;

@Local
public interface ConfiguracionONLocal {

	public void insert ( Configuracion op) throws Exception ;
	
	public void update (Configuracion op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Configuracion>getConfiguracion();
	
	public Configuracion getConfiguracion(int id);

	public void guardar(Configuracion p) throws Exception;
	
}
