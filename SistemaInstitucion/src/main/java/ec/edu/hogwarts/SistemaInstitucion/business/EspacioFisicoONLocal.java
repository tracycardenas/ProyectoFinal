package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;

@Local
public interface EspacioFisicoONLocal {

	public void insert ( EspacioFisico op) ;
	
	public void update (EspacioFisico op) ;
	
	public void delete (int id) ;
	
	public List<EspacioFisico>getEspacioFisico();
	
}
