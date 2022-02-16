package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;

@Stateless
public class MateriaPrerrequisitoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(MateriaPrerrequisito op) {
		
		em.persist(op);
	}
	
	public void update(MateriaPrerrequisito op) {
		
		em.merge(op);
	}
	
	public MateriaPrerrequisito read(int id) {
		
		MateriaPrerrequisito op = em.find(MateriaPrerrequisito.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		MateriaPrerrequisito op = em.find(MateriaPrerrequisito.class, id);
		em.remove(op);
	}
	
	public List<MateriaPrerrequisito> getList(){
		 
		List<MateriaPrerrequisito> listado = new ArrayList<MateriaPrerrequisito>();
		
		String jpql = "SELECT op FROM MateriaPrerrequisito op";
				
		
		Query query = em.createQuery(jpql,MateriaPrerrequisito.class);
		listado=query.getResultList();
		
		return listado;
	}

}
