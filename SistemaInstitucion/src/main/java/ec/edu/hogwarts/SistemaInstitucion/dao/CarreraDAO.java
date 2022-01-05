package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;



@Stateless
public class CarreraDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carrera op) {
		
		em.persist(op);
	}
	
	public void update(Carrera op) {
		
		em.merge(op);
	}
	
	public Carrera read(int id) {
		
		Carrera op = em.find(Carrera.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Carrera op = em.find(Carrera.class, id);
		em.remove(op);
	}
	
	public List<Carrera> getList(){
		 
		List<Carrera> listado = new ArrayList<Carrera>();
		
		String jpql = "SELECT op FROM Carrera op";
				
		
		Query query = em.createQuery(jpql,Carrera.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Carrera buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Carrera pro = new Carrera();
		List<Carrera> listado = new ArrayList<Carrera>();
		
		String jpql = "SELECT op FROM Carrera op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Carrera.class);
		query.setParameter(1, id);
		try {
			pro= (Carrera) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
	
}
