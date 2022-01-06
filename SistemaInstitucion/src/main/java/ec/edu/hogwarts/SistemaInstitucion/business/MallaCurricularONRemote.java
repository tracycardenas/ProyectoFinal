package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Remote
public interface MallaCurricularONRemote {

	public void insert ( MallaCurricular op) throws Exception;
	
	public void update (MallaCurricular op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<MallaCurricular>getMallaCurricular();
	
}
