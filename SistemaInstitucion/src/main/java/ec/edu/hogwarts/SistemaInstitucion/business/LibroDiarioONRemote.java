package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Remote
public interface LibroDiarioONRemote {

	public void insert ( LibroDiario op) ;
	
	public void update (LibroDiario op) ;
	
	public void delete (int id) ;
	
	public List<LibroDiario>getLibroDiario();
	
}
