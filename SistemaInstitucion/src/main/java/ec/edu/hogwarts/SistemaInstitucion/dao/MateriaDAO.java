package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;

@Stateless
public class MateriaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Materia op) {
		
		em.persist(op);
	}
	
	public void update(Materia op) {
		
		em.merge(op);
	}
	
	public Materia read(int id) {
		
		Materia op = em.find(Materia.class, id);
		op.getPrerequisitos().size();
		return op;
	}
	
	public void Delete(int id) {
		
		Materia op = em.find(Materia.class, id);
		em.remove(op);
	}
	
	public List<Materia> getList(){
		 
		List<Materia> listado = new ArrayList<Materia>();
		
		String jpql = "SELECT op FROM Materia op";
				
		
		Query query = em.createQuery(jpql,Materia.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Materia buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Materia pro = new Materia();
		List<Materia> listado = new ArrayList<Materia>();
		
		String jpql = "SELECT op FROM Materia op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Materia.class);
		query.setParameter(1, id);
		try {
			pro= (Materia) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
	
	/*public List<MateriaPrerrequisito> getPreRequisitos(){
		 
		List<MateriaPrerrequisito> listado = new ArrayList<MateriaPrerrequisito>();
		
		String jpql = "SELECT op FROM Materia op";
				
		
		Query query = em.createQuery(jpql,MateriaPrerrequisito.class);
		listado=query.getResultList();
		
		return listado;
	}*/
}
