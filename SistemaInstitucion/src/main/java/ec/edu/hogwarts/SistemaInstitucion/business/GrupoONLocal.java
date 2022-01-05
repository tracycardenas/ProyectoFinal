package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;

@Local
public interface GrupoONLocal {

	public void insert ( Grupo op) ;
	
	public void update (Grupo op) ;
	
	public void delete (int id) ;
	
	public List<Grupo>getGrupo();
	
}
