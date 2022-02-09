package ec.edu.hogwarts.SistemaInstitucion.business;

import java.util.List;

import javax.ejb.Local;

import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;

@Local
public interface NivelONLocal {
	
	public void insert ( Nivel op) throws Exception;
	public void update (Nivel op) throws Exception;
	public void delete (int id) throws Exception;
	public List<Nivel>getNiveles();
	public Nivel getNivel(int id);

}
