package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;

@Remote
public interface EspacioFisicoONRemote {

	public void insert ( EspacioFisico op) throws Exception ;
	
	public void update (EspacioFisico op) throws Exception ;
	
	public void delete (int id) throws Exception;
	
	public List<EspacioFisico>getEspacioFisico();
	
}
