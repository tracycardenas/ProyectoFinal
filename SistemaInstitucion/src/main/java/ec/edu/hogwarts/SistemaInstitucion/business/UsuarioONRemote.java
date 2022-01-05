package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.Usuario;

@Remote
public interface UsuarioONRemote {

	public void insert ( Usuario op) ;
	
	public void update (Usuario op) ;
	
	public void delete (int id) ;
	
	public List<Usuario>getUsuario();
	
}
