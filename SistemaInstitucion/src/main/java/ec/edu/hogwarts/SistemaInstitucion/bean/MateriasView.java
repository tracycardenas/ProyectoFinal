package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;
import ec.edu.hogwarts.SistemaInstitucion.model.PlanAnalitico;

@Named
@ViewScoped
public class MateriasView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private MateriaONLocal materiaON;
	private Materia materia;
	private Materia materiaActual;
	private PlanAnalitico planAnalitico;
	private List<Materia> materias;
	private List<MateriaPrerrequisito> materiasPrerrequisitos;
	
	private List<Materia> materiasSeleccionadas;
	private List<Materia> materiasFiltro;						//Materias presentado para filtroGlobal
	private static boolean bandera = true;						//Bandera para proceder a agregar las materias
	
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		if(bandera) {
			bandera = false;
			//this.agregarMaterias();
		}
		cargarMaterias();
		this.materia = new Materia();
		this.materiaActual = new Materia();
		this.planAnalitico = new PlanAnalitico();
		this.materiasFiltro = this.materias;
		this.materiasPrerrequisitos = new ArrayList<MateriaPrerrequisito>();
		this.materiasSeleccionadas = new ArrayList<Materia>();
	}
	/*
	 * 			CRUD MATERIAS - ON
	 * */
	public void insertarMateria(Materia m) {
		try {
			materiaON.insert(m);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void borrarMateria() {
		try {
			materiaON.delete(this.materiaActual.getId());
			materias.remove(this.materiaActual);
			this.materiaActual = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Materia Eliminada"));
			PrimeFaces.current().ajax().update("form:messages","form:dt-materias");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar"));
		}
	}
	
	public void cargarMaterias() {
		this.materias = materiaON.getMaterias();
	}
	
	/*
	 * 			FILTRO MATERIAS
	 * */
	public boolean globalFilterFunctionMaterias(Object value, Object filter, Locale locale) {	//FILTRO DE MATERIAS
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        Materia materia = (Materia) value;
        return materia.getCodigo().toLowerCase().contains(filterText)
                || materia.getNombre().toLowerCase().contains(filterText)
                || materia.getDescripcion().toLowerCase().contains(filterText)
                || materia.getNumeroNivel()< filterInt;
    }
	
	private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
	
	/*
	 * 			MÉTODOS PARA NUEVA MATERIA
	 * */
	public void agregarMateriasSeleccion() {
		this.materias.remove(this.materiaActual);
		this.materiasSeleccionadas.add(this.materiaActual);
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-seleccion");
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-preseleccion");
	}
	
	public void borrarMateriasSeleccion() {
		this.materiasSeleccionadas.remove(this.materiaActual);
		this.materias.add(this.materiaActual);
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-seleccion");
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-preseleccion");
	}
	
	public String guardarMateria() {
		try {
			if(this.materiasSeleccionadas.size()!=0) {
				for(Materia m: this.materiasSeleccionadas) {
					MateriaPrerrequisito mP = new MateriaPrerrequisito();
					mP.setMateriaRequerida(m);
					this.materiasPrerrequisitos.add(mP);
				}
			}
			this.materia.setPrerequisitos(this.materiasPrerrequisitos);
			this.materia.setPlanAnalitico(this.planAnalitico);
			this.insertarMateria(materia);
			return "Listar-Materia?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al agregar materia."));
			return null;
		}
		
	}
	
	/*
	 * 			SETS AND GETS
	 * */
	
	public void agregarMaterias() {
		Materia m1 = new Materia();
		Materia m2 = new Materia();
		Materia m3 = new Materia();
		Materia m4 = new Materia();
		Materia m5 = new Materia();
		Materia m6 = new Materia();
		
		m1.setCodigo("C-CE-ICO-001");
		m2.setCodigo("G-RF-ICO-001");
		m3.setCodigo("C-CE-ICO-002");
		m4.setCodigo("G-HU-ICO-001");
		m5.setCodigo("E-CT-ICO-001");
		m6.setCodigo("G-HU-ICO-002");
		
		m1.setNombre("ÁLGEBRA LINEAL");
		m2.setNombre("ANTROPOLOGÍA FILOSÓFICO-TEOLÓGICA");
		m3.setNombre("CÁLCULO DE UNA VARIABLE");
		m4.setNombre("COMUNICACIÓN ORAL Y ESCRITA");
		m5.setNombre("INTRODUCCIÓN A LAS CIENCIAS DE LA COMPUTACIÓN");
		m6.setNombre("LÓGICA");
		
		m1.setHorasClase(64);
		m2.setHorasClase(32);
		m3.setHorasClase(96);
		m4.setHorasClase(32);
		m5.setHorasClase(64);
		m6.setHorasClase(32);
		
		m1.setHorasPracticas(64);
		m2.setHorasPracticas(16);
		m3.setHorasPracticas(96);
		m4.setHorasPracticas(32);
		m5.setHorasPracticas(64);
		m6.setHorasPracticas(32);
		
		m1.setHorasUnidad(32);
		m2.setHorasUnidad(32);
		m3.setHorasUnidad(48);
		m4.setHorasUnidad(16);
		m5.setHorasUnidad(32);
		m6.setHorasUnidad(16);
		
		m1.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m2.setDescripcion("INTEGRACIÓN DE SABERES, CONTEXTOS Y CULTURA");
		m3.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m4.setDescripcion("COMUNICACIÓN Y LENGUAJES");
		m5.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		m6.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		
		m1.setUnidad("BÁSICA");
		m2.setUnidad("BÁSICA");
		m3.setUnidad("BÁSICA");
		m4.setUnidad("BÁSICA");
		m5.setUnidad("BÁSICA");
		m6.setUnidad("BÁSICA");
		
		m1.setPlanAnalitico(planAnalitico);
		m2.setPlanAnalitico(planAnalitico);
		m3.setPlanAnalitico(planAnalitico);
		m4.setPlanAnalitico(planAnalitico);
		m5.setPlanAnalitico(planAnalitico);
		m6.setPlanAnalitico(planAnalitico);
		
		this.insertarMateria(m1);
		this.insertarMateria(m2);
		this.insertarMateria(m3);
		this.insertarMateria(m4);
		this.insertarMateria(m5);
		this.insertarMateria(m6);
	}
	
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public PlanAnalitico getPlanAnalitico() {
		return planAnalitico;
	}
	public void setPlanAnalitico(PlanAnalitico planAnalitico) {
		this.planAnalitico = planAnalitico;
	}
	public MateriaONLocal getMateriaON() {
		return materiaON;
	}
	public void setMateriaON(MateriaONLocal materiaON) {
		this.materiaON = materiaON;
	}
	public Materia getMateriaActual() {
		return materiaActual;
	}
	public void setMateriaActual(Materia materiaActual) {
		this.materiaActual = materiaActual;
	}
	public List<MateriaPrerrequisito> getMateriasPrerrequisitos() {
		return materiasPrerrequisitos;
	}
	public void setMateriasPrerrequisitos(List<MateriaPrerrequisito> materiasPrerrequisitos) {
		this.materiasPrerrequisitos = materiasPrerrequisitos;
	}
	public List<Materia> getMateriasFiltro() {
		return materiasFiltro;
	}
	public void setMateriasFiltro(List<Materia> materiasFiltro) {
		this.materiasFiltro = materiasFiltro;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public List<Materia> getMateriasSeleccionadas() {
		return materiasSeleccionadas;
	}
	public void setMateriasSeleccionadas(List<Materia> materiasSeleccionadas) {
		this.materiasSeleccionadas = materiasSeleccionadas;
	}
	
}
