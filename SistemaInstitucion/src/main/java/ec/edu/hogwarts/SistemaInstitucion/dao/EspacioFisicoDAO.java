package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;

@Stateless
public class EspacioFisicoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(EspacioFisico op) {
		
		em.persist(op);
	}
	
	public void update(EspacioFisico op) {
		
		em.merge(op);
	}
	
	public EspacioFisico read(int id) {
		
		EspacioFisico op = em.find(EspacioFisico.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		EspacioFisico op = em.find(EspacioFisico.class, id);
		em.remove(op);
	}
	
	public List<EspacioFisico> getList(){
		 
		List<EspacioFisico> listado = new ArrayList<EspacioFisico>();
		
		String jpql = "SELECT op FROM EspacioFisico op";
				
		
		Query query = em.createQuery(jpql,EspacioFisico.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public EspacioFisico buscar(String id) {
		
		System.out.println("Llegaste "+id);
		EspacioFisico pro = new EspacioFisico();
		List<EspacioFisico> listado = new ArrayList<EspacioFisico>();
		
		String jpql = "SELECT op FROM EspacioFisico op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,EspacioFisico.class);
		query.setParameter(1, id);
		try {
			pro= (EspacioFisico) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
