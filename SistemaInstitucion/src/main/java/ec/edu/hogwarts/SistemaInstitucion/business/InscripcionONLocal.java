package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Local
public interface InscripcionONLocal {

	public void insert ( Inscripcion op) ;
	
	public void update (Inscripcion op) ;
	
	public void delete (int id) ;
	
	public List<Inscripcion>getInscripcion();
	
}
