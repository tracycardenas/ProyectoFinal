package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CalificacionesONLocal;

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

	private Grupo grupo;

	private int periodoID;

	private Nivel nivel;

	private List<Nivel> niveles = new ArrayList<Nivel>();
	private List<Estudiante> estudiantes;
	private List<Integer> grupos = new ArrayList<Integer>();
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private List<Calificacion> calificaciones2 = new ArrayList<Calificacion>();

	private List<Materia> materias = new ArrayList<Materia>();

	List<Estudiante> estudiantesGrupo;

	private Calificacion calificacion;
	private String cedula;

	@PostConstruct
	public void init() {
		this.loadEstudiantes();
	}

	// Getters and Setters

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

	public List<Integer> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Integer> grupos) {
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
		System.out.println("GET CEDULA " + cedula);
		return cedula;
	}

	public void setCedula(String cedula) {
		System.out.println("SET CEDULA " + cedula);
		this.cedula = cedula;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		System.out.println("SET CALIFICACION " + calificacion.getEstudiante().getNombre());
		this.calificacion = calificacion;
	}

	// METODOS

	public void cargarNiveles(String cedula) {
		grupos = new ArrayList<Integer>();

		List<Integer> gruposAnteriores = new ArrayList<Integer>();

		for (int i = 0; i < calificacionON.getCalificacion().size(); i++) {
			if (cedula.equals(calificacionON.getCalificacion().get(i).getEstudiante().getCedula())) {
				calificaciones2.add(calificacionON.getCalificacion().get(i));

			}

		}

		for (int j = 0; j < calificaciones2.size(); j++) {
			grupo = calificaciones2.get(j).getGrupo();
			gruposAnteriores.add(grupo.getPeriodo());

		}
		
		//ELIMINAR VALORES REPETIDOS
		Set<Integer> hashSet = new HashSet<Integer>(gruposAnteriores);
		gruposAnteriores.clear();
		gruposAnteriores.addAll(hashSet);

		grupos = gruposAnteriores;

	}

	public void loadEstudiantes() {
		this.estudiantes = personasON.getEstudiantes();
		for (int i = 0; i < estudiantes.size(); i++) {
			Persona estudiante = estudiantes.get(i);
		}

	}

	public void actualizarCalificaciones(AjaxBehaviorEvent e) {

		Materia materiaAnterior = new Materia();
		calificaciones = new ArrayList<Calificacion>();
		materiaAnterior.setNombre("");
		if (periodoID != 0) {

			System.out.println("Calificaciones num: " + calificacionON.getCalificacion().size());

			for (int i = 0; i < calificaciones2.size(); i++) {
				if (periodoID == calificaciones2.get(i).getGrupo().getPeriodo()) {

					calificacion = calificaciones2.get(i);
					calificaciones.add(calificacion);
					materiaAnterior = calificaciones2.get(i).getGrupo().getMateria();

				}
			}

		}
	}

	public List<SelectItem> getSelecItemPeriodoss() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<Integer> listaPeriodos = grupos;

		for (Integer mater : listaPeriodos) {
			SelectItem item = new SelectItem(mater.intValue());
			selectItems.add(item);

		}
		return selectItems;
	}

}
