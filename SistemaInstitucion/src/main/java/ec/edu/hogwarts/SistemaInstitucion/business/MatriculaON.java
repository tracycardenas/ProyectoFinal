package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MatriculaDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Stateless
public class MatriculaON implements MatriculaONLocal {

	@Inject
	private MatriculaDAO daoMatricula;
	
	public void insert ( Matricula op) throws Exception{
		
		daoMatricula.insert(op);
		
	}
	
	public void update (Matricula op) throws Exception{
		
		daoMatricula.update(op);
	}
	
	public void delete (int id) throws Exception {
		
		daoMatricula.Delete(id);
	}
	
	public List<Matricula>getMatricula(){
		
		return daoMatricula.getList();
	}
	
	public Matricula getMatriculaporCedula(String cedula) {
		return daoMatricula.buscarporCedula(cedula);
	}
}
