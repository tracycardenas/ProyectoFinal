package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;

@Stateless
public class CalificacionDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Calificacion op) {
		
		em.persist(op);
	}
	
	public void update(Calificacion op) {
		
		em.merge(op);
	}
	
	public Calificacion read(int id) {
		
		Calificacion op = em.find(Calificacion.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Calificacion op = em.find(Calificacion.class, id);
		em.remove(op);
	}
	
	public List<Calificacion> getList(){
		 
		List<Calificacion> listado = new ArrayList<Calificacion>();
		
		String jpql = "SELECT op FROM Calificacion op";
				
		
		Query query = em.createQuery(jpql,Calificacion.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Calificacion buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Calificacion pro = new Calificacion();
		List<Calificacion> listado = new ArrayList<Calificacion>();
		
		String jpql = "SELECT op FROM Calificacion op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Calificacion.class);
		query.setParameter(1, id);
		try {
			pro= (Calificacion) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}

}
