package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.GrupoDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Stateless
public class GrupoON implements GrupoONLocal{

	@Inject
	private GrupoDAO daoGrupo;
	
	public void insert ( Grupo op) throws Exception{
		
		daoGrupo.insert(op);
		
	}
	
	public void update (Grupo op) throws Exception{
		
		daoGrupo.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoGrupo.Delete(id);
	}
	
	public List<Grupo>getGrupo(){
		
		return daoGrupo.getList();
	}
	
	public List<Materia> getMaterias(String cedula){
		return daoGrupo.obtenerMaterias(cedula);
	}
	
	public Grupo getGrupoporCodigo(int id) {
		return daoGrupo.read(id);
	}
}
