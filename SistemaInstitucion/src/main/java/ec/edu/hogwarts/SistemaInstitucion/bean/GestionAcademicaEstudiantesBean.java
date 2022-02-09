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
import ec.edu.hogwarts.SistemaInstitucion.business.NivelONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@ViewScoped
public class GestionAcademicaEstudiantesBean implements Serializable {
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
	
	@Inject
	private NivelONLocal nivelON;

	private Grupo grupo;

	private int periodoID;
	
	private Nivel nivel;
	
	

	private List<Nivel> niveles = new ArrayList<Nivel>();
	private List<Estudiante> estudiantes;
	private List<Grupo> grupos = new ArrayList<Grupo>() ;
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private List<Calificacion> calificaciones2 = new ArrayList<Calificacion>();

	private List<Materia> materias = new ArrayList<Materia>();

	private List<Calificacion> calificacionesBD;

	List<Estudiante> estudiantesGrupo;
	


	private Calificacion calificacion;
	private String cedula;
	

	@PostConstruct
	public void init() {
		this.loadEstudiantes();
	}	
	
	//Getters and Setters
	
	

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	

	public int getPeriodoID() {
		return periodoID;
	}

	public void setPeriodoID(int periodoID) {
		this.periodoID = periodoID;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
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
	


	public List<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
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
	
	

	public void cargarNiveles(String cedula) {
		Grupo nivelAnterior = new Grupo();
		nivelAnterior.setPeriodo(0);
		System.out.println("ENTRE A CARGAR NIVELES "+cedula);
		System.out.println(" niveles " +nivelON.getNiveles().size() );
		
		for (int i = 0; i < calificacionON.getCalificacion().size(); i++) {
			if (cedula.equals(calificacionON.getCalificacion().get(i).getEstudiante().getCedula())) {
				calificaciones2.add(calificacionON.getCalificacion().get(i));
				for (int j = 0; j <calificaciones2.size(); j++) {
					grupo=calificaciones2.get(j).getGrupo();
					
					if (nivelAnterior.getPeriodo()==grupo.getPeriodo()) {
						
					
					} else {
						grupos.add(grupo);
						
						nivelAnterior=grupo;
						
					}
					
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


	
	public void actualizarCalificaciones(AjaxBehaviorEvent e) {
		System.out.println("*******************************************");
		System.out.println("PERIODO ID " + periodoID);
		calificaciones=new ArrayList<Calificacion>();
		Materia materiaAnterior = new Materia();
		materiaAnterior.setNombre("");
		if (periodoID!=0) {
			
				System.out.println("Calificaciones num: "+calificacionON.getCalificacion().size());
			
				for (int i = 0; i < calificacionON.getCalificacion().size(); i++) {
					if (periodoID==calificacionON.getCalificacion().get(i).getGrupo().getPeriodo()) {
						if (materiaAnterior.getNombre().equals(calificacionON.getCalificacion().get(i).getGrupo().getMateria().getNombre())) {
							
						} else {
							calificacion= calificacionON.getCalificacion().get(i);
							calificaciones.add(calificacion);
							materiaAnterior = calificacionON.getCalificacion().get(i).getGrupo().getMateria();
						}
						
					}
				}
			
			
		}
	}	

	public List<SelectItem> getSelecItemPeriodoss() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<Grupo> listaPeriodos = grupos;

		for (Grupo mater : listaPeriodos) {
			SelectItem item = new SelectItem(mater.getPeriodo());
			selectItems.add(item);

		}
		return selectItems;
	}
	
	
	

}
