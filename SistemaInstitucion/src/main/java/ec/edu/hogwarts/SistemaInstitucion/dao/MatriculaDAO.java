package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Stateless
public class MatriculaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Matricula op) {
		
		em.persist(op);
	}
	
	public void update(Matricula op) {
		
		em.merge(op);
	}
	
	public Matricula read(int id) {
		
		Matricula op = em.find(Matricula.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Matricula op = em.find(Matricula.class, id);
		em.remove(op);
	}
	
	public List<Matricula> getList(){
		 
		List<Matricula> listado = new ArrayList<Matricula>();
		
		String jpql = "SELECT op FROM Matricula op";
				
		
		Query query = em.createQuery(jpql,Matricula.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Matricula buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Matricula pro = new Matricula();
		List<Matricula> listado = new ArrayList<Matricula>();
		
		String jpql = "SELECT op FROM Matricula op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Matricula.class);
		query.setParameter(1, id);
		try {
			pro= (Matricula) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
