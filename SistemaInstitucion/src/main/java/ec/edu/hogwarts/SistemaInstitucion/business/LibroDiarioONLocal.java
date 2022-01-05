package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Local
public interface LibroDiarioONLocal {

	public void insert ( LibroDiario op) ;
	
	public void update (LibroDiario op) ;
	
	public void delete (int id) ;
	
	public List<LibroDiario>getLibroDiario();
	
}
