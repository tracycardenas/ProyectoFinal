package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;

@Stateless
public class MallaCurricularDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(MallaCurricular op) {
		
		em.persist(op);
	}
	
	public void update(MallaCurricular op) {
		
		em.merge(op);
	}
	
	public MallaCurricular read(int id) {
		
		MallaCurricular op = em.find(MallaCurricular.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		MallaCurricular op = em.find(MallaCurricular.class, id);
		em.remove(op);
	}
	
	public List<MallaCurricular> getList(){
		 
		List<MallaCurricular> listado = new ArrayList<MallaCurricular>();
		
		String jpql = "SELECT op FROM MallaCurricular op";
				
		
		Query query = em.createQuery(jpql,MallaCurricular.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public MallaCurricular buscar(String id) {
		
		System.out.println("Llegaste "+id);
		MallaCurricular pro = new MallaCurricular();
		List<MallaCurricular> listado = new ArrayList<MallaCurricular>();
		
		String jpql = "SELECT op FROM MallaCurricular op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,MallaCurricular.class);
		query.setParameter(1, id);
		try {
			pro= (MallaCurricular) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
