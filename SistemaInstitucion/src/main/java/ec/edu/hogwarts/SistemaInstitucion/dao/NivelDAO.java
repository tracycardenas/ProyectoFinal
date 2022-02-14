package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;


@Stateless
public class NivelDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Nivel op) {
		
		em.persist(op);
	}
	
	public void update(Nivel op) {
		
		em.merge(op);
	}
	
	public Nivel read(int id) {
		Nivel op = em.find(Nivel.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Nivel op = em.find(Nivel.class, id);
		em.remove(op);
	}
	
	public List<Nivel> getList(){
		List<Nivel> listado = new ArrayList<Nivel>();
		String jpql = "SELECT op FROM Nivel op";
		Query query = em.createQuery(jpql,Nivel.class);
		listado=query.getResultList();
		return listado;
	}
	


}
