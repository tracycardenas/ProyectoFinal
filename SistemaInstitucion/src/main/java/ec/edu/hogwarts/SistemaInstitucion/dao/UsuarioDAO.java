package ec.edu.hogwarts.SistemaInstitucion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.hogwarts.SistemaInstitucion.model.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuario op) {
		
		em.persist(op);
	}
	
	public void update(Usuario op) {
		
		em.merge(op);
	}
	
	public Usuario read(int id) {
		
		Usuario op = em.find(Usuario.class, id);
		return op;
	}
	
	public void Delete(int id) {
		
		Usuario op = em.find(Usuario.class, id);
		em.remove(op);
	}
	
	public List<Usuario> getList(){
		 
		List<Usuario> listado = new ArrayList<Usuario>();
		
		String jpql = "SELECT op FROM Usuario op";
				
		
		Query query = em.createQuery(jpql,Usuario.class);
		listado=query.getResultList();
		
		return listado;
	}
	
	public Usuario buscar(String id) {
		
		System.out.println("Llegaste "+id);
		Usuario pro = new Usuario();
		List<Usuario> listado = new ArrayList<Usuario>();
		
		String jpql = "SELECT op FROM Usuario op"
		         + "     WHERE op.cedula = ?1"; 
		
		Query query = em.createQuery(jpql,Usuario.class);
		query.setParameter(1, id);
		try {
			pro= (Usuario) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			pro=null;
		}
		
		
		return pro;
	}
}
