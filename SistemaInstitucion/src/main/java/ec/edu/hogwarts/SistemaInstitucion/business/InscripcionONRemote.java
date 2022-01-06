package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Remote
public interface InscripcionONRemote {

	public void insert ( Inscripcion op) throws Exception;
	
	public void update (Inscripcion op) throws Exception;
	
	public void delete (int id) throws Exception ;
	
	public List<Inscripcion>getInscripcion();
	
}
