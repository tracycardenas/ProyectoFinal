package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Local
public interface MatriculaONLocal {

	public void insert ( Matricula op) throws Exception;
	
	public void update (Matricula op) throws Exception ;
	
	public void delete (int id) throws Exception;
	
	public List<Matricula>getMatricula();
	
}
