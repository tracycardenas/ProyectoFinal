package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Remote
public interface MatriculaONRemote {

	public void insert ( Matricula op) throws Exception;
	
	public void update (Matricula op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Matricula>getMatricula();
	
}