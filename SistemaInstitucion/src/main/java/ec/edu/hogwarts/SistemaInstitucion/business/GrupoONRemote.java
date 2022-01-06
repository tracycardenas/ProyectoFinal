package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;

@Remote
public interface GrupoONRemote {

	public void insert ( Grupo op) throws Exception;
	
	public void update (Grupo op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<Grupo>getGrupo();
	
}
