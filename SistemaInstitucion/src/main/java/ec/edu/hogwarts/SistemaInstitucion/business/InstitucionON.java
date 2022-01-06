package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.InstitucionDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Stateless
public class InstitucionON implements InstitucionONLocal, InstitucionONRemote {

	@Inject
	private InstitucionDAO daoInstitucion;
	
	public void insert ( Institucion op) throws Exception{
		
		daoInstitucion.insert(op);
		
	}
	
	public void update (Institucion op) {
		
		daoInstitucion.update(op);
	}
	
	public void delete (int id) {
		
		daoInstitucion.Delete(id);
	}
	
	public List<Institucion>getInstitucion(){
		
		return daoInstitucion.getList();
	}
}
