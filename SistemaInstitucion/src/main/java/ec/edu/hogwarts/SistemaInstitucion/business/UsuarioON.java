package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.UsuarioDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Usuario;

@Stateless
public class UsuarioON implements UsuarioONLocal, UsuarioONRemote {

	@Inject
	private UsuarioDAO daoUsuario;
	
	public void insert ( Usuario op) {
		
		daoUsuario.insert(op);
		
	}
	
	public void update (Usuario op) {
		
		daoUsuario.update(op);
	}
	
	public void delete (int id) {
		
		daoUsuario.Delete(id);
	}
	
	public List<Usuario>getUsuario(){
		
		return daoUsuario.getList();
	}
}
