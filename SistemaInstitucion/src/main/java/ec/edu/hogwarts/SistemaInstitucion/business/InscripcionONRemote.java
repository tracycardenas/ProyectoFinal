package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Remote
public interface InscripcionONRemote {

	public void insert ( Inscripcion op) ;
	
	public void update (Inscripcion op) ;
	
	public void delete (int id) ;
	
	public List<Inscripcion>getInscripcion();
	
}
