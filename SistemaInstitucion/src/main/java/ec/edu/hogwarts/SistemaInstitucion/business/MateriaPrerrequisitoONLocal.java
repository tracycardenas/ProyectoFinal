package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;

import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;

@Local
public interface MateriaPrerrequisitoONLocal {
	
	public void insert ( MateriaPrerrequisito op) throws Exception;
	public void update (MateriaPrerrequisito op) throws Exception;
	public void delete (int id) throws Exception;
	public List<MateriaPrerrequisito>getMateriaPrerrequisitos();
	public MateriaPrerrequisito getMateriaPrerrequisito(int id);

}
