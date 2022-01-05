package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.Usuario;

@Local
public interface UsuarioONLocal {

	public void insert ( Usuario op) ;
	
	public void update (Usuario op) ;
	
	public void delete (int id) ;
	
	public List<Usuario>getUsuario();
	
}
