package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Remote
public interface InstitucionONRemote {

	public void insert ( Institucion op) throws Exception;
	
	public void update (Institucion op)throws Exception ;
	
	public void delete (int id) throws Exception;
	
	public List<Institucion>getInstitucion();
	
}
