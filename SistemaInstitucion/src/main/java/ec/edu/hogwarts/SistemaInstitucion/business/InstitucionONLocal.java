package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Local
public interface InstitucionONLocal {

	public void insert ( Institucion op) ;
	
	public void update (Institucion op) ;
	
	public void delete (int id) ;
	
	public List<Institucion>getInstitucion();
	
}
