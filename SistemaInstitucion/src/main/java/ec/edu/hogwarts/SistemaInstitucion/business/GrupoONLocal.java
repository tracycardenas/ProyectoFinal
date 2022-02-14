package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Local
public interface GrupoONLocal {

	public void insert ( Grupo op) throws Exception;
	
	public void update (Grupo op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Grupo>getGrupos();
	
	public List<Materia> getMaterias(String cedula);
	public Grupo getGrupoporCodigo(int id) ;
	
}
