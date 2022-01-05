package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;

@Stateless
public class InstitucionDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Institucion op) {
		
		em.persist(op);
	}
	
	public void update(Institucion op) {
		
		em.merge(op);
	}
	
	public Institucion read(int id) {
		
		Institucion op = em.find(Institucion.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Institucion op = em.find(Institucion.class, id);
		em.remove(op);
	}
	
	public List<Institucion> getList(){
		 
		List<Institucion> listado = new ArrayList<Institucion>();
		
		String jpql = "SELECT op FROM Institucion op";
				
		
		Query query = em.createQuery(jpql,Institucion.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Institucion buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Institucion pro = new Institucion();
		List<Institucion> listado = new ArrayList<Institucion>();
		
		String jpql = "SELECT op FROM Institucion op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Institucion.class);
		query.setParameter(1, id);
		try {
			pro= (Institucion) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
