package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Factura;

@Stateless
public class FacturaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura op) {
		
		em.persist(op);
	}
	
	public void update(Factura op) {
		
		em.merge(op);
	}
	
	public Factura read(int id) {
		
		Factura op = em.find(Factura.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Factura op = em.find(Factura.class, id);
		em.remove(op);
	}
	
	public List<Factura> getList(){
		 
		List<Factura> listado = new ArrayList<Factura>();
		
		String jpql = "SELECT op FROM Factura op";
				
		
		Query query = em.createQuery(jpql,Factura.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Factura buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Factura pro = new Factura();
		List<Factura> listado = new ArrayList<Factura>();
		
		String jpql = "SELECT op FROM Factura op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Factura.class);
		query.setParameter(1, id);
		try {
			pro= (Factura) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
