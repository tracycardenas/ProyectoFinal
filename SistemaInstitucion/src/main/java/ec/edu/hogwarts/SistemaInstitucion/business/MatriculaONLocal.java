package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Local
public interface MatriculaONLocal {

	public void insert ( Matricula op) ;
	
	public void update (Matricula op) ;
	
	public void delete (int id) ;
	
	public List<Matricula>getMatricula();
	
}
