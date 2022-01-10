package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Persona;


@Stateless
public class PersonaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Persona op) {
		
		em.persist(op);
	}
	
	public void update(Persona op) {
		
		em.merge(op);
	}
	
	public Persona read(int id) {
		
		Persona op = em.find(Persona.class, id);
	
		return op;
	}
	
	public void Delete(int id) {
		
		Persona op = em.find(Persona.class, id);
		em.remove(op);
	}
	
	public List<Persona> getListEstudiantes(){
		 
		List<Persona> listado = new ArrayList<Persona>();
		
		String jpql = "SELECT op FROM Persona op WHERE per_rol='Estudiante'";
				
		
		Query query = em.createQuery(jpql,Persona.class);
		listado=query.getResultList();
		
		return listado;
	}
	public List<Persona> getListDocentes(){
		 
		List<Persona> listado = new ArrayList<Persona>();
		
		String jpql = "SELECT op FROM Persona op WHERE per_rol='Docente'";
				
		
		Query query = em.createQuery(jpql,Persona.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Persona buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Persona pro = new Persona();
		List<Persona> listado = new ArrayList<Persona>();
		
		String jpql = "SELECT op FROM Persona op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Persona.class);
		query.setParameter(1, id);
		try {
			pro= (Persona) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}

}
