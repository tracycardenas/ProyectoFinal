package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Local
public interface MallaCurricularONLocal {

	public void insert ( MallaCurricular op) throws Exception;
	
	public void update (MallaCurricular op) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public List<MallaCurricular>getMallaCurricular();
	
}
