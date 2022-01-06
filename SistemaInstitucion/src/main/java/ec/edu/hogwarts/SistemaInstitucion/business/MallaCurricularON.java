package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.hogwarts.SistemaInstitucion.dao.MallaCurricularDAO;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Stateless
public class MallaCurricularON implements MallaCurricularONLocal, MallaCurricularONRemote {

	@Inject
	private MallaCurricularDAO daoMallaCurricular;
	
	public void insert ( MallaCurricular op) throws Exception{
		
		daoMallaCurricular.insert(op);
		
	}
	
	public void update (MallaCurricular op) throws Exception {
		
		daoMallaCurricular.update(op);
	}
	
	public void delete (int id) throws Exception{
		
		daoMallaCurricular.Delete(id);
	}
	
	public List<MallaCurricular>getMallaCurricular(){
		
		return daoMallaCurricular.getList();
	}
}
