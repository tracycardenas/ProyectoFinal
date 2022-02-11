package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.InscripcionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Stateless
public class InscripcionON implements InscripcionONLocal{

	@Inject
	private InscripcionDAO daoInscripcion;
	
	public void insert ( Inscripcion op) throws Exception{
		
		daoInscripcion.insert(op);
		
	}
	
	public void update (Inscripcion op) throws Exception{
		
		daoInscripcion.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoInscripcion.Delete(id);
	}
	
	public List<Inscripcion>getInscripcion(){
		
		return daoInscripcion.getList();
	}
	
	public Inscripcion getInscripcionEstudiante(String cedula) {
		return daoInscripcion.buscarporCedula(cedula);
	}
}
