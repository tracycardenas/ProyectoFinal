package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Local
public interface LibroDiarioONLocal {

	public void insert ( LibroDiario op) throws Exception;
	
	public void update (LibroDiario op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<LibroDiario>getLibroDiario();
	
}
