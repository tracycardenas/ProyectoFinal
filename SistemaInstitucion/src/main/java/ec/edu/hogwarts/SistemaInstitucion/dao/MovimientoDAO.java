package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;

@Stateless
public class MovimientoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Movimiento op) {
		
		em.persist(op);
	}
	
	public void update(Movimiento op) {
		
		em.merge(op);
	}
	
	public Movimiento read(int id) {
		
		Movimiento op = em.find(Movimiento.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Movimiento op = em.find(Movimiento.class, id);
		em.remove(op);
	}
	
	public List<Movimiento> getList(){
		 
		List<Movimiento> listado = new ArrayList<Movimiento>();
		
		String jpql = "SELECT op FROM Movimiento op";
				
		
		Query query = em.createQuery(jpql,Movimiento.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Movimiento buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Movimiento pro = new Movimiento();
		List<Movimiento> listado = new ArrayList<Movimiento>();
		
		String jpql = "SELECT op FROM Movimiento op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Movimiento.class);
		query.setParameter(1, id);
		try {
			pro= (Movimiento) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
