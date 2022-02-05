package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Stateless
public class GrupoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Grupo op) {
		
		em.persist(op);
	}
	
	public void update(Grupo op) {
		
		em.merge(op);
	}
	
	public Grupo read(int id) {
		
		Grupo op = em.find(Grupo.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Grupo op = em.find(Grupo.class, id);
		em.remove(op);
	}
	
	public List<Grupo> getList(){
		 
		List<Grupo> listado = new ArrayList<Grupo>();
		
		String jpql = "SELECT op FROM Grupo op";
				
		
		Query query = em.createQuery(jpql,Grupo.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Grupo buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Grupo pro = new Grupo();
		List<Grupo> listado = new ArrayList<Grupo>();
		
		String jpql = "SELECT op FROM Grupo op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Grupo.class);
		query.setParameter(1, id);
		try {
			pro= (Grupo) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
	public List<Materia> obtenerMaterias(String cedula) {
		List<Materia> materias = null;
		String consulta;
		try {
			consulta = "SELECT u FROM Grupo u WHERE u.per_id=?1 ";
			Query query = em.createQuery(consulta);
			query.setParameter(1,cedula);
			
			List<Materia> listaMaterias = query.getResultList();
			if(!listaMaterias.isEmpty()) {
				for (Materia materia : listaMaterias) {
					materias.add(materia);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		return materias;
	}
}
