package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;

@Stateless
public class ConfiguracionDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Configuracion op) {
		
		em.persist(op);
	}
	
	public void update(Configuracion op) {
		
		em.merge(op);
	}
	
	public Configuracion read(int id) {
		
		Configuracion op = em.find(Configuracion.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Configuracion op = em.find(Configuracion.class, id);
		em.remove(op);
	}
	
	public List<Configuracion> getList(){
		 
		List<Configuracion> listado = new ArrayList<Configuracion>();
		
		String jpql = "SELECT op FROM Configuracion op";
				
		
		Query query = em.createQuery(jpql,Configuracion.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Configuracion buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Configuracion pro = new Configuracion();
		List<Configuracion> listado = new ArrayList<Configuracion>();
		
		String jpql = "SELECT op FROM Configuracion op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Configuracion.class);
		query.setParameter(1, id);
		try {
			pro= (Configuracion) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
