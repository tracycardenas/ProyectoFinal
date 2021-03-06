package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;
import ec.edu.hogwarts.SistemaInstitucion.model.PlanAnalitico;

@Named
@RequestScoped
public class MateriasView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private MateriaONLocal materiaON;
	
	private int id;
	
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
	
	public String editarMateria() {
		return "Insertar-Materia?faces-redirect=true&id="+this.materiaActual.getId();
	}
	
	public void cargarDatosMateria() {
		Materia m = materiaON.getMateriaporCodigo(this.id);
		this.materia = m;
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
	 * 			M??TODOS PARA NUEVA MATERIA
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
		
		m1.setNombre("??LGEBRA LINEAL");
		m2.setNombre("ANTROPOLOG??A FILOS??FICO-TEOL??GICA");
		m3.setNombre("C??LCULO DE UNA VARIABLE");
		m4.setNombre("COMUNICACI??N ORAL Y ESCRITA");
		m5.setNombre("INTRODUCCI??N A LAS CIENCIAS DE LA COMPUTACI??N");
		m6.setNombre("L??GICA");
		
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
		
		m1.setDescripcion("FUNDAMENTOS TE??RICOS");
		m2.setDescripcion("INTEGRACI??N DE SABERES, CONTEXTOS Y CULTURA");
		m3.setDescripcion("FUNDAMENTOS TE??RICOS");
		m4.setDescripcion("COMUNICACI??N Y LENGUAJES");
		m5.setDescripcion("EPISTEMOLOG??A Y METODOLOG??A DE LA INVESTIGACI??N");
		m6.setDescripcion("EPISTEMOLOG??A Y METODOLOG??A DE LA INVESTIGACI??N");
		
		m1.setUnidad("B??SICA");
		m2.setUnidad("B??SICA");
		m3.setUnidad("B??SICA");
		m4.setUnidad("B??SICA");
		m5.setUnidad("B??SICA");
		m6.setUnidad("B??SICA");
		
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
