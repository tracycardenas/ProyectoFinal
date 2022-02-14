package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CalificacionesONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.GrupoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@ViewScoped
public class GestionAcademicaDocentesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CalificacionesONLocal calificacionON;

	@Inject
	private PersonaONLocal personasON;

	@Inject
	private GrupoONLocal grupoON;

	@Inject
	private MateriaONLocal materiaON;

	private Grupo grupo;
	private Materia materia;

	private int materiaID;
	private int grupoID;
	
	

	private List<Materia> materias = new ArrayList<Materia>();
	private List<Estudiante> estudiantes;
	private List<Grupo> grupos = new ArrayList<Grupo>() ;
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private List<Calificacion> calificacionesBD;

	List<Estudiante> estudiantesGrupo;
	
	private List<SelectItem> selecItemGrupos;


	private Calificacion calificacion;
	private String cedula;
	
	private double total =0;

	@PostConstruct
	public void init() {
		this.loadEstudiantes();
	}	
	
	//Getters and Setters
	
	
	
	public int getMateriaID() {
		return materiaID;
	}




	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public void setMateriaID(int materiaID) {
		this.materiaID = materiaID;
	}

	public int getGrupoID() {
		return grupoID;
	}

	public void setGrupoID(int grupoID) {
		this.grupoID = grupoID;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	
	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getCedula() {
		System.out.println("GET CEDULA "+cedula);
		return cedula;
	}

	public void setCedula(String cedula) {
		System.out.println("SET CEDULA "+cedula);
		this.cedula = cedula;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Calificacion getCalificacion() {
		//System.out.println("GET CALIFICACION "+calificacion.getEstudiante().getNombre());
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		System.out.println("SET CALIFICACION "+calificacion.getEstudiante().getNombre());
		this.calificacion = calificacion;
	}

	
	//METODOS
	
	public String guardarCalificacion() {
		try {
			calificacionON.insert(this.calificacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void cargarMaterias(String cedula) {
		Materia matAnterior = new Materia();
		matAnterior.setNombre("");
		for (int i = 0; i < grupoON.getGrupos().size(); i++) {
			if (cedula.equals(grupoON.getGrupos().get(i).getDocente().getCedula())) {
				materia = grupoON.getGrupos().get(i).getMateria();
				System.out.println("MAT ANTERIOR antes del if"+matAnterior.getNombre());
				System.out.println("MAT ACTUAL "+materia.getNombre());
				if (matAnterior.getNombre().equals(materia.getNombre())) {
					

				} else {
					materias.add(materia);
					System.out.println("MAT ANTERIOR "+matAnterior.getNombre());
					System.out.println("MAT ACTUAL "+materia.getNombre());
					matAnterior=materia;
					System.out.println("MAT ANTERIOR Actualizada"+matAnterior.getNombre());
				}
				
			}
		}

	}
	
	

	public void loadEstudiantes() {
		this.estudiantes = personasON.getEstudiantes();
		for (int i = 0; i < estudiantes.size(); i++) {
			Persona estudiante = estudiantes.get(i);
		}

	}

	public void actualizarGrupos(AjaxBehaviorEvent e) {
		System.out.println("*******************************************");
		System.out.println("MATERIA ID " + materiaID);
		System.out.println("GRUPO ID de actualizar grupos " + grupoID);
		System.out.println();
		selecItemGrupos = new ArrayList<SelectItem>();
		List<Grupo> listGrupos = grupoON.getGrupos();

		for (Grupo grup : listGrupos) {
			if (grup.getMateria().getId() == materiaID) {
				SelectItem item = new SelectItem(grup.getId(), grup.getNombre());
				selecItemGrupos.add(item);
			}
		}
	}
	

	
	public void actualizarEstudiantes(AjaxBehaviorEvent e) {
		System.out.println("*******************************************");
		System.out.println("MATERIA ID " + materiaID);
		System.out.println("GRUPO ID" + grupoID);
		
		if (materiaID!=0) {
			
			
			calificacionesBD = calificacionON.getCalificacion();
			System.out.println("CALIFICACIONES BD " +calificacionesBD.size());
			calificaciones = new ArrayList<Calificacion>();
			for (int i = 0; i < calificacionesBD.size(); i++) {
				if (grupoID == calificacionesBD.get(i).getGrupo().getId()) {
					calificaciones.add(calificacionesBD.get(i));
					System.out.println("antes de total************------------");
					
				}
				
			}
			
						
		}
	}
	
	
	
	
	public void cargarCal(Calificacion calificacion) {
		this.calificacion = calificacion;
		
	}
	
	public void editarCal() {
		try {
			total= calificacion.getAporte1()+calificacion.getAporte2()+
					calificacion.getExamen1() +calificacion.getExamen2();
			
			calificacion.setTotal(total);
			
				
			if (calificacion.getTotal()>69) {
				calificacion.setEstado("Aprobado");
			}
			if (calificacion.getTotal()<69) {
				calificacion.setEstado("Reprobado");
			}
			if (calificacion.getAporte1()==0 || calificacion.getAporte2()==0 ||
					calificacion.getExamen1() ==0||calificacion.getExamen2()==0) {
				calificacion.setEstado("Cursando");
			}
			this.calificacionON.update(calificacion);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<SelectItem> getSelecItemMaterias() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<Materia> listaMaterias = materias;

		for (Materia mater : listaMaterias) {
			SelectItem item = new SelectItem(mater.getId(), mater.getNombre());
			selectItems.add(item);

		}
		return selectItems;
	}
	


	
	public List<SelectItem> getSelectItemGrupos() {
		return selecItemGrupos;
	}
	
	

	public List<Estudiante> getEstudiantesGrupo() {
		return estudiantesGrupo;
	}

	


	
	

}
