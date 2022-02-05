package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
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
	
	public Persona read(String cedula) {
		
		Persona op = em.find(Persona.class, cedula);
	
		return op;
	}
	
	public void Delete(int id) {
		
		Persona op = em.find(Persona.class, id);
		em.remove(op);
	}
	
	public List<Estudiante> getListEstudiantes(){
		 
		List<Estudiante> listado = new ArrayList<Estudiante>();
		
		String jpql = "SELECT op FROM Persona op WHERE per_rol='Estudiante'";
				
		
		Query query = em.createQuery(jpql,Persona.class);
		listado=query.getResultList();
		
		return listado;
	}
	public List<Docente> getListDocentes(){
		 
		List<Docente> listado = new ArrayList<Docente>();
		
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
	
	public Estudiante readEstudiante(String cedula) {
		Persona op = em.find(Persona.class, cedula);
		Estudiante e = em.find(Estudiante.class, cedula);
		
		return e;
	}
	
	public Docente readDocente(String cedula) {
		Docente d = em.find(Docente.class, cedula);
		return d;
	}
	
	public Persona iniciarSesion(Persona per) {
		Persona persona = null;
		String consulta;
		try {
			consulta = "SELECT u FROM Persona u WHERE u.email=?1 and u.password=?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1,per.getEmail());
			query.setParameter(2, per.getPassword());
			
			List<Persona> lista = query.getResultList();
			if(!lista.isEmpty()) {
				persona = lista.get(0);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return persona;
	}

}
