package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;

@Local
public interface GrupoONLocal {

	public void insert ( Grupo op) throws Exception;
	
	public void update (Grupo op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Grupo>getGrupo();
	
}
