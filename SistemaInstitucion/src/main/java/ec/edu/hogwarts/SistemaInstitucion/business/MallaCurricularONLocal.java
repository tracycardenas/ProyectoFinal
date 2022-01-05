package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;


import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Local
public interface MallaCurricularONLocal {

	public void insert ( MallaCurricular op) ;
	
	public void update (MallaCurricular op) ;
	
	public void delete (int id) ;
	
	public List<MallaCurricular>getMallaCurricular();
	
}
