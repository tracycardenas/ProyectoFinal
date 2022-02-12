package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.ConfiguracionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;

@Stateless
public class ConfiguracionON implements ConfiguracionONLocal{

	@Inject
	private ConfiguracionDAO daoConfiguracion;
	
	public void insert ( Configuracion op) throws Exception{
		
		daoConfiguracion.insert(op);
		
	}
	
	public void update (Configuracion op) throws Exception {
		
		daoConfiguracion.update(op);
	}
	
	public Configuracion getConfiguracion(int id) {
		
		return daoConfiguracion.read(id);
	}
	
	public void delete (int id) throws Exception{
		
		daoConfiguracion.Delete(id);
	}
	
	public List<Configuracion>getConfiguracion(){
		
		return daoConfiguracion.getList();
	}
	
	public void guardar(Configuracion p) throws Exception {
		if(daoConfiguracion.read(p.getId())==null)
			daoConfiguracion.insert(p);
		else
			daoConfiguracion.update(p);
	}
}
