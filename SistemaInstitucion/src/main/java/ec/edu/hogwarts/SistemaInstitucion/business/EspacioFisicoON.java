package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.EspacioFisicoDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;

@Stateless
public class EspacioFisicoON implements EspacioFisicoONLocal, EspacioFisicoONRemote {

	@Inject
	private EspacioFisicoDAO daoEspacioFisico;
	
	public void insert ( EspacioFisico op) throws Exception {
		
		daoEspacioFisico.insert(op);
		
	}
	
	public void update (EspacioFisico op) throws Exception {
		
		daoEspacioFisico.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoEspacioFisico.Delete(id);
	}
	
	public List<EspacioFisico>getEspacioFisico(){
		
		return daoEspacioFisico.getList();
	}
}
