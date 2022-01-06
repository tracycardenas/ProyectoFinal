package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.LibroDiarioDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Stateless
public class LibroDiarioON implements LibroDiarioONLocal, LibroDiarioONRemote {

	@Inject
	private LibroDiarioDAO daoLibroDiario;
	
	public void insert ( LibroDiario op) throws Exception{
		
		daoLibroDiario.insert(op);
		
	}
	
	public void update (LibroDiario op) throws Exception{
		
		daoLibroDiario.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoLibroDiario.Delete(id);
	}
	
	public List<LibroDiario>getLibroDiario(){
		
		return daoLibroDiario.getList();
	}
}
