package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Remote
public interface InstitucionONRemote {

	public void insert ( Institucion op) ;
	
	public void update (Institucion op) ;
	
	public void delete (int id) ;
	
	public List<Institucion>getInstitucion();
	
}
