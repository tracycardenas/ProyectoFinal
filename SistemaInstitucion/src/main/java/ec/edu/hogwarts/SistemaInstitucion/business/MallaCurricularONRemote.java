package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Remote;

import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Remote
public interface MallaCurricularONRemote {

	public void insert ( MallaCurricular op) ;
	
	public void update (MallaCurricular op) ;
	
	public void delete (int id) ;
	
	public List<MallaCurricular>getMallaCurricular();
	
}
