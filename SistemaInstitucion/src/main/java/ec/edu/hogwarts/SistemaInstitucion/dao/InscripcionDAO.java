package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;

@Stateless
public class InscripcionDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Inscripcion op) {
		
		em.persist(op);
	}
	
	public void update(Inscripcion op) {
		
		em.merge(op);
	}
	
	public Inscripcion read(int id) {
		
		Inscripcion op = em.find(Inscripcion.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Inscripcion op = em.find(Inscripcion.class, id);
		em.remove(op);
	}
	
	public List<Inscripcion> getList(){
		 
		List<Inscripcion> listado = new ArrayList<Inscripcion>();
		
		String jpql = "SELECT op FROM Inscripcion op";
				
		
		Query query = em.createQuery(jpql,Inscripcion.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Inscripcion buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Inscripcion pro = new Inscripcion();
		List<Inscripcion> listado = new ArrayList<Inscripcion>();
		
		String jpql = "SELECT op FROM Inscripcion op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Inscripcion.class);
		query.setParameter(1, id);
		try {
			pro= (Inscripcion) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
