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

		/*for (int i = 0; i < nivelON.getNiveles().size() ; i++) {
			System.out.println("entre al primer for de niveles " +nivelON.getNiveles().size() );
			System.out.println("materias" + nivelON.getNiveles().get(i).getMaterias().size());
			for (int j = 0; j < nivelON.getNiveles().get(i).getMaterias().size(); j++) {
				System.out.println("entre al segundo for de materias "+nivelON.getNiveles().get(i).getMaterias().size());
				for (int j2 = 0; j2 < nivelON.getNiveles().get(i).getMaterias().get(j).getGrupos().size(); j2++) {
					System.out.println("entre al tercer for de grupos "+ nivelON.getNiveles().get(i).getMaterias().get(j).getGrupos().size());
					for (int k = 0; k < nivelON.getNiveles().get(i).getMaterias().get(j).getGrupos().get(j2).getCalificaciones().size(); k++) {
						System.out.println("entre al cuarto for de calificaciones "+nivelON.getNiveles().get(i).getMaterias().get(j).getGrupos().get(j2).getCalificaciones().size());
						if (cedula.equals(nivelON.getNiveles().get(i).getMaterias().get(j).getGrupos().get(j2).getCalificaciones().get(k).getEstudiante().getCedula())) {
							nivel = nivelON.getNiveles().get(i);
							niveles.add(nivel);
							System.out.println("ENTRE AL IF");
							/*if (nivelAnterior.getNivel()!=nivel.getNivel()) {
								niveles.add(nivel);
							
							}
							nivelAnterior=nivel;
						}
					}
					
				}
				
				
			}
		}*/
		
		
		/*for (int i = 0; i < grupoON.getGrupo().size(); i++) {
			if (cedula.equals(grupoON.getGrupo().get(i).getDocente().getCedula())) {
				materia = grupoON.getGrupo().get(i).getMateria();
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
		}*/

	}
	
	

	public void loadEstudiantes() {
		this.estudiantes = personasON.getEstudiantes();
		for (int i = 0; i < estudiantes.size(); i++) {
			Persona estudiante = estudiantes.get(i);
		}

	}

	/*public void actualizarGrupos(AjaxBehaviorEvent e) {
		System.out.println("*******************************************");
		System.out.println("MATERIA ID " + materiaID);
		System.out.println("GRUPO ID de actualizar grupos " + grupoID);
		System.out.println();
		selecItemGrupos = new ArrayList<SelectItem>();
		List<Grupo> listGrupos = grupoON.getGrupo();

		for (Grupo grup : listGrupos) {
			if (grup.getMateria().getId() == materiaID) {
				SelectItem item = new SelectItem(grup.getId(), grup.getNombre());
				selecItemGrupos.add(item);
			}
		}
	}*/
	

	
	public void actualizarCalificaciones(AjaxBehaviorEvent e) {
		System.out.println("*******************************************");
		System.out.println("PERIODO ID " + periodoID);
		calificaciones=new ArrayList<Calificacion>();
		
		if (periodoID!=0) {
			
			
			
			for (int i = 0; i < calificacionON.getCalificacion().size(); i++) {
				if (periodoID==calificacionON.getCalificacion().get(i).getGrupo().getPeriodo()) {
					calificacion= calificacionON.getCalificacion().get(i);
					calificaciones.add(calificacion);
				}
			}
			
			
		}
	}	
			
			
			/*
			
			Nivel nivelActual;
		
			for (int i = 0; i < niveles.size(); i++) {
				System.out.println("ENTRE AL FOR DE NIVELES");
				if (nivelID==niveles.get(i).getId()) {
					nivelActual = niveles.get(i);
					System.out.println("NIVEL ACTUAL"+nivelActual.getId());
					System.out.println("materiass"+nivelActual.getMaterias().get(0).getNombre());

					materias=nivelActual.getMaterias();
					/*System.out.println("materias"+niveles.get(i).getMaterias().size());
					for (int j = 0; j < niveles.get(i).getMaterias().size(); j++) {
						System.out.println("entre al if de mater");
						materias.add(niveles.get(i).getMaterias().get(j));
						System.out.println("MATERIAS EN ACTUALIZAR "+materias.get(i).getNombre());

					}
				}
			}
			System.out.println("TERMINO EL PRIMER FOR");
			for (int i = 0; i < materias.size(); i++) {
				System.out.println("ENTRO AL FOR DE MATERIAS" +materias.size());
				for (int j = 0; j < materias.get(i).getGrupos().size(); j++) {
					System.out.println("entro al for de grupos" +materias.get(i).getGrupos().size());
					calificaciones.add(materias.get(i).getGrupos().get(j).getCalificaciones().get(j));
				}
			}
			
			/*for (int i = 0; i < calificacionesBD.size(); i++) {
				if (grupoID == calificacionesBD.get(i).getGrupo().getId()) {
					calificaciones.add(calificacionesBD.get(i));
					System.out.println("antes de total************------------");
					total= calificaciones.get(i).getAporte1()+calificaciones.get(i).getAporte2()+
							calificaciones.get(i).getExamen1() +calificaciones.get(i).getExamen2();
					
					System.out.println("TOTAAL******** "+total);
					calificaciones.get(i).setTotal(total);
					
						
					if (calificaciones.get(i).getTotal()>69) {
						calificaciones.get(i).setEstado("Aprobado");
					}
					if (calificaciones.get(i).getTotal()<69) {
						calificaciones.get(i).setEstado("Reprobado");
					}
					if (calificaciones.get(i).getAporte1()==0 || calificaciones.get(i).getAporte2()==0 ||
							calificaciones.get(i).getExamen1() ==0||calificaciones.get(i).getExamen2()==0) {
						calificaciones.get(i).setEstado("Cursando");
					}
					try {
						calificacionON.update(calificaciones.get(i));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("-----------------------------------------------------------------");
				}
				
			}*/
			
			
	
	
	
	

	public List<SelectItem> getSelecItemPeriodoss() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<Grupo> listaPeriodos = grupos;

		for (Grupo mater : listaPeriodos) {
			SelectItem item = new SelectItem(mater.getPeriodo());
			selectItems.add(item);

		}
		return selectItems;
	}
	


	
	/*public List<SelectItem> getSelectItemGrupos() {
		return selecItemGrupos;
	}
	
	

	public List<Estudiante> getEstudiantesGrupo() {
		return estudiantesGrupo;
	}*/

	


	
	

}
