package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Local
public interface InscripcionONLocal {

	public void insert ( Inscripcion op) throws Exception;
	
	public void update (Inscripcion op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Inscripcion>getInscripcion();
	
	public Inscripcion getInscripcionEstudiante(String cedula);
}
