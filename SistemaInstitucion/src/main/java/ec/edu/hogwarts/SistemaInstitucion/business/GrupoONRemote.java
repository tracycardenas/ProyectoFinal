package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;

@Remote
public interface GrupoONRemote {

	public void insert ( Grupo op) ;
	
	public void update (Grupo op) ;
	
	public void delete (int id) ;
	
	public List<Grupo>getGrupo();
	
}
