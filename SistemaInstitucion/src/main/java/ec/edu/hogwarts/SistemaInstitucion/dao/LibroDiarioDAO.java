package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;

@Stateless
public class LibroDiarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(LibroDiario op) {
		
		em.persist(op);
	}
	
	public void update(LibroDiario op) {
		
		em.merge(op);
	}
	
	public LibroDiario read(int id) {
		
		LibroDiario op = em.find(LibroDiario.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		LibroDiario op = em.find(LibroDiario.class, id);
		em.remove(op);
	}
	
	public List<LibroDiario> getList(){
		 
		List<LibroDiario> listado = new ArrayList<LibroDiario>();
		
		String jpql = "SELECT op FROM LibroDiario op";
				
		
		Query query = em.createQuery(jpql,LibroDiario.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public LibroDiario buscar(String id) {
		
		System.out.println("Llegaste "+id);
		LibroDiario pro = new LibroDiario();
		List<LibroDiario> listado = new ArrayList<LibroDiario>();
		
		String jpql = "SELECT op FROM LibroDiario op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,LibroDiario.class);
		query.setParameter(1, id);
		try {
			pro= (LibroDiario) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
